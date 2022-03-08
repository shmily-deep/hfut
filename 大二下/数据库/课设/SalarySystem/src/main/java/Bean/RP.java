package Bean;
//奖罚金表
public class RP {
    private String RPtype;  //奖罚金类型，迟到、早退、出差、全勤、加班、
    private int RPmoney;    //奖罚金金额

    public RP(String RPtype, int RPmoney) {
        this.RPtype = RPtype;
        this.RPmoney = RPmoney;
    }

    public String getRPtype() {
        return RPtype;
    }

    public void setRPtype(String RPtype) {
        this.RPtype = RPtype;
    }

    public int getRPmoney() {
        return RPmoney;
    }

    public void setRPmoney(int RPmoney) {
        this.RPmoney = RPmoney;
    }

    @Override
    public String toString() {
        return "RP{" +
                "RPtype='" + RPtype + '\'' +
                ", RPmoney=" + RPmoney +
                '}';
    }
}
