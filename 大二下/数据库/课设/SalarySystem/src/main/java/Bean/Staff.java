package Bean;


import java.sql.Date;
import java.text.SimpleDateFormat;

//员工表
public class Staff {
    private String Sno;         //工号
    private String Sname;       //姓名
    private String Ssex;        //性别
    private  short Sage;        //年龄
    private String Dname;       //部门名
    private String Spos;        //职位名
    private Date Sdate;         //入职日期
    private String Stel;        //电话号码
    private String Saddr;       //家庭住址
    private String Card;        //银行卡号

    public Staff(String sno, String sname, String ssex, short sage, String dname, String spos, Date sdate, String stel, String saddr, String card) {
        Sno = sno;
        Sname = sname;
        Ssex = ssex;
        Sage = sage;
        Dname = dname;
        Spos = spos;
        Sdate = sdate;
        Stel = stel;
        Saddr = saddr;
        Card = card;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    public short getSage() {
        return Sage;
    }

    public void setSage(short sage) {
        Sage = sage;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public String getSpos() {
        return Spos;
    }

    public void setSpos(String spos) {
        Spos = spos;
    }

    public Date getSdate() {
        return Sdate;
    }

    public void setSdate(Date sdate) {
        Sdate = sdate;
    }

    public String getStel() {
        return Stel;
    }

    public void setStel(String stel) {
        Stel = stel;
    }

    public String getSaddr() {
        return Saddr;
    }

    public void setSaddr(String saddr) {
        Saddr = saddr;
    }

    public String getCard() {
        return Card;
    }

    public void setCard(String card) {
        Card = card;
    }

    public static java.sql.Date strToDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "Sno='" + Sno + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Ssex='" + Ssex + '\'' +
                ", Sage=" + Sage +
                ", Dname='" + Dname + '\'' +
                ", Spos='" + Spos + '\'' +
                ", Sdate=" + Sdate +
                ", Stel='" + Stel + '\'' +
                ", Saddr='" + Saddr + '\'' +
                ", Card='" + Card + '\'' +
                '}';
    }
}
