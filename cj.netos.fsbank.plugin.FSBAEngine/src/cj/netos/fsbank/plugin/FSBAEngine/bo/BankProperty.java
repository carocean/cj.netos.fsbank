package cj.netos.fsbank.plugin.FSBAEngine.bo;

public class BankProperty {
	public final static transient String CONSTANS_KEY_poundageRate = "poundageRate";
	public final static transient String CONSTANS_KEY_bigDecimal_scale = "bigDecimal.scale";
	public final static transient String CONSTANS_KEY_bigDecimal_roundingMode = "bigDecimal.roundingMode";
	public final static transient String CONSTANS_KEY_bigDecimal_setPrecision = "bigDecimal.setPrecision";

	String key;
	String value;
	String bank;

	public BankProperty() {
		// TODO Auto-generated constructor stub
	}

	public BankProperty(String bank, String key, String value) {
		this.bank = bank;
		this.key = key;
		this.value = value;
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

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
}
