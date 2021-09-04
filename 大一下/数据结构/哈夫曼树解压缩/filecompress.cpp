#include "filecompress.h"

void FileCompress::dispose(huffmanNode *t)
{
    if(t!=NULL){
        dispose(t->leftChild);
        dispose(t->rightChild);
        delete t;
    }
}

void FileCompress::code(huffmanNode *t, QString str)
{
    if(t != NULL){
        if(t->leftChild == NULL && t->rightChild == NULL){
            map_code[t->element] = str;
        }
        else{
            code(t->leftChild,str+"0");
            code(t->rightChild,str+"1");
        }

    }
}

void FileCompress::run()
{
    if(flag){
        compress(path);

    }else{
        uncompress(path);
    }


}

FileCompress::FileCompress(QObject *parent):QThread(parent)
{
    BeforeSize = 0;
    AfterSize = 0;
    CompressTime = 0;
    UncompressTime = 0;
    root = NULL;

}

FileCompress::~FileCompress()
{
    if(root != NULL){
        dispose(root);
    }

}

bool cmp(huffmanNode*a,huffmanNode* b){
    return a->weight < b->weight;

}

void FileCompress::compress(QString path)
{
    if(root != NULL){
        dispose(root);
        root = NULL;
        map_code.clear();
        map_weight.clear();
    }
    double start = clock();
    QFile ifs(path);
    if(!ifs.open(QFile::ReadOnly)){

        emit error();
        return;
    }
    QByteArray buf1;
    unsigned char c;
    int n;
    BeforeSize = 0;
    long long precentage;
    while(!ifs.atEnd()){
        buf1 = ifs.read(1024);
        n = buf1.size();
        BeforeSize += n;
        for(int i=0;i<n;i++){
            c = buf1[i];
            map_weight[c]++;
        }
    }
    ifs.close();
    precentage = BeforeSize / 100;
    QVector<huffmanNode*> q;
    QMap<unsigned char,long long>::iterator it = map_weight.begin();
    for(;it != map_weight.end();it++){
        huffmanNode *node = new huffmanNode(it.key(),it.value());
        q.push_back(node);
    }

    huffmanNode *w ,*x,*y;
    while(q.size() > 1){
        qSort(q.begin(),q.end(),cmp);
        x = q[0];
        y = q[1];
        q.erase(q.begin());
        q.erase(q.begin());
        long long weight = x->weight + y->weight;
        w = new huffmanNode(0,weight,x,y);
        q.push_back(w);
    }
    root = q[0];
    code(root,"");


    int m = path.lastIndexOf('/');
    name = path.mid(m+1,path.length());

    int index = path.indexOf('.');
    CompressFilepath = path.mid(0,index);
    CompressFilepath += ".huf";
    QFile ofs(CompressFilepath);
    ofs.open(QFile::WriteOnly);
    ifs.open(QFile::ReadOnly);
    unsigned char value=0;
    int pos = 0;
    QString str;
    QDataStream out(&ofs);
    long long cur_count = 0;
    AfterSize = 0;
    while(!ifs.atEnd()){
        buf1 = ifs.read(1024);
        for(int i=0;i<buf1.size();i++){
            cur_count++;
            if(cur_count == precentage){
                emit mysigals();
                cur_count = 0;
            }

            str = map_code[buf1[i]];
            for(int j=0;j<str.length(); j++){
                value <<= 1;
                if(str.at(j) == '1')
                    value += 1;
                pos++;
                if(pos == 8){

                    out<<value;
                    AfterSize++;
                    pos = 0;
                }
            }
        }
    }
    if(pos < 8){
        value <<= (8-pos);
        out<<value;
        AfterSize++;
    }
    ofs.close();
    ifs.close();
    double end = clock();
    CompressTime = end - start;
}

void FileCompress::uncompress(QString path)
{
    double start = clock();
    QFile ifs(CompressFilepath); 
    if(!ifs.open(QFile::ReadOnly)){

        emit error();
        return;
    }
    QFile ofs(path);
    ofs.open(QFile::WriteOnly);
    QByteArray buf;
    QDataStream out(&ofs);
    unsigned char c;
    int n;


    huffmanNode *cur = root;
    int count = 0;
    long long precentage = AfterSize / 100;
    long long cur_count = 0;

    while(!ifs.atEnd()){
        buf = ifs.read(1024);
        n = buf.size();
        for(int i=0;i<n;i++){
            cur_count++;
            if(cur_count == precentage){
                emit mysigals();
                cur_count = 0;
            }
            c = buf[i];
            for(int j=7;j>=0;j--){
                if((c & (1<<j)) == 0){
                    cur = cur->leftChild;
                }
                else
                    cur = cur->rightChild;
                if(cur->leftChild == NULL && cur->rightChild == NULL){
                    out<<cur->element;
                    cur = root;
                    count++;
                    if(count == BeforeSize){
                        ifs.close();
                        ofs.close();
                        double end = clock();
                        UncompressTime = end - start;
                        return ;
                    }
                }


            }

        }
    }


}

void FileCompress::setPath(QString path)
{
    this->path = path;

}

void FileCompress::setFlag(bool flag)
{
    this->flag = flag;

}

QString FileCompress::getName()
{
    return name;
}

bool FileCompress::getFlag()
{
    return flag;
}

long long FileCompress::getBeforeSize()
{
    return BeforeSize;
}

long long FileCompress::getAfterSize()
{
    return AfterSize;
}

double FileCompress::getCompressTime()
{
    return CompressTime;
}

double FileCompress::getUncompressTime()
{
    return UncompressTime;
}
