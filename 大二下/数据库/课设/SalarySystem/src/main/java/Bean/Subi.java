package Bean;
//津贴表
public class Subi {
    private String Type;    //津贴类型，餐补、交通补贴、住房补贴
    private int Sub;        //津贴金额

    public Subi(String type, int sub) {
        Type = type;
        Sub = sub;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getSub() {
        return Sub;
    }

    public void setSub(int sub) {
        Sub = sub;
    }

    @Override
    public String toString() {
        return "Subi{" +
                "Type='" + Type + '\'' +
                ", Sub=" + Sub +
                '}';
    }
}
