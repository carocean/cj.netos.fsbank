package cj.netos.fsbank.plugin.FSBAEngine.bs;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.netos.fsbank.args.BankPresident;
import cj.netos.fsbank.bs.IFSBankPresidentBS;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "fSBankPresidentBS")
public class FSBankPresidentBS implements IFSBankPresidentBS {
	@CjServiceRef(refByName = "mongodb.fsbank.home")
	ICube home;// 银行本身的信息均存在fsbank网盘下的home下，home管理平台所有金证银行信息，而交易放在金证银行对应的存储方案空间中。

	@Override
	public void savePresident(BankPresident president) {
		home.saveDoc(TABLE_PRESIDENT, new TupleDocument<>(president));
	}

	@Override
	public boolean hasPresidentOfBank(String bank) {//一个银行一个行长
		String where=String.format("{'tuple.bank':'%s'}", bank);
		return home.tupleCount(TABLE_PRESIDENT, where)>0;
	}

	

}
