package cj.netos.fsbank.stub;

import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubService;

//仅开发读操作，在读的时候可能已有变化，所以读出的永远都是旧的
@CjStubService(bindService = "/balance.service", usage = "余额服务")
public interface IFSBankBalanceStub {
	@CjStubMethod(usage = "加载余额")
	void loadBalance(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "返回债价")
	void getBondPrice(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "债券存量")
	void getBondQuantitiesBalance(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "冻结金余额")
	void getFreezeAmountBalance(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "自由金余额")
	void getFreeAmountBalance(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "尾金余额")
	void getTailAmountBalance(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "用户债券余额")
	void getIndividualBondBalance(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "user", usage = "用户") String user,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

}
