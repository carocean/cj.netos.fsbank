package cj.netos.fsbank.plugin.FSBAEngine.bs;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.netos.fsbank.args.BankCompany;
import cj.netos.fsbank.bs.IFSBankCompanyBS;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "fSBankCompanyBS")
public class FSBankCompanyBS implements IFSBankCompanyBS {
	@CjServiceRef(refByName = "mongodb.fsbank.home")
	ICube home;// 银行本身的信息均存在fsbank网盘下的home下，home管理平台所有金证银行信息，而交易放在金证银行对应的存储方案空间中。

	@Override
	public void saveCompany(BankCompany company) {
		home.saveDoc(TABLE_COMPANY, new TupleDocument<>(company));
	}

	@Override
	public boolean existsCompany(String bank) {//一个银行一个企业
		String where = String.format("{'tuple.bank':'%s'}", bank);
		return home.tupleCount(TABLE_COMPANY, where) > 0;
	}

}
