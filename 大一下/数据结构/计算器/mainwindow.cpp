#include "mainwindow.h"
#include "ui_mainwindow.h"
#include<QPushButton>
#include<QMessageBox>
#include<QStringLiteral>


int count = 0;
MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    QPushButton *NumButtons[10];
    for(int i=0;i<10;i++){
        QString buttonName = "Button" + QString::number(i);
        NumButtons[i] = MainWindow::findChild<QPushButton*>(buttonName);
        connect(NumButtons[i],SIGNAL(pressed()),this,SLOT(NumButton_Pressed()));
    }
    connect(ui->Add,SIGNAL(pressed()),this,SLOT(MathButton_Pressed()));
    connect(ui->Sub,SIGNAL(pressed()),this,SLOT(MathButton_Pressed()));
    connect(ui->Mul,SIGNAL(pressed()),this,SLOT(MathButton_Pressed()));
    connect(ui->Div,SIGNAL(pressed()),this,SLOT(MathButton_Pressed()));
    connect(ui->Spot,SIGNAL(pressed()),this,SLOT(MathButton_Pressed()));
    connect(ui->LeftBracket,SIGNAL(pressed()),this,SLOT(MathButton_Pressed()));
    connect(ui->RightBracket,SIGNAL(pressed()),this,SLOT(MathButton_Pressed()));

    connect(ui->Backspace,SIGNAL(pressed()),this,SLOT(Clear_Back_Pressed()));
    connect(ui->Clear,SIGNAL(pressed()),this,SLOT(Clear_Back_Pressed()));

    connect(ui->Equal,SIGNAL(pressed()),this,SLOT(EqualButton_Pressed()));



}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::NumButton_Pressed()    //槽函数--当按钮按钮下，文本框显示内容
{
    QPushButton *button = (QPushButton *)sender();//适用多个同类型组件共用一个槽函数
    QString ButtonVal = button->text();
    QString Display = ui->Display->text();//Qt::CaseInsensitive) == 0
    QString temp = Display.right(1);
    if((QString::compare(Display,"0") == 0)||
            (QString::compare(Display,"0.0") == 0)){
        ui->Display->setText(ButtonVal);
    }
    else if(temp.length() && temp[0] == ')'){
        QString newVal = Display + ButtonVal;
        ui->Display->setText(newVal);
        QMessageBox::critical(NULL,"错误","数字前面不能为右括号",QMessageBox::Ok);
    }
    else{
        QString newVal = Display + ButtonVal;
        ui->Display->setText(newVal);
    }
}

void MainWindow::MathButton_Pressed()   //槽函数--当+ - * / . (  ) 按钮按下时显示
{
    QString display = ui->Display->text();
    QPushButton *button = (QPushButton *)sender();
    QString MathSymbol = button->text();
    QString temp = display.right(1);//获取最后一位，返回值为字符串
    QRegExp reg2("[-+*/]");
    QRegExp reg3("[-+*/.(]");
    if((QString::compare(display,"0") == 0)||
            (QString::compare(display,"0.0") == 0)){
        display = "";
        temp = "";
    }
    if(QString::compare(MathSymbol,"+",Qt::CaseInsensitive) == 0){
        display.append("+");
        ui->Display->setText(display);

    }
    else if(QString::compare(MathSymbol,"-",Qt::CaseInsensitive) == 0){
        display.append("-");
        ui->Display->setText(display);
    }
    else if(QString::compare(MathSymbol,"*",Qt::CaseInsensitive) == 0){
        display.append("*");
        ui->Display->setText(display);
    }
    else if(QString::compare(MathSymbol,"/",Qt::CaseInsensitive) == 0){
        display.append("/");
        ui->Display->setText(display);
    }
    else if(QString::compare(MathSymbol,".",Qt::CaseInsensitive) == 0){

        ui->Display->setText(display+".");
        if(temp.length()){
            QRegExp reg("[0-9]");
            if(!reg.exactMatch(temp)){
                QMessageBox::critical(NULL,"错误","小数点前不为数字",QMessageBox::Ok);
                return;
            }
        }
        else{
            QMessageBox::critical(NULL,"错误","小数点前为空",QMessageBox::Ok);
            return;
        }
        QRegExp reg1("[-+*/.()]");
        int pos = display.lastIndexOf(reg1);
        if(pos != -1 && display[pos] == '.'){
            QMessageBox::critical(NULL,"错误","一串数字只能有一个小数点",QMessageBox::Ok);
            return;
        }
    }
    else if(QString::compare(MathSymbol,"(",Qt::CaseInsensitive) == 0){
        ui->Display->setText(display + "(");
        QRegExp reg4("[0-9.)]");
        if(temp.length() && reg4.exactMatch(temp)){
            QMessageBox::critical(NULL,"错误","<FONT size=3><div>左括号前不能为右括号数字小数点<br/></div>",QMessageBox::Ok);
        }

        count++;
        return;
    }
    else if(QString::compare(MathSymbol,")",Qt::CaseInsensitive) == 0){
        display.append(")");
        ui->Display->setText(display);
        if(count <= 0){
            QMessageBox::critical(NULL,"错误","<FONT size=3><div>前面没有匹配左括号"
                                            "<br/></div>",QMessageBox::Ok);
            count--;
            return;
        }
        QRegExp reg5("[-+*/.(]");
        if(temp.length()){
            if(reg5.exactMatch(temp)){
                QMessageBox::critical(NULL,"错误","右括号前不能为+ - * / . (",QMessageBox::Ok);

            }
        }
        else{
            QMessageBox::critical(NULL,"错误","右括号前为空",QMessageBox::Ok);

        }
        count--;
        return;
    }

    if(temp.length()){
        if(reg2.exactMatch(MathSymbol) && reg3.exactMatch(temp)){
            QMessageBox::critical(NULL,"错误","符号前面不能为+ - * / . (",QMessageBox::Ok);
        }
    }else{
        if(QString::compare(MathSymbol,"(",Qt::CaseInsensitive) != 0){
            QMessageBox::critical(NULL,"错误","<FONT size=3><div>符号前为空"
                                            "<br/></div>",QMessageBox::Ok);
        }

    }

}

void MainWindow::Clear_Back_Pressed()   //实现清除，消去最后一位的功能
{
    QPushButton *button = (QPushButton *)sender();
    QString display = ui->Display->text();
    QString temp = display.right(1);
    if(QString::compare(button->text(),"C",Qt::CaseInsensitive) == 0){
        ui->Display->setText("");
        count = 0;

    }
    else if(button == ui->Backspace){
        display = display.remove(display.length()-1,1);
        ui->Display->setText(display);
        if(QString::compare(temp,"(",Qt::CaseInsensitive) == 0){
            count--;
        }
        else if(QString::compare(temp,")",Qt::CaseInsensitive) == 0){
            count++;
        }
    }

}

void MainWindow::EqualButton_Pressed()  //实现=按钮按下时，显示计算结果
{
    QString display = ui->Display->text();
   try{
        cal.set(display);
        double result = cal.get();
        if(result == NULL){
            QMessageBox::critical(NULL,"错误","<FONT size=3><div>除数为0"
                                                     "<br/></div>",QMessageBox::Ok);
        }
        ui->Display->setText(QString::number(result,'g',16));
    }catch(_EXCEPTION_POINTERS *p){

        QMessageBox::critical(NULL,"错误","数学表达式有误",QMessageBox::Ok);
    }

}
















