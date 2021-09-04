#ifndef QCALCULATOR_H
#define QCALCULATOR_H

#include<QString>
#include<QStack>
#include<QMap>
class qCalculator
{
public:
    qCalculator();
    qCalculator(QString str);

    void set(QString str);
    double get();
    double result();
    bool isdight(QChar c);

private:
    QStack<double>numbers;
    QStack<QChar>operators;
    QString str;
    QMap<QChar,int>map;
};

#endif // QCALCULATOR_H
