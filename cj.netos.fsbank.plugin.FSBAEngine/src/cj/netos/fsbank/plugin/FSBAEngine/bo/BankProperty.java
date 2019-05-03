package cj.netos.fsbank.plugin.FSBAEngine.bo;

public class BankProperty {
	public final static transient String CONSTANS_KEY_ultimate_BondPrice = "ultimate_BondPrice";
	public final static transient String CONSTANS_KEY_BondKind = "bondKind";
	public final static transient String CONSTANS_KEY_Currency = "currency";
	public final static transient String CONSTANS_KEY_poundageRate = "poundageRate";
	public final static transient String CONSTANS_KEY_bigDecimal_scale = "bigDecimal.scale";
	public final static transient String CONSTANS_KEY_bigDecimal_roundingMode = "bigDecimal.roundingMode";
	public final static transient String CONSTANS_KEY_Rule_bondRate = "rule.bondRate";
	public final static transient String CONSTANS_KEY_Rule_freeRate = "rule.freeMRate";
	public final static transient String CONSTANS_KEY_Rule_reserveRate = "rule.reserveRate";
	String key;
	String value;
	String desc;
	String bank;

	public BankProperty() {
		// TODO Auto-generated constructor stub
	}

	public BankProperty(String bank, String key, String value,String desc) {
		this.bank = bank;
		this.key = key;
		this.value = value;
		this.desc=desc;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
}
