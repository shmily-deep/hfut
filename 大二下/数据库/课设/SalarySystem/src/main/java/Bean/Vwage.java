package Bean;

import java.sql.Date;

public class Vwage {
	private String Sno;
	private java.sql.Date Rdate;
	private int Bsalary;//基本工资
	private int Wsub;//工龄补贴
	private int Msub;//餐补
	private int Tsub;
	private int Hsub;
	private int Late;//迟到
	private int Leave;//早退
	private int Evec;//出差补贴
	private int Fattend;//全勤奖
	private int Extra;//加班
	private int Stax;
	private int Sins;
	private int Sala;//应发工资
	private int Npay;//实发工资

	public Vwage(String sno, Date rdate, int bsalary, int wsub, int msub, int tsub, int hsub, int late, int leave, int evec, int fattend, int extra, int stax, int sins, int sala, int npay) {
		Sno = sno;
		Rdate = rdate;
		Bsalary = bsalary;
		Wsub = wsub;
		Msub = msub;
		Tsub = tsub;
		Hsub = hsub;
		Late = late;
		Leave = leave;
		Evec = evec;
		Fattend = fattend;
		Extra = extra;
		Stax = stax;
		Sins = sins;
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

	public int getBsalary() {
		return Bsalary;
	}

	public void setBsalary(int bsalary) {
		Bsalary = bsalary;
	}

	public int getMsub() {
		return Msub;
	}

	public void setMsub(int msub) {
		Msub = msub;
	}

	public int getTsub() {
		return Tsub;
	}

	public void setTsub(int tsub) {
		Tsub = tsub;
	}

	public int getHsub() {
		return Hsub;
	}

	public void setHsub(int hsub) {
		Hsub = hsub;
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
		return "Vwage{" +
				"Sno='" + Sno + '\'' +
				", Rdate=" + Rdate +
				", Bsalary=" + Bsalary +
				", Wsub=" + Wsub +
				", Msub=" + Msub +
				", Tsub=" + Tsub +
				", Hsub=" + Hsub +
				", Late=" + Late +
				", Leave=" + Leave +
				", Evec=" + Evec +
				", Fattend=" + Fattend +
				", Extra=" + Extra +
				", Stax=" + Stax +
				", Sins=" + Sins +
				", Sala=" + Sala +
				", Npay=" + Npay +
				'}';
	}
}
