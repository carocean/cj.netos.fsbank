package cj.netos.fsbank.program.stub;

import java.util.List;

import cj.netos.fsbank.args.BankInfo;
import cj.netos.fsbank.args.BankLicense;
import cj.netos.fsbank.args.BankPresident;
import cj.netos.fsbank.args.SeparateBillRuler;
import cj.netos.fsbank.stub.IFSBankManagerStub;
import cj.studio.ecm.annotation.CjService;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;

@CjService(name = "/manager.service")
public class FSBankManagerStub extends GatewayAppSiteRestStub implements IFSBankManagerStub {

	@Override
	public BankLicense registerBank(BankPresident president, long validTerm,SeparateBillRuler ruler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unregisterBank(String bankCode) {
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
		// TODO Auto-generated method stub
		return null;
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
