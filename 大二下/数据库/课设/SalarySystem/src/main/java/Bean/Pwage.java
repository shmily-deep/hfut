package Bean;

import java.sql.Date;

public class Pwage {
    private String Sno;             //工号
    private java.sql.Date Rdate;    //结算日期
    private int Wsub;               //工龄补贴
    private int Late;               //迟到罚款
    private int Leave;              //早退罚款
    private int Extra;              //加班费
    private int Evec;               //出差补贴
    private int Fattend;            //全勤奖
    private int Sala;               //应发工资
    private int Npay;               //实发工资

    public Pwage(String sno, Date rdate, int wsub, int late, int leave, int extra, int evec, int fattend, int sala, int npay) {
        Sno = sno;
        Rdate = rdate;
        Wsub = wsub;
        Late = late;
        Leave = leave;
        Extra = extra;
        Evec = evec;
        Fattend = fattend;
        Sala = sala;
        Npay = npay;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public Date getRdate() {
        return Rdate;
    }

    public void setRdate(Date rdate) {
        Rdate = rdate;
    }

    public int getWsub() {
        return Wsub;
    }

    public void setWsub(int wsub) {
        Wsub = wsub;
    }

    public int getLate() {
        return Late;
    }

    public void setLate(int late) {
        Late = late;
    }

    public int getLeave() {
        return Leave;
    }

    public void setLeave(int leave) {
        Leave = leave;
    }

    public int getExtra() {
        return Extra;
    }

    public void setExtra(int extra) {
        Extra = extra;
    }

    public int getEvec() {
        return Evec;
    }

    public void setEvec(int evec) {
        Evec = evec;
    }

    public int getFattend() {
        return Fattend;
    }

    public void setFattend(int fattend) {
        Fattend = fattend;
    }

    public int getSala() {
        return Sala;
    }

    public void setSala(int sala) {
        Sala = sala;
    }

    public int getNpay() {
        return Npay;
    }

    public void setNpay(int npay) {
        Npay = npay;
    }

    @Override
    public String toString() {
        return "Pwage{" +
                "Sno='" + Sno + '\'' +
                ", Rdate=" + Rdate +
                ", Wsub=" + Wsub +
                ", Late=" + Late +
                ", Leave=" + Leave +
                ", Extra=" + Extra +
                ", Evec=" + Evec +
                ", Fattend=" + Fattend +
                ", Sala=" + Sala +
                ", Npay=" + Npay +
                '}';
    }
}
