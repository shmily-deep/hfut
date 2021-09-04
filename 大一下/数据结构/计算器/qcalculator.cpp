#include "qCalculator.h"
#include<QMessageBox>

qCalculator::qCalculator()
{
    map = { {'(',0},{'+',1},{'-',1},{'*',2},{'/',2},{')',3} };
}

qCalculator::qCalculator(QString str)
{
    map = { {'(',0},{'+',1},{'-',1},{'*',2},{'/',2},{')',3} };
    set(str);
}

void qCalculator::set(QString str)
{
    this->str = str;
}

double qCalculator::get()
{
    int i = 0,len = str.length();
    int priority_top,priority_cur;
    QString num ;
    while(i < len){
        if(isdight(str[i])){
            num = "";
            while(i < len && (isdight(str[i]) || str[i] == '.')){
                num.append(str[i]);
                i++;
            }
            numbers.push(num.toDouble());
        }else{
            if(operators.empty()){
                operators.push(str[i]);
                i++;
                continue;
            }
            priority_top = map[operators.top()];
            priority_cur = map[str[i]];
            if(priority_cur == 0){
                operators.push(str[i]);
            }
            else if(priority_cur == 3){
                while(operators.top() != '('){
                    numbers.push(result());
                }
                operators.pop();
            }
            else{
                while(!operators.empty() && priority_cur <= priority_top){
                    numbers.push(result());
                    if(!operators.empty()){
                        priority_top = map[operators.top()];
                    }
                }
                operators.push(str[i]);
            }
            i++;
        }
    }
    while(!operators.empty()){
        numbers.push(result());
    }
    double n = numbers.top();
    numbers.pop();
    return n;
}

double qCalculator::result()
{
    double b = numbers.top();
    numbers.pop();
    double a = numbers.top();
    numbers.pop();
    QChar c = operators.top();
    operators.pop();
    if(c == '+')
        return a +b;
    else if(c == '-')
        return a - b;
    else if(c == '*')
        return a * b;
    else if(c == '/'){
        if(b == 0){
            return NULL;
        }

        return a / b;
    }
}

bool qCalculator::isdight(QChar c)
{
    if(c <= '9' && c >= '0')
        return true;
    return false;

}
