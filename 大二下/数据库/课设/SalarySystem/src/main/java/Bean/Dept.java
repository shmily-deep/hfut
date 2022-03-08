package Bean;
//部门表
public class Dept {
    private String Dname;   //部门名
    private String Manger;  //部门经理
    private int Tnum;       //总人数

    public Dept(String dname, String manger, int tnum) {
        Dname = dname;
        Manger = manger;
        Tnum = tnum;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public String getManger() {
        return Manger;
    }

    public void setManger(String manger) {
        Manger = manger;
    }

    public int getTnum() {
        return Tnum;
    }

    public void setTnum(int tnum) {
        Tnum = tnum;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "Dname='" + Dname + '\'' +
                ", Manger='" + Manger + '\'' +
                ", Tnum=" + Tnum +
                '}';
    }
}
