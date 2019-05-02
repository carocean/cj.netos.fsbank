package cj.netos.fsbank.args;

import java.math.BigDecimal;

public class CashoutBill {
	String code;
	String cashoutor;
	String identity;
	BigDecimal reqAmount;
	BigDecimal resAmount;//reqAmount=resAmount+poundageAmount
	long ctime;
	BigDecimal poundageRate;//手续费
	BigDecimal poundageAmount;
	BigDecimal balance;//余额，与balanceType有关
	String memo;
	String balanceType;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCashoutor() {
		return cashoutor;
	}
	public void setCashoutor(String cashoutor) {
		this.cashoutor = cashoutor;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	public BigDecimal getPoundageRate() {
		return poundageRate;
	}
	public void setPoundageRate(BigDecimal poundageRate) {
		this.poundageRate = poundageRate;
	}
	public BigDecimal getPoundageAmount() {
		return poundageAmount;
	}
	public void setPoundageAmount(BigDecimal poundageAmount) {
		this.poundageAmount = poundageAmount;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public BigDecimal getReqAmount() {
		return reqAmount;
	}
	public void setReqAmount(BigDecimal reqAmount) {
		this.reqAmount = reqAmount;
	}
	public BigDecimal getResAmount() {
		return resAmount;
	}
	public void setResAmount(BigDecimal resAmount) {
		this.resAmount = resAmount;
	}
	
}
