package cj.netos.fsbank.program.stub;

import java.util.List;

import cj.netos.fsbank.args.BState;
import cj.netos.fsbank.args.BankInfo;
import cj.netos.fsbank.args.BankLicense;
import cj.netos.fsbank.args.BankState;
import cj.netos.fsbank.bs.IFSBankInfoBS;
import cj.netos.fsbank.bs.IFSBankLicenseBS;
import cj.netos.fsbank.bs.IFSBankStateBS;
import cj.netos.fsbank.stub.IFSBankManagerStub;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.ultimate.util.StringUtil;

@CjService(name = "/manager.service")
public class FSBankManagerStub extends GatewayAppSiteRestStub implements IFSBankManagerStub {
	@CjServiceRef(refByName = "FSBAEngine.fSBankInfoBS")
	IFSBankInfoBS fSBankInfoBS;

	@CjServiceRef(refByName = "FSBAEngine.fSBankStateBS")
	IFSBankStateBS fSBankStateBS;

	@CjServiceRef(refByName = "FSBAEngine.fSBankLicenseBS")
	IFSBankLicenseBS fSBankLicenseBS;

	@Override
	public String registerBank(String bankName, String president, String company) throws CircuitException {
		if (StringUtil.isEmpty(bankName)) {
			throw new CircuitException("404", String.format("银行名为空"));
		}
		if (StringUtil.isEmpty(president)) {
			throw new CircuitException("404", String.format("行长为空"));
		}
		BankInfo info = new BankInfo();
		info.setCode(null);
		info.setPresident(president);
		info.setCompany(company);
		info.setName(bankName);
		info.setCtime(System.currentTimeMillis());
		BankState state = new BankState();
		state.setState(BState.opened);
		info.setBstate(state.getState());

		fSBankInfoBS.saveBank(info);
		// 插入营业状态为正常
		state.setBank(info.getCode());
		state.setCtime(System.currentTimeMillis());
		fSBankStateBS.save(state);
		return info.getCode();
	}

	@Override
	public String issueBankLicense(String bank, long issueDate, long expiryDate, String presidentPwd)
			throws CircuitException {
		BankInfo info = fSBankInfoBS.getBankInfo(bank);
		if (info == null) {
			throw new CircuitException("404", String.format("银行不存在：" + bank));
		}
		if (StringUtil.isEmpty(info.getPresident())) {
			throw new CircuitException("404", String.format("未指定行长"));
		}
		if (StringUtil.isEmpty(presidentPwd)) {
			throw new CircuitException("404", String.format("未指定行行登录密码"));
		}
		long currTime = System.currentTimeMillis();
		if (issueDate < currTime || expiryDate < currTime) {
			throw new CircuitException("500", String.format("颁发日期或过期日期无效"));
		}
		BankLicense license = new BankLicense();
		license.setBank(bank);
		license.setCompany(info.getCompany());
		license.setCtime(currTime);
		license.setExpiryDate(expiryDate);
		license.setIssueDate(issueDate);
		license.setPresident(info.getPresident());
		fSBankLicenseBS.saveLicense(presidentPwd, license);
		return license.getCode();
	}

	@Override
	public void revokeBank(String bankCode) {
		fSBankStateBS.revokeBank(bankCode);
	}

	@Override
	public void freezeBank(String bankCode) {
		fSBankStateBS.freezeBank(bankCode);
	}

	@Override
	public void closedBank(String bankCode) {
		fSBankStateBS.closedBank(bankCode);
	}

	@Override
	public void resumeBank(String bankCode) {
		fSBankStateBS.resumeBank(bankCode);
	}

	@Override
	public BankInfo getBankInfo(String bankCode) {
		return fSBankInfoBS.getBankInfo(bankCode);
	}

	@Override
	public List<BankInfo> pageBankInfo(int currPage, int pageSize) {
		return fSBankInfoBS.pageBankInfo(currPage, pageSize);
	}

	@Override
	public BankLicense getBankLicense(String bankCode) {
		return fSBankLicenseBS.getBankLicense(bankCode);
	}

	@Override
	public List<BankLicense> pageBankLicense(int currPage, int pageSize) {
		return fSBankLicenseBS.pageBankLicense(currPage, pageSize);
	}

	@Override
	public void updateBankName(String bank, String name) throws CircuitException {
		fSBankInfoBS.updateBankName(bank,name);
	}

	@Override
	public void updateBankPresident(String bank, String president) throws CircuitException {
		fSBankInfoBS.updateBankPresident(bank,president);
	}

	@Override
	public void updateBankCompany(String bank, String company) throws CircuitException {
		fSBankInfoBS.updateBankCompany(bank,company);
	}
	
	@Override
	public BState getBankState(String bankCode) {
		return fSBankStateBS.getState(bankCode).getState();
	}
}
