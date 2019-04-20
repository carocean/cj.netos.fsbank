package cj.netos.fsbank.plugin.FSBAEngine.bs;

import java.util.HashMap;
import java.util.Map;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.netos.fsbank.args.BankLicense;
import cj.netos.fsbank.bs.IFSBankLicenseBS;
import cj.studio.backend.uc.service.AuthenticationException;
import cj.studio.backend.uc.stub.ITenantTokenStub;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.stub.annotation.CjStubRef;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.gson2.com.google.gson.reflect.TypeToken;
@CjBridge(aspects = "@rest")
@CjService(name = "fSBankLicenseBS")
public class FSBankLicenseBS implements IFSBankLicenseBS {
	@CjServiceRef(refByName = "mongodb.fsbank.home")
	ICube home;// 银行本身的信息均存在fsbank网盘下的home下，home管理平台所有金证银行信息，而交易放在金证银行对应的存储方案空间中。

	@CjStubRef(remote = "rest://backend/uc/", stub = ITenantTokenStub.class)
	ITenantTokenStub tenantTokenStub;

	@Override
	public void saveLicense(String presidentPwd,BankLicense license) throws CircuitException{
		license.setCode(null);
		// 产生token
		String json = "";
		try {
			json = tenantTokenStub.genToken("netos.fsbank", license.getPresident(), presidentPwd,
					license.getExpiryDate());
		} catch (AuthenticationException e) {
			throw new CircuitException(e.getStatus(),e);
		}
		Map<String,String> map=new Gson().fromJson(json, new TypeToken<HashMap<String,String>>(){}.getType());
		if(!"200".equals(map.get("status"))) {
			throw new CircuitException(map.get("status"),"uc消息："+map.get("message"));
		}
		license.setToken(map.get("result"));
		String id = home.saveDoc(TABLE_LISENCE, new TupleDocument<>(license));
		license.setCode(id);
	}

	@Override
	public boolean hasLicenseOfBank(String bank) {// 一个银行一个企业
		String where = String.format("{'tuple.bank':'%s'}", bank);
		return home.tupleCount(TABLE_LISENCE, where) > 0;
	}

}
