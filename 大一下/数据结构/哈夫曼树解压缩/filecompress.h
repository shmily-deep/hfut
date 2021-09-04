#ifndef FILECOMPRESS_H
#define FILECOMPRESS_H
# pragma execution_character_set("utf-8")
#include<QMap>
#include<QString>
#include<QFile>
#include<QtAlgorithms>
#include<QVector>
#include<QDataStream>
#include<QThread>
#include<QObject>
#include<ctime>
#include<QMessageBox>


#include<QDebug>
struct huffmanNode{
    unsigned char element;
    long long weight;
    huffmanNode *leftChild,*rightChild;
    huffmanNode(unsigned char element = NULL,long long weight = 0,huffmanNode *leftChild = NULL,
                huffmanNode *rightChild = NULL){
        this->element = element;
        this->weight = weight;
        this->leftChild = leftChild;
        this->rightChild = rightChild;
    }
};

class FileCompress:public QThread
{
    Q_OBJECT
private:
    QMap<unsigned char,long long> map_weight;
    QMap<unsigned char,QString>map_code;
    huffmanNode *root;
    QString CompressFilepath;   //压缩后文件路径
    QString path;//文件路径
    long long BeforeSize;//压缩前目标文件大小
    long long AfterSize;//压缩文件大小
    QString name;//文件名
    bool flag;//压缩和解压标志，当为true时，压缩，当为false时，解压
    void dispose(huffmanNode *t);
    void code(huffmanNode *t,QString str);
    void run();
    double CompressTime;//压缩时间
    double UncompressTime;//解压时间
signals:
    void mysigals();//自定义信号用于进度条
    void error();//用于错误信息
public:
    FileCompress(QObject *parent = nullptr);
    ~FileCompress();
    void compress(QString path);
    void uncompress(QString path);
    void setPath(QString path);
    void setFlag(bool flag);
    QString getName();
    bool getFlag();

    //获取文件信息
    
    long long getBeforeSize();
    long long getAfterSize();
    double getCompressTime();
    double getUncompressTime();
};
#endif // FILECOMPRESS_H
