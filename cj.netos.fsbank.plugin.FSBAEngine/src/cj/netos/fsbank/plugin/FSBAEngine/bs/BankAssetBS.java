package cj.netos.fsbank.plugin.FSBAEngine.bs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.AggregateIterable;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.netos.fsbank.args.CashoutBill;
import cj.netos.fsbank.args.DepositBill;
import cj.netos.fsbank.args.ExchangeBill;
import cj.netos.fsbank.bs.IBankAssetBS;
import cj.netos.fsbank.bs.IFSBankPropertiesBS;
import cj.netos.fsbank.bs.IFSBankTransactionBS;
import cj.netos.fsbank.plugin.FSBAEngine.util.BigDecimalConstants;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.annotation.CjServiceSite;

@CjService(name = "bankAssetBS")
public class BankAssetBS implements IBankAssetBS, BigDecimalConstants {
	@CjServiceSite
	IServiceSite site;
	ICube cubeBank;
	@CjServiceRef
	IFSBankPropertiesBS fSBankPropertiesBS;

	private ICube getBankCube(String bank) {
		if (cubeBank != null) {
			return cubeBank;
		}
		cubeBank = (ICube) site.getService("mongodb.fsbank." + bank + ":autocreate");
		return cubeBank;
	}

	@Override
	public long depositBillCount(String bank) {
		return getBankCube(bank).tupleCount(IFSBankTransactionBS.TABLE_Deposits);
	}

	@Override
	public long cashBillCount(String bank) {
		return getBankCube(bank).tupleCount(IFSBankTransactionBS.TABLE_Cashouts);
	}

	@Override
	public long exchangeBillCount(String bank) {
		return getBankCube(bank).tupleCount(IFSBankTransactionBS.TABLE_Exchanges);
	}

	@Override
	public List<DepositBill> pageDepositBill(String bank, int currPage, int pageSize) {
		String cjql = String.format("select {'tuple':'*'}.limit(%s).skip(%s) from tuple %s %s where {}", pageSize,
				currPage, IFSBankTransactionBS.TABLE_Deposits, DepositBill.class.getName());
		IQuery<DepositBill> q = getBankCube(bank).createQuery(cjql);
		List<DepositBill> list = new ArrayList<>();
		List<IDocument<DepositBill>> docs = q.getResultList();
		for (IDocument<DepositBill> doc : docs) {
			doc.tuple().setCode(doc.docid());
			list.add(doc.tuple());
		}
		return list;
	}

	@Override
	public List<CashoutBill> pageCashoutBill(String bank, int currPage, int pageSize) {
		String cjql = String.format("select {'tuple':'*'}.limit(%s).skip(%s) from tuple %s %s where {}", pageSize,
				currPage, IFSBankTransactionBS.TABLE_Cashouts, CashoutBill.class.getName());
		IQuery<CashoutBill> q = getBankCube(bank).createQuery(cjql);
		List<CashoutBill> list = new ArrayList<>();
		List<IDocument<CashoutBill>> docs = q.getResultList();
		for (IDocument<CashoutBill> doc : docs) {
			doc.tuple().setCode(doc.docid());
			list.add(doc.tuple());
		}
		return list;
	}

	@Override
	public List<ExchangeBill> pageExchangeBill(String bank, int currPage, int pageSize) {
		String cjql = String.format("select {'tuple':'*'}.limit(%s).skip(%s) from tuple %s %s where {}", pageSize,
				currPage, IFSBankTransactionBS.TABLE_Exchanges, ExchangeBill.class.getName());
		IQuery<ExchangeBill> q = getBankCube(bank).createQuery(cjql);
		List<ExchangeBill> list = new ArrayList<>();
		List<IDocument<ExchangeBill>> docs = q.getResultList();
		for (IDocument<ExchangeBill> doc : docs) {
			doc.tuple().setCode(doc.docid());
			list.add(doc.tuple());
		}
		return list;
	}

	@Override
	public BigDecimal totalDepositBillAmount(String bank) {
		List<Bson> pipeline = Arrays
				.asList(Document.parse("{$group : {_id : null, totalDepositBillAmount : {$sum : \"$tuple.amount\"}}}"));
		AggregateIterable<Document> it = getBankCube(bank).aggregate(IFSBankTransactionBS.TABLE_Deposits, pipeline);
		BigDecimal ret = null;
		Document doc = it.first();
		if (doc == null) {
			return new BigDecimal(0);
		}
		ret = new BigDecimal(doc.get("totalDepositBillAmount") + "");
		return ret;
	}

	@Override
	public BigDecimal totalDepositBondQuantities(String bank) {
		List<Bson> pipeline = Arrays.asList(Document.parse(
				"{$group : {_id : null, totalDepositBondQuantities : {$sum : \"$tuple.separeteBill.bondQuantities\"}}}"));
		AggregateIterable<Document> it = getBankCube(bank).aggregate(IFSBankTransactionBS.TABLE_Deposits, pipeline);
		BigDecimal ret = null;
		Document doc = it.first();
		if (doc == null) {
			return new BigDecimal(0);
		}
		ret = new BigDecimal(doc.get("totalDepositBondQuantities") + "");
		return ret;
	}

	@Override
	public BigDecimal totalDepositBondAmount(String bank) {
		List<Bson> pipeline = Arrays.asList(Document.parse(
				"{$group : {_id : null, totalDepositBondAmount : {$sum : \"$tuple.separeteBill.bondAmount\"}}}"));
		AggregateIterable<Document> it = getBankCube(bank).aggregate(IFSBankTransactionBS.TABLE_Deposits, pipeline);
		BigDecimal ret = null;
		Document doc = it.first();
		if (doc == null) {
			return new BigDecimal(0);
		}
		ret = new BigDecimal(doc.get("totalDepositBondAmount") + "");
		return ret;
	}

