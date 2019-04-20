package cj.netos.fsbank.program.stub;

import java.math.BigDecimal;
import java.util.List;

import cj.netos.fsbank.args.BState;
import cj.netos.fsbank.args.BankCompany;
import cj.netos.fsbank.args.BankInfo;
import cj.netos.fsbank.args.BankLicense;
import cj.netos.fsbank.args.BankPresident;
import cj.netos.fsbank.args.BankState;
import cj.netos.fsbank.args.SeparateBillRuler;
import cj.netos.fsbank.bs.IFSBankCompanyBS;
import cj.netos.fsbank.bs.IFSBankInfoBS;
import cj.netos.fsbank.bs.IFSBankLicenseBS;
import cj.netos.fsbank.bs.IFSBankPresidentBS;
import cj.netos.fsbank.bs.IFSBankSeparateBillRulerBS;
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

	@CjServiceRef(refByName = "FSBAEngine.fSBankLicenseBS")
	IFSBankLicenseBS fSBankLicenseBS;

	@CjServiceRef(refByName = "FSBAEngine.fSBankSeparateBillBS")
	IFSBankSeparateBillRulerBS fSBankSeparateBillBS;
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
		if (fSBankPresidentBS.hasPresidentOfBank(president.getBank())) {
			throw new CircuitException("500",
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
		if (fSBankCompanyBS.hasCompanyOfBank(company.getBank())) {
			throw new CircuitException("500", String.format("银行：%s 已存在企业", company.getBank()));
		}
		fSBankCompanyBS.saveCompany(company);
	}

	@Override
	public void setBankSeparateBillRule(SeparateBillRuler ruler) throws CircuitException {
		if (StringUtil.isEmpty(ruler.getBank())) {
			throw new CircuitException("404", String.format("未指定银行"));
		}
		if (ruler.getIssueBondRate() == null || new BigDecimal(0).compareTo(ruler.getIssueBondRate()) >= 0) {
			throw new CircuitException("404", String.format("发债金率未指定"));
		}
		if (ruler.getReserveRate() == null || new BigDecimal(0).compareTo(ruler.getReserveRate()) >= 0) {
			throw new CircuitException("404", String.format("准备金率未指定"));
		}
		if (ruler.getFreeMRate() == null || new BigDecimal(0).compareTo(ruler.getFreeMRate()) >= 0) {
			ruler.setFreeMRate(new BigDecimal(1).subtract(ruler.getIssueBondRate().add(ruler.getReserveRate())));
		}
		if(ruler.getFreeMRules().isEmpty()) {
			throw new CircuitException("404", String.format("自由金分配规则为空"));
		}
		if(ruler.totalFreeMRuleRate()!=1.0) {
			throw new CircuitException("404", String.format("自由金分配规则总为不为1"));
		}
		if (!fSBankInfoBS.existsBankCode(ruler.getBank())) {
			throw new CircuitException("404", String.format("银行代码：%s 不存在", ruler.getBank()));
		}
		if (fSBankSeparateBillBS.hasRulerOfBank(ruler.getBank())) {
			throw new CircuitException("500", String.format("银行：%s 已存在拆单规则", ruler.getBank()));
		}
		fSBankSeparateBillBS.saveRuler(ruler);
	}

	@Override
	public String issueBankLicense(String presidentPwd, BankLicense license) throws CircuitException {
		if (StringUtil.isEmpty(license.getBank())) {
			throw new CircuitException("404", String.format("未指定银行"));
		}
		if (StringUtil.isEmpty(license.getPresident())) {
			throw new CircuitException("404", String.format("未指定行长"));
		}
		if (StringUtil.isEmpty(license.getPresident())) {
			throw new CircuitException("404", String.format("未指定行行登录密码"));
		}
		long currTime = System.currentTimeMillis();
		if (license.getIssueDate() < currTime || license.getExpiryDate() < currTime) {
			throw new CircuitException("500", String.format("颁发日期或过期日期无效"));
		}
		if (!fSBankPresidentBS.hasPresidentOfBank(license.getBank())) {
			throw new CircuitException("404", String.format("银行资料不完善，银行：%s 还不存在行长", license.getBank()));
		}
		if (!StringUtil.isEmpty(license.getCompany()) && !fSBankCompanyBS.hasCompanyOfBank(license.getBank())) {
			throw new CircuitException("404", String.format("银行资料不完善，银行：%s 还没有归属企业", license.getBank()));
		}
		if (!fSBankSeparateBillBS.hasRulerOfBank(license.getBank())) {
			throw new CircuitException("404", String.format("资料不完整，银行：%s 没有拆单规则", license.getBank()));
		}
		fSBankLicenseBS.saveLicense(presidentPwd, license);
		return license.getCode();
	}
	@Override
	public SeparateBillRuler geBankSeparateBilltRuler(String bank) {
		return fSBankSeparateBillBS.getRuler(bank);
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
