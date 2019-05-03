package cj.netos.fsbank.args;

import java.math.BigDecimal;

public class DepositBill {
	String code;
	String depositor;
	BigDecimal amount;
	BigDecimal currBondPrice;
	BigDecimal newBondPrice;
	long dtime;
	BigDecimal tailAmount;
	SepareteBill separeteBill;
	public DepositBill() {
		
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDepositor() {
		return depositor;
	}
	public void setDepositor(String depositor) {
		this.depositor = depositor;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getCurrBondPrice() {
		return currBondPrice;
	}
	public void setCurrBondPrice(BigDecimal currBondPrice) {
		this.currBondPrice = currBondPrice;
	}
	public BigDecimal getNewBondPrice() {
		return newBondPrice;
	}
	public void setNewBondPrice(BigDecimal newBondPrice) {
		this.newBondPrice = newBondPrice;
	}
	public long getDtime() {
		return dtime;
	}
	public void setDtime(long dtime) {
		this.dtime = dtime;
	}
	public BigDecimal getTailAmount() {
		return tailAmount;
	}
	public void setTailAmount(BigDecimal tailAmount) {
		this.tailAmount = tailAmount;
	}
	public SepareteBill getSepareteBill() {
		return separeteBill;
	}
	public void setSepareteBill(SepareteBill separeteBill) {
		this.separeteBill = separeteBill;
	}
}
