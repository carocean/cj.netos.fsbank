package cj.netos.fsbank.plugin.FSBAEngine.bs;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.netos.fsbank.args.BankState;
import cj.netos.fsbank.bs.IFSBankStateBS;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "fSBankStateBS")
public class FSBankStateBS implements IFSBankStateBS {
	@CjServiceRef(refByName = "mongodb.fsbank.home")
	ICube home;// 银行本身的信息均存在fsbank网盘下的home下，home管理平台所有金证银行信息，而交易放在金证银行对应的存储方案空间中。

	@Override
	public void save(BankState state) {
		state.setId(null);
		String id = home.saveDoc(TABLE_BANK_STATE, new TupleDocument<>(state));
		state.setId(id);
	}

}
