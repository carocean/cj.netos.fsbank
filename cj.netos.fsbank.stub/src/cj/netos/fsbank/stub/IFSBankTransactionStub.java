package cj.netos.fsbank.stub;

import cj.netos.fsbank.args.CashoutBill;
import cj.netos.fsbank.args.DepositBill;
import cj.studio.gateway.stub.annotation.CjStubInContentKey;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/transaction.service", usage = "金证银行交易服务")
public interface IFSBankTransactionStub {
	@CjStubMethod(command = "post", usage = "存款，存款即投资")
	void deposit(@CjStubInParameter(key = "bank",usage = "银行标识")String bank,@CjStubInContentKey(key = "bill", usage = "存款单") DepositBill bill);

	@CjStubMethod(command = "post", usage = "提现")
	void cashout(@CjStubInParameter(key = "bank",usage = "银行标识")String bank,@CjStubInContentKey(key = "bill", usage = "提现单") CashoutBill bill);
}
