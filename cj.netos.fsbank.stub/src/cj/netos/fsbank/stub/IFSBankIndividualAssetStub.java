package cj.netos.fsbank.stub;

import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/asset/individualAccount.service", usage = "个人账户资产服务")
public interface IFSBankIndividualAssetStub {
	@CjStubMethod(usage = "存款笔数")
	void depositBillCount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "提现笔数")
	void cashoutBillCount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "cashoutor", usage = "提现人") String cashoutor,
			@CjStubInParameter(key = "identity", usage = "身份") String identity,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "承兑笔数")
	void exchangeBillCount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "exchanger", usage = "承兑人") String exchanger,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "存单分页")
	void pageDepositBill(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor,
			@CjStubInParameter(key = "currPage", usage = "当前页号") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "提现单分页")
	void pageCashoutBill(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "cashoutor", usage = "提现人") String cashoutor,
			@CjStubInParameter(key = "identity", usage = "身份") String identity,
			@CjStubInParameter(key = "currPage", usage = "当前页号") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "承兑单分页")
	void pageExchangeBill(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "exchanger", usage = "承兑人") String exchanger,
			@CjStubInParameter(key = "currPage", usage = "当前页号") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "提现单分页")
	void pageCashoutBillByIdentity(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "identity", usage = "提现者身份") String identity,
			@CjStubInParameter(key = "currPage", usage = "当前页号") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "存单总金额")
	void totalDepositBillAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "存单总债数")
	void totalDepositBondQuantities(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "存单总债金")
	void totalDepositBondAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "存单总准备金")
	void totalDepositReserveAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "存单总自由金")
	void totalDepositFreeAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "depositor", usage = "存款人") String depositor,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "总承兑债数")
	void totalExchangeBondQuantities(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "exchanger", usage = "承兑人") String exchanger,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "总承兑金")
	void totalExchangeBillAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "exchanger", usage = "承兑人") String exchanger,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "总承兑尾金")
	void totalExchangeTailAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "exchanger", usage = "承兑人") String exchanger,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "总提现请求金")
	void totalCashoutReqAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "cashoutor", usage = "提现人") String cashoutor,
			@CjStubInParameter(key = "identity", usage = "身份") String identity,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "总提现响应金")
	void totalCashoutResAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "cashoutor", usage = "提现人") String cashoutor,
			@CjStubInParameter(key = "identity", usage = "身份") String identity,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "总收续费")
	void totalCashoutPoundageAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "cashoutor", usage = "提现人") String cashoutor,
			@CjStubInParameter(key = "identity", usage = "身份") String identity,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计指定身份的总请求金")
	void totalCashoutReqAmountByIdentity(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "identity", usage = "身份") String identity,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计指定身份的总响应金")
	void totalCashoutResAmountByIdentity(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "identity", usage = "身份") String identity,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计指定身份的总手续费")
	void totalCashoutPoundageAmountByIdentity(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "identity", usage = "身份") String identity,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);
}
