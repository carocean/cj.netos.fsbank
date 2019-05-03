package cj.netos.fsbank.args;

import java.math.BigDecimal;

public class Balance {
	BigDecimal bondPrice;
	BigDecimal bondQuantities;
	BigDecimal freezeAmount;
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

	public BigDecimal getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(BigDecimal freezeAmount) {
		this.freezeAmount = freezeAmount;
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
