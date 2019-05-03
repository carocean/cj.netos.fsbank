package cj.netos.fsbank.args;

import java.math.BigDecimal;

public class SepareteBill {
	BigDecimal bondRate;
	BigDecimal bondAmount;
	BigDecimal bondQuantities;
	BigDecimal reserveRate;
	BigDecimal reserveAmount;
	BigDecimal freeRate;
	BigDecimal freeAmount;
	public BigDecimal getBondRate() {
		return bondRate;
	}
	public void setBondRate(BigDecimal bondRate) {
		this.bondRate = bondRate;
	}
	public BigDecimal getBondAmount() {
		return bondAmount;
	}
	public void setBondAmount(BigDecimal bondAmount) {
		this.bondAmount = bondAmount;
	}
	public BigDecimal getBondQuantities() {
		return bondQuantities;
	}
	public void setBondQuantities(BigDecimal bondQuantities) {
		this.bondQuantities = bondQuantities;
	}
	public BigDecimal getReserveRate() {
		return reserveRate;
	}
	public void setReserveRate(BigDecimal reserveRate) {
		this.reserveRate = reserveRate;
	}
	public BigDecimal getReserveAmount() {
		return reserveAmount;
	}
	public void setReserveAmount(BigDecimal reserveAmount) {
		this.reserveAmount = reserveAmount;
	}
	public BigDecimal getFreeRate() {
		return freeRate;
	}
	public void setFreeRate(BigDecimal freeRate) {
		this.freeRate = freeRate;
	}
	public BigDecimal getFreeAmount() {
		return freeAmount;
	}
	public void setFreeAmount(BigDecimal freeAmount) {
		this.freeAmount = freeAmount;
	}
	
	
}
