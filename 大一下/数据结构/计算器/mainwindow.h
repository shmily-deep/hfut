#ifndef MAINWINDOW_H
#define MAINWINDOW_H
# pragma execution_character_set("utf-8")

#include <QMainWindow>
#include"qcalculator.h"

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT


public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

private:
    Ui::MainWindow *ui;
    qCalculator cal;
private slots:
    void NumButton_Pressed();//槽函数--当按钮按钮下，文本框显示内容
    void MathButton_Pressed();//槽函数--当+ - * / . (  ) 按钮按下时显示
    void Clear_Back_Pressed();//实现清除，消去最后一位的功能
    void EqualButton_Pressed();//实现=按钮按下时，显示计算结果




};
#endif // MAINWINDOW_H
