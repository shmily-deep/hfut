package Bean;
//代扣款项表
public class Remit {
    private String Sno;     //工号
    private int Stax;       //个人所得税
    private int Sins;       //五险一金

    public Remit(String sno, int stax, int sins) {
        Sno = sno;
        Stax = stax;
        Sins = sins;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public int getStax() {
        return Stax;
    }

    public void setStax(int stax) {
        Stax = stax;
    }

    public int getSins() {
        return Sins;
    }

    public void setSins(int sins) {
        Sins = sins;
    }

    @Override
    public String toString() {
        return "Remit{" +
                "Sno='" + Sno + '\'' +
                ", Stax=" + Stax +
                ", Sins=" + Sins +
                '}';
    }
}
