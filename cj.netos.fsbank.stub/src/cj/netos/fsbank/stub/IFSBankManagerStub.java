package cj.netos.fsbank.stub;

import java.util.ArrayList;
import java.util.List;

import cj.netos.fsbank.args.BankInfo;
import cj.netos.fsbank.args.BankLicense;
import cj.netos.fsbank.args.BankPresident;
import cj.netos.fsbank.args.SeparateBillRuler;
import cj.studio.gateway.stub.annotation.CjStubInContentKey;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubReturn;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/manager.service", usage = "金证银行管理服务")
public interface IFSBankManagerStub {
	@CjStubMethod(command = "post", usage = "银行注册信息")
	BankLicense registerBank(@CjStubInContentKey(key = "president", usage = "银行行长") BankPresident president,
			@CjStubInParameter(key = "validTerm", usage = "有效期") long validTerm,
			@CjStubInContentKey(key = "ruler", usage = "拆单规则，一个银行的拆单规则一旦设定下来就永久不能变") SeparateBillRuler ruler);

	@CjStubMethod(usage = "吊销指定的银行，吊销并不是删除，只是改变状态为吊销")
	void unregisterBank(@CjStubInParameter(key = "bankCode", usage = "银行代码") String bankCode);

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
