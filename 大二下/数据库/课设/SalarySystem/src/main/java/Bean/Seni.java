package Bean;
//工龄表
public class Seni {
    private byte Wage;  //工龄
    private int Wsub;   //补贴金额

    public Seni(byte wage, int wsub) {
        Wage = wage;
        Wsub = wsub;
    }

    public byte getWage() {
        return Wage;
    }

    public void setWage(byte wage) {
        Wage = wage;
    }

    public int getWsub() {
        return Wsub;
    }

    public void setWsub(int wsub) {
        Wsub = wsub;
    }

    @Override
    public String toString() {
        return "Seni{" +
                "Wage=" + Wage +
                ", Wsub=" + Wsub +
                '}';
    }
}
