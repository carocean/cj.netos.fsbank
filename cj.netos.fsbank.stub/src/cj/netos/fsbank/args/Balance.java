package cj.netos.fsbank.args;

import java.math.BigDecimal;

public class Balance {
	BigDecimal bondPrice;
	BigDecimal bondQuantities;
	String bondKind;
	String currency;
	BigDecimal reserveAmount;
	BigDecimal bondAmount;
	BigDecimal freeAmount;
	BigDecimal tailAmount;
	public BigDecimal getBondPrice() {
		return bondPrice;
	}
	public void setBondPrice(BigDecimal bondPrice) {
		this.bondPrice = bondPrice;
	}
	public BigDecimal getBondQuantities() {
		return bondQuantities;
	}
	public void setBondQuantities(BigDecimal bondQuantities) {
		this.bondQuantities = bondQuantities;
	}
	public String getBondKind() {
		return bondKind;
	}
	public void setBondKind(String bondKind) {
		this.bondKind = bondKind;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getReserveAmount() {
		return reserveAmount;
	}
	public void setReserveAmount(BigDecimal reserveAmount) {
		this.reserveAmount = reserveAmount;
	}
	public BigDecimal getBondAmount() {
		return bondAmount;
	}
	public void setBondAmount(BigDecimal bondAmount) {
		this.bondAmount = bondAmount;
	}
	public BigDecimal getFreeAmount() {
		return freeAmount;
	}
	public void setFreeAmount(BigDecimal freeAmount) {
		this.freeAmount = freeAmount;
	}
	public BigDecimal getTailAmount() {
		return tailAmount;
	}
	public void setTailAmount(BigDecimal tailAmount) {
		this.tailAmount = tailAmount;
	}
	
	
}
