package cj.netos.fsbank.plugin.FSBAEngine.bs;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.netos.fsbank.args.SeparateBillRuler;
import cj.netos.fsbank.bs.IFSBankSeparateBillRulerBS;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "fSBankSeparateBillBS")
public class FSBankSeparateBillRulerBS implements IFSBankSeparateBillRulerBS {
	@CjServiceRef(refByName = "mongodb.fsbank.home")
	ICube home;// 银行本身的信息均存在fsbank网盘下的home下，home管理平台所有金证银行信息，而交易放在金证银行对应的存储方案空间中。

	@Override
	public boolean hasRulerOfBank(String bank) {
		String where = String.format("{'tuple.bank':'%s'}", bank);
		return home.tupleCount(TABLE_NAME, where) > 0;
	}

	@Override
	public void saveRuler(SeparateBillRuler ruler) {
		home.saveDoc(TABLE_NAME, new TupleDocument<>(ruler));

	}

}
