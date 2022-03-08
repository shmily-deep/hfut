package Bean;
//基本工资表
public class Bwage {
    private String Spos;    //职位名
    private int Bsalary;    //基本工资

    public Bwage(String spos, int bsalary) {
        Spos = spos;
        Bsalary = bsalary;
    }

    public String getSpos() {
        return Spos;
    }

    public void setSpos(String spos) {
        Spos = spos;
    }

    public int getBsalary() {
        return Bsalary;
    }

    public void setBsalary(int bsalary) {
        Bsalary = bsalary;
    }

    @Override
    public String toString() {
        return "Bwage{" +
                "Spos='" + Spos + '\'' +
                ", Bsalary=" + Bsalary +
                '}';
    }
}
