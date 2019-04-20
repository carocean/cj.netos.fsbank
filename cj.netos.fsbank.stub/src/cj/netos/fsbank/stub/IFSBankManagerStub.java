package cj.netos.fsbank.stub;

import java.util.ArrayList;
import java.util.List;

import cj.netos.fsbank.args.BankCompany;
import cj.netos.fsbank.args.BankInfo;
import cj.netos.fsbank.args.BankLicense;
import cj.netos.fsbank.args.BankPresident;
import cj.netos.fsbank.args.SeparateBillRuler;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.stub.annotation.CjStubInContentKey;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubReturn;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/manager.service", usage = "金证银行管理服务")
public interface IFSBankManagerStub {
	@CjStubMethod(command = "post", usage = "注册银行，该方法仅是创建银行主体信息，稍后要调用其它方法以完善资料")
	@CjStubReturn(usage = "返回行号")
	String registerBank(@CjStubInContentKey(key = "info", usage = "银行信息，json") BankInfo info) throws CircuitException;

	@CjStubMethod(command = "post", usage = "设置行长")
	void setPresident(@CjStubInContentKey(key = "president", usage = "行长信息，json") BankPresident president)
			throws CircuitException;

	@CjStubMethod(command = "post", usage = "设置公司信息")
	void setCompany(@CjStubInContentKey(key = "company", usage = "公司信息，json") BankCompany company)
			throws CircuitException;

	@CjStubMethod(command = "post", usage = "颁发银行牌照")
	@CjStubReturn(usage = "执照标识")
	String issueBankLicense(
			@CjStubInParameter(key = "presidentPwd", usage = "行长密码，要在license内容中指定行长") String presidentPwd,
			@CjStubInContentKey(key = "license", usage = "银行照片，json") BankLicense license) throws CircuitException;

	@CjStubMethod(command = "post", usage = "设置拆单规则")
	void setBankSeparateBillRule(@CjStubInContentKey(key = "ruler", usage = "规则，json") SeparateBillRuler ruler) throws CircuitException;

	@CjStubMethod(usage = "吊销指定的银行，吊销并不是删除，只是改变状态为吊销")
	void deregisterBank(@CjStubInParameter(key = "bankCode", usage = "银行代码") String bankCode) throws CircuitException;

	@CjStubMethod(usage = "冻结指定的银行")
	void freezeBank(@CjStubInParameter(key = "bankCode", usage = "银行代码") String bankCode);

	@CjStubMethod(usage = "暂停运行指定的银行")
	void pauseBank(@CjStubInParameter(key = "bankCode", usage = "银行代码") String bankCode);

	@CjStubMethod(usage = "恢复运行指定的银行")
	void resumeBank(@CjStubInParameter(key = "bankCode", usage = "银行代码") String bankCode);

	@CjStubMethod(usage = "获取指定的银行信息")
	BankInfo getBankInfo(@CjStubInParameter(key = "bankCode", usage = "银行代码") String bankCode);

	@CjStubMethod(usage = "获取指定的银行信息")
	BankLicense getBankLicense(@CjStubInParameter(key = "bankCode", usage = "银行代码") String bankCode);

	@CjStubMethod(usage = "分页查询银行列表")
	@CjStubReturn(elementType = ArrayList.class, type = BankInfo.class, usage = "银行列表")
	List<BankInfo> pageBankInfo(@CjStubInParameter(key = "currPage", usage = "当前页码") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize);

	@CjStubMethod(usage = "分页查询银行列表")
	@CjStubReturn(elementType = ArrayList.class, type = BankLicense.class, usage = "营业执照列表")
	List<BankLicense> pageBankLicense(@CjStubInParameter(key = "currPage", usage = "当前页码") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize);

}
