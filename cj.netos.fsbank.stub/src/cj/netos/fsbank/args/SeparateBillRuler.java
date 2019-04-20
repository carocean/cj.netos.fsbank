package cj.netos.fsbank.args;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SeparateBillRuler {
	BigDecimal issueBondRate;
	BigDecimal reserveRate;
	BigDecimal freeMRate;// 自由金率等于rules内各个收益分配率之和
	String bank;// 一个银行有且仅有一条拆单规则
	List<FreeMSharingRule> freeMRules;// 自由金规则总和

	public SeparateBillRuler() {
		freeMRules = new ArrayList<>();
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public BigDecimal getIssueBondRate() {
		return issueBondRate;
	}

	public void setIssueBondRate(BigDecimal issueBondRate) {
		this.issueBondRate = issueBondRate;
	}

	public BigDecimal getReserveRate() {
		return reserveRate;
	}

	public void setReserveRate(BigDecimal reserveRate) {
		this.reserveRate = reserveRate;
	}

	public BigDecimal getFreeMRate() {
		return freeMRate;
	}

	public void setFreeMRate(BigDecimal freeMRate) {
		this.freeMRate = freeMRate;
	}

	public List<FreeMSharingRule> getFreeMRules() {
		return freeMRules;
	}

	public double totalFreeMRuleRate() {
		BigDecimal total = new BigDecimal(0.0);
		for (FreeMSharingRule r : freeMRules) {
			total=total.add(r.getRate());
		}
		return total.doubleValue();
	}
}
