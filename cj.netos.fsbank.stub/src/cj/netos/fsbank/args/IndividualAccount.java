package cj.netos.fsbank.args;

import java.math.BigDecimal;

public class IndividualAccount {
	String user;
	BigDecimal boudBalance;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public BigDecimal getBoudBalance() {
		return boudBalance;
	}

	public void setBoudBalance(BigDecimal boudBalance) {
		this.boudBalance = boudBalance;
	}

}
