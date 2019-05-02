package cj.netos.fsbank.args;

import java.math.BigDecimal;

public class ExchangeBill {
	String code;
	String exchanger;
	String bondKind;
	BigDecimal bondQuantities;
	BigDecimal currBondPrice;
	BigDecimal newBondPrice;
	BigDecimal deservedAmount;
	long etime;
	BigDecimal tailAmount;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getExchanger() {
		return exchanger;
	}
	public void setExchanger(String exchanger) {
		this.exchanger = exchanger;
	}
	public String getBondKind() {
		return bondKind;
	}
	public void setBondKind(String bondKind) {
		this.bondKind = bondKind;
	}
	public BigDecimal getBondQuantities() {
		return bondQuantities;
	}
	public void setBondQuantities(BigDecimal bondQuantities) {
		this.bondQuantities = bondQuantities;
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
	public BigDecimal getDeservedAmount() {
		return deservedAmount;
	}
	public void setDeservedAmount(BigDecimal deservedAmount) {
		this.deservedAmount = deservedAmount;
	}
	public long getEtime() {
		return etime;
	}
	public void setEtime(long etime) {
		this.etime = etime;
	}
	public BigDecimal getTailAmount() {
		return tailAmount;
	}
	public void setTailAmount(BigDecimal tailAmount) {
		this.tailAmount = tailAmount;
	}
	
	
}