	@Override
	public BigDecimal totalDepositReserveAmount(String bank) {
		List<Bson> pipeline = Arrays.asList(Document.parse(
				"{$group : {_id : null, totalDepositReserveAmount : {$sum : \"$tuple.separeteBill.reserveAmount\"}}}"));
		AggregateIterable<Document> it = getBankCube(bank).aggregate(IFSBankTransactionBS.TABLE_Deposits, pipeline);
		BigDecimal ret = null;
		Document doc = it.first();
		if (doc == null) {
			return new BigDecimal(0);
		}
		ret = new BigDecimal(doc.get("totalDepositReserveAmount") + "");
		return ret;
	}

	@Override
	public BigDecimal totalDepositFreeAmount(String bank) {
		List<Bson> pipeline = Arrays.asList(Document.parse(
				"{$group : {_id : null, totalDepositFreeAmount : {$sum : \"$tuple.separeteBill.freeAmount\"}}}"));
		AggregateIterable<Document> it = getBankCube(bank).aggregate(IFSBankTransactionBS.TABLE_Deposits, pipeline);
		BigDecimal ret = null;
		Document doc = it.first();
		if (doc == null) {
			return new BigDecimal(0);
		}
		ret = new BigDecimal(doc.get("totalDepositFreeAmount") + "");
		return ret;
	}

	@Override
	public BigDecimal totalExchangeBondQuantities(String bank) {
		List<Bson> pipeline = Arrays.asList(Document
				.parse("{$group : {_id : null, totalExchangeBondQuantities : {$sum : \"$tuple.bondQuantities\"}}}"));
		AggregateIterable<Document> it = getBankCube(bank).aggregate(IFSBankTransactionBS.TABLE_Exchanges, pipeline);
		BigDecimal ret = null;
		Document doc = it.first();
		if (doc == null) {
			return new BigDecimal(0);
		}
		ret = new BigDecimal(doc.get("totalExchangeBondQuantities") + "");
		return ret;
	}

	@Override
	public BigDecimal totalExchangeBillAmount(String bank) {
		List<Bson> pipeline = Arrays.asList(Document
				.parse("{$group : {_id : null, totalExchangeBillAmount : {$sum : \"$tuple.deservedAmount\"}}}"));
		AggregateIterable<Document> it = getBankCube(bank).aggregate(IFSBankTransactionBS.TABLE_Exchanges, pipeline);
		BigDecimal ret = null;
		Document doc = it.first();
		if (doc == null) {
			return new BigDecimal(0);
		}
		ret = new BigDecimal(doc.get("totalExchangeBillAmount") + "");
		return ret;
	}

	@Override
	public BigDecimal totalExchangeTailAmount(String bank) {
		List<Bson> pipeline = Arrays.asList(
				Document.parse("{$group : {_id : null, totalExchangeTailAmount : {$sum : \"$tuple.tailAmount\"}}}"));
		AggregateIterable<Document> it = getBankCube(bank).aggregate(IFSBankTransactionBS.TABLE_Exchanges, pipeline);
		BigDecimal ret = null;
		Document doc = it.first();
		if (doc == null) {
			return new BigDecimal(0);
		}
		ret = new BigDecimal(doc.get("totalExchangeTailAmount") + "");
		return ret;
	}

	@Override
	public BigDecimal totalCashoutReqAmount(String bank) {
		List<Bson> pipeline = Arrays.asList(
				Document.parse("{$group : {_id : null, totalCashoutReqAmount : {$sum : \"$tuple.reqAmount\"}}}"));
		AggregateIterable<Document> it = getBankCube(bank).aggregate(IFSBankTransactionBS.TABLE_Cashouts, pipeline);
		BigDecimal ret = null;
		Document doc = it.first();
		if (doc == null) {
			return new BigDecimal(0);
		}
		ret = new BigDecimal(doc.get("totalCashoutReqAmount") + "");
		return ret;
	}

	@Override
	public BigDecimal totalCashoutResAmount(String bank) {
		List<Bson> pipeline = Arrays.asList(
				Document.parse("{$group : {_id : null, totalCashoutResAmount : {$sum : \"$tuple.resAmount\"}}}"));
		AggregateIterable<Document> it = getBankCube(bank).aggregate(IFSBankTransactionBS.TABLE_Cashouts, pipeline);
		BigDecimal ret = null;
		Document doc = it.first();
		if (doc == null) {
			return new BigDecimal(0);
		}
		ret = new BigDecimal(doc.get("totalCashoutResAmount") + "");
		return ret;
	}

	@Override
	public BigDecimal totalCashoutPoundageAmount(String bank) {
		List<Bson> pipeline = Arrays.asList(Document
				.parse("{$group : {_id : null, totalCashoutPoundageAmount : {$sum : \"$tuple.poundageAmount\"}}}"));
		AggregateIterable<Document> it = getBankCube(bank).aggregate(IFSBankTransactionBS.TABLE_Cashouts, pipeline);
		BigDecimal ret = null;
		Document doc = it.first();
		if (doc == null) {
			return new BigDecimal(0);
		}
		ret = new BigDecimal(doc.get("totalCashoutPoundageAmount") + "");
		return ret;
	}

}
