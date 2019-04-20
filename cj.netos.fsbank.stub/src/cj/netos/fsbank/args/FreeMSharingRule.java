package cj.netos.fsbank.args;

import java.math.BigDecimal;

public class FreeMSharingRule {
	String code;
	String name;// 分红的名目
	BigDecimal rate;// 分红率

	public FreeMSharingRule() {
	}

	public FreeMSharingRule(String code, String name, BigDecimal rate) {
		super();
		this.code = code;
		this.name = name;
		this.rate = rate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}
