package cj.netos.fsbank.plugin.FSBAEngine.bs;

import java.util.List;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.netos.fsbank.args.BankInfo;
import cj.netos.fsbank.args.BankLicense;
import cj.netos.fsbank.bs.IFSBankInfoBS;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;

@CjService(name = "fSBankInfoBS")
public class FSBankInfoBS implements IFSBankInfoBS {
	@CjServiceRef(refByName = "mongodb.fsbank.home")
	ICube home;// 银行本身的信息均存在fsbank网盘下的home下，home管理平台所有金证银行信息，而交易放在金证银行对应的存储方案空间中。

	@Override
	public boolean existsBankName(String name) {
		String where = String.format("{'tuple.name':'%s'}", name);
		return home.tupleCount(TABLE_BANK_INFO, where) > 0;
	}

	@Override
	public void saveBank(BankInfo info) {
		info.setCode(null);
		String id = home.saveDoc(TABLE_BANK_INFO, new TupleDocument<>(info));
		info.setCode(id);
	}

	@Override
	public boolean existsBankCode(String bank) {
		String where = String.format("{'_id':ObjectId('%s')}", bank);
		return home.tupleCount(TABLE_BANK_INFO, where) > 0;
	}

	@Override
	public void deregisterBank(String bankCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void freezeBank(String bankCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pauseBank(String bankCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resumeBank(String bankCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public BankInfo getBankInfo(String bankCode) {
		String cjql = String.format("select {'tuple':'*'} from tuple %s %s where {'_id':ObjectId('%s')}", TABLE_BANK_INFO,
				BankInfo.class.getName(),bankCode);
		IQuery<BankInfo> q = home.createQuery(cjql);
		IDocument<BankInfo> doc = q.getSingleResult();
		if (doc == null)
			return null;
		doc.tuple().setCode(doc.docid());
		return doc.tuple();
	}

	@Override
	public BankLicense getBankLicense(String bankCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankInfo> pageBankInfo(int currPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankLicense> pageBankLicense(int currPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
