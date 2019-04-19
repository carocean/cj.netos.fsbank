package cj.netos.fsbank.args;

public class BankInfo {
	String code;
	String name;
	BState bstate;//银行运行状态：暂停，运行中
	long ctime;
	public BankInfo() {
	}
	public BankInfo(String code, String name, BState bstate,  long ctime) {
		super();
		this.code = code;
		this.name = name;
		this.bstate = bstate;
		this.ctime = ctime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BState getBstate() {
		return bstate;
	}
	public void setBstate(BState bstate) {
		this.bstate = bstate;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	
}
