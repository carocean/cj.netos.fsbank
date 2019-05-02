package cj.netos.fsbank.program.stub;

import cj.netos.fsbank.bs.IFSBankPropertiesBS;
import cj.netos.fsbank.stub.IFSBankPropertiesStub;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;

@CjService(name = "/properties.service")
public class FSBankPropertiesStub extends GatewayAppSiteRestStub implements IFSBankPropertiesStub {
	@CjServiceRef(refByName = "FSBAEngine.fSBankPropertiesBS")
	IFSBankPropertiesBS fSBankPropertiesBS;
	@Override
	public void put(String bank, String key, String value) {
		fSBankPropertiesBS.put(bank,key,value);
	}

	@Override
	public String get(String bank, String key) {
		return fSBankPropertiesBS.get(bank,key);
	}

	@Override
	public String[] enumKey(String bank) {
		return fSBankPropertiesBS.enumKey(bank);
	}

	@Override
	public String[] pageKeys(String bank, int currPage, int pageSize) {
		return fSBankPropertiesBS.pageKeys(bank,currPage,pageSize);
	}

	@Override
	public long count(String bank) {
		return fSBankPropertiesBS.count(bank);
	}
	@Override
	public void remove(String bank, String key) {
		 fSBankPropertiesBS.remove(bank, key);;
	}
}
