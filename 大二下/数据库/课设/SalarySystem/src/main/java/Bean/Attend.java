package Bean;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Attend {
    private String Sno;
    private java.sql.Date Adate;
    private byte Nlate;
    private byte Nleave;
    private byte Nevec;
    private byte Nextra;
    private byte Nattend;

    public Attend(String sno, Date adate, byte nlate, byte nleave, byte nevec, byte nextra, byte nattend) {
        Sno = sno;
        Adate = adate;
        Nlate = nlate;
        Nleave = nleave;
        Nevec = nevec;
        Nextra = nextra;
        Nattend = nattend;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getMonth() {
        String str = Adate.toString();
        str = str.substring(0, str.length() - 3);
        return str;
    }

    public Date getAdate() {
        return Adate;
    }

    public void setAdate(Date adate) {
        Adate = adate;
    }

    public byte getNlate() {
        return Nlate;
    }

    public void setNlate(byte nlate) {
        Nlate = nlate;
    }

    public byte getNleave() {
        return Nleave;
    }

    public void setNleave(byte nleave) {
        Nleave = nleave;
    }

    public byte getNevec() {
        return Nevec;
    }

    public void setNevec(byte nevec) {
        Nevec = nevec;
    }

    public byte getNextra() {
        return Nextra;
    }

    public void setNextra(byte nextra) {
        Nextra = nextra;
    }

    public byte getNattend() {
        return Nattend;
    }

    public void setNattend(byte nattend) {
        Nattend = nattend;
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
        return "Attend{" +
                "Sno='" + Sno + '\'' +
                ", Adate=" + Adate +
                ", Nlate=" + Nlate +
                ", Nleave=" + Nleave +
                ", Nevec=" + Nevec +
                ", Nextra=" + Nextra +
                ", Nattend=" + Nattend +
                '}';
    }
}
