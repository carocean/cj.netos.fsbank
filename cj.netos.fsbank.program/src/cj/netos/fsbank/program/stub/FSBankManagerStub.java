package cj.netos.fsbank.program.stub;

import java.util.List;

import cj.netos.fsbank.args.BState;
import cj.netos.fsbank.args.BankCompany;
import cj.netos.fsbank.args.BankInfo;
import cj.netos.fsbank.args.BankLicense;
import cj.netos.fsbank.args.BankPresident;
import cj.netos.fsbank.args.BankState;
import cj.netos.fsbank.bs.IFSBankCompanyBS;
import cj.netos.fsbank.bs.IFSBankInfoBS;
import cj.netos.fsbank.bs.IFSBankPresidentBS;
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
	@CjServiceRef(refByName = "FSBAEngine.fSBankPresidentBS")
	IFSBankPresidentBS fSBankPresidentBS;
	@CjServiceRef(refByName = "FSBAEngine.fSBankCompanyBS")
	IFSBankCompanyBS fSBankCompanyBS;

	@Override
	public String registerBank(BankInfo info) throws CircuitException {
		if (StringUtil.isEmpty(info.getName())) {
			throw new CircuitException("404", String.format("No bank was filled in"));
		}
		info.setCode(null);
		info.setCtime(System.currentTimeMillis());
		BankState state = new BankState();
		state.setState(BState.normal);
		info.setBstate(state.getState());

		fSBankInfoBS.saveBank(info);
		// 插入营业状态为正常
		state.setBank(info.getCode());
		state.setCtime(System.currentTimeMillis());
		fSBankStateBS.save(state);
		return info.getCode();
	}

	@Override
	public void setPresident(BankPresident president) throws CircuitException {
		if (StringUtil.isEmpty(president.getBank())) {
			throw new CircuitException("404", String.format("No bank was filled in"));
		}
		if (StringUtil.isEmpty(president.getUserCode())) {
			throw new CircuitException("404", String.format("No userCode was filled in"));
		}
		if (StringUtil.isEmpty(president.getCellphone())) {
			throw new CircuitException("404", String.format("No cellphone was filled in"));
		}
		if (!fSBankInfoBS.existsBankCode(president.getBank())) {
			throw new CircuitException("404",
					String.format("Bank with specified code %s does not exist", president.getBank()));
		}
		if (fSBankPresidentBS.existsPresident(president.getBank())) {
			throw new CircuitException("404",
					String.format("president with specified president bank %s has existed", president.getBank()));
		}
		fSBankPresidentBS.savePresident(president);
	}

	@Override
	public void setCompany(BankCompany company) throws CircuitException {
		if (StringUtil.isEmpty(company.getBank())) {
			throw new CircuitException("404", String.format("未指定银行"));
		}
		if (StringUtil.isEmpty(company.getName())) {
			throw new CircuitException("404", String.format("未指定企业名"));
		}
		if (StringUtil.isEmpty(company.getCode())) {
			throw new CircuitException("404", String.format("未指定企业代码"));
		}
		if (StringUtil.isEmpty(company.getContact())) {
			throw new CircuitException("404", String.format("未指定联系方式"));
		}
		if (!fSBankInfoBS.existsBankCode(company.getBank())) {
			throw new CircuitException("404", String.format("银行代码：%s 不存在", company.getBank()));
		}
		if (fSBankCompanyBS.existsCompany(company.getBank())) {
			throw new CircuitException("404", String.format("银行：%s 已存在企业", company.getBank()));
		}
		fSBankCompanyBS.saveCompany(company);
	}

	@Override
	public void verifyBankIntegrity(String bankCode) throws CircuitException {
		throw new CircuitException("404", "我操你大爷");

	}

	@Override
	public void deregisterBank(String bankCode) {
		fSBankInfoBS.deregisterBank(bankCode);
	}

	@Override
	public void freezeBank(String bankCode) {
		fSBankInfoBS.freezeBank(bankCode);
	}

	@Override
	public void pauseBank(String bankCode) {
		fSBankInfoBS.pauseBank(bankCode);
	}

	@Override
	public void resumeBank(String bankCode) {
		fSBankInfoBS.resumeBank(bankCode);
	}

	@Override
	public BankInfo getBankInfo(String bankCode) {
		return fSBankInfoBS.getBankInfo(bankCode);
	}

	@Override
	public BankLicense getBankLicense(String bankCode) {
		return fSBankInfoBS.getBankLicense(bankCode);
	}

	@Override
	public List<BankInfo> pageBankInfo(int currPage, int pageSize) {
		return fSBankInfoBS.pageBankInfo(currPage, pageSize);
	}

	@Override
	public List<BankLicense> pageBankLicense(int currPage, int pageSize) {
		return fSBankInfoBS.pageBankLicense(currPage, pageSize);
	}

}