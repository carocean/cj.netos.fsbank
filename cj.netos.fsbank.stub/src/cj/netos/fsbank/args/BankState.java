package cj.netos.fsbank.args;
/**
 * 营业状态
 * @author caroceanjofers
 *
 */
public class BankState {
	String id;
	BState state;
	String bank;
	long ctime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public BState getState() {
		return state;
	}
	public void setState(BState state) {
		this.state = state;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	
}
