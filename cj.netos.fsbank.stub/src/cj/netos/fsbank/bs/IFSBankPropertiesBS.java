package cj.netos.fsbank.bs;

public interface IFSBankPropertiesBS {
	static String TABLE_KEY="properties";
	void put(String bank, String key, String value);

	void remove(String bank, String key);

	boolean containsKey(String bank, String key);

	String get(String bank, String key);

	String[] enumKey(String bank);

	String[] pageKeys(String bank, int currPage, int pageSize);

	long count(String bank);

}
