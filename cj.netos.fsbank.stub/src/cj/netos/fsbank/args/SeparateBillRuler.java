package cj.netos.fsbank.args;

import java.math.BigDecimal;

public class SeparateBillRuler {
	BigDecimal bondRate;
	BigDecimal reserveRate;
	BigDecimal freeMRate;
	String bank;// 一个银行有且仅有一条拆单规则
	String bondKind;
	public SeparateBillRuler() {
	}
	public String getBondKind() {
		return bondKind;
	}
	public void setBondKind(String bondKind) {
		this.bondKind = bondKind;
	}
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public BigDecimal getBondRate() {
		return bondRate;
	}

	public void setBondRate(BigDecimal bondRate) {
		this.bondRate = bondRate;
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

}
