package cj.netos.fsbank.stub;

import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/asset/bank.service", usage = "银行资产服务")
public interface IBankAssetStub {
	@CjStubMethod(usage = "存单总数")
	void depositBillCount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "提现单总数")
	void cashBillCount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "承兑单总数")
	void exchangeBillCount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "存单分页")
	void pageDepositBill(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "currPage", usage = "当前分页") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "分页大小") int pageSize,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "提现单分页")
	void pageCashoutBill(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "currPage", usage = "当前分页") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "分页大小") int pageSize,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "承兑单分页")
	void pageExchangeBill(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "currPage", usage = "当前分页") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "分页大小") int pageSize,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计银行总存入金额")
	void totalDepositBillAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计银行总发债量")
	void totalDepositBondQuantities(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计银行总发债金额")
	void totalDepositBondAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计银行总准备金额")
	void totalDepositReserveAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计银行总自由金额")
	void totalDepositFreeAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计银行总承兑的债量")
	void totalExchangeBondQuantities(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计银行总承兑的金额")
	void totalExchangeBillAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计银行总尾金金额")
	void totalExchangeTailAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计银行总请求提现金额")
	void totalCashoutReqAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计银行总实际晌应金额")
	void totalCashoutResAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);

	@CjStubMethod(usage = "统计银行总手续费收取金额")
	void totalCashoutPoundageAmount(@CjStubInParameter(key = "bank", usage = "银行") String bank,
			@CjStubInParameter(key = "informAddress", usage = "回调通知地址，回调地址的查询串中的参数&分隔符请务必使用%26此为&的basic64编码，否则会冲突") String informAddress);
}
