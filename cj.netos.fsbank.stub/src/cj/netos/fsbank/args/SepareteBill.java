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
	BigDecimal rebateRate;//要求的返利率，返利率是相对于自由金率的比率
	BigDecimal rebateAmount;//返利金
	public BigDecimal getRebateRate() {
		return rebateRate;
	}
	public void setRebateRate(BigDecimal rebateRate) {
		this.rebateRate = rebateRate;
	}
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
	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}
	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}
	
}
