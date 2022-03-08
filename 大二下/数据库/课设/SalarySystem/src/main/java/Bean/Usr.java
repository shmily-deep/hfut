package Bean;
//帐号表
public class Usr {
    private String Sno;     //工号
    private String Pwd;     //密码
    private int Iden;    //身份

    public Usr(String sno, String pwd, int iden) {
        Sno = sno;
        Pwd = pwd;
        Iden = iden;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    public int getIden() {
        return Iden;
    }

    public void setIden(int iden) {
        Iden = iden;
    }

    @Override
    public String toString() {
        return "Usr{" +
                "Sno='" + Sno + '\'' +
                ", Pwd='" + Pwd + '\'' +
                ", Iden='" + Iden + '\'' +
                '}';
    }
}
