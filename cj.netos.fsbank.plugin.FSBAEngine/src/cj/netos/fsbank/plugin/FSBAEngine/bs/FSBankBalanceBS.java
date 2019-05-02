package cj.netos.fsbank.plugin.FSBAEngine.bs;

import java.math.BigDecimal;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.model.UpdateOptions;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.netos.fsbank.args.Balance;
import cj.netos.fsbank.bs.IFSBankBalanceBS;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceSite;

@CjService(name = "fSBankBalance")
public class FSBankBalanceBS implements IFSBankBalanceBS {
	ICube bank;
	@CjServiceSite
	IServiceSite site;

	ICube getBank(String bankid) {
		if (bank != null) {
			return bank;
		}
		bank = (ICube) site.getService("mongodb.fsbank." + bankid+":autocreate");
		return bank;
	}
	
	@Override
	public Balance loadBalance(String bank) {
		ICube cube = getBank(bank);
		String cjql = String.format("select {'tuple':'*'} from tuple %s %s where {}", TABLE_NAME,
				Balance.class.getName());
		IQuery<Balance> q = cube.createQuery(cjql);
		IDocument<Balance> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple();
	}
	@Override
	public BigDecimal getBondPrice(String bank) {
		ICube cube = getBank(bank);
		String cjql = String.format("select {'tuple.bondPrice':1} from tuple %s %s where {}", TABLE_NAME,
				Balance.class.getName());
		IQuery<Balance> q = cube.createQuery(cjql);
		IDocument<Balance> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple().getBondPrice();
	}

	@Override
	public void updateBondPrice(String bank, BigDecimal price) {
		ICube cube = getBank(bank);
		Bson filter=Document.parse(String.format("{}"));
		Bson update=Document.parse(String.format("{'$set':{'tuple.bondPrice':%s}}",price));
		UpdateOptions uo=new UpdateOptions();
		uo.upsert(true);
		cube.updateDocOne(TABLE_NAME, filter, update,uo);
	}

	@Override
	public String getBondKind(String bank) {
		ICube cube = getBank(bank);
		String cjql = String.format("select {'tuple.bondKind':1} from tuple %s %s where {}", TABLE_NAME,
				Balance.class.getName());
		IQuery<Balance> q = cube.createQuery(cjql);
		IDocument<Balance> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple().getBondKind();
	}

	@Override
	public void updateBondKind(String bank, String kind) {
		ICube cube = getBank(bank);
		Bson filter=Document.parse(String.format("{}"));
		Bson update=Document.parse(String.format("{'$set':{'tuple.bondKind':'%s'}}",kind));
		UpdateOptions uo=new UpdateOptions();
		uo.upsert(true);
		cube.updateDocOne(TABLE_NAME, filter, update,uo);
	}

	@Override
	public String getCurrency(String bank) {
		ICube cube = getBank(bank);
		String cjql = String.format("select {'tuple.currency':1} from tuple %s %s where {}", TABLE_NAME,
				Balance.class.getName());
		IQuery<Balance> q = cube.createQuery(cjql);
		IDocument<Balance> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple().getCurrency();
	}

	@Override
	public void updateCurrency(String bank, String currency) {
		ICube cube = getBank(bank);
		Bson filter=Document.parse(String.format("{}"));
		Bson update=Document.parse(String.format("{'$set':{'tuple.currency':'%s'}}",currency));
		UpdateOptions uo=new UpdateOptions();
		uo.upsert(true);
		cube.updateDocOne(TABLE_NAME, filter, update,uo);
	}

	@Override
	public BigDecimal getBondAmountBalance(String bank) {
		ICube cube = getBank(bank);
		String cjql = String.format("select {'tuple.bondAmount':1} from tuple %s %s where {}", TABLE_NAME,
				Balance.class.getName());
		IQuery<Balance> q = cube.createQuery(cjql);
		IDocument<Balance> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple().getBondAmount();
	}

	@Override
	public void updateBondAmountBalance(String bank, BigDecimal balance) {
		ICube cube = getBank(bank);
		Bson filter=Document.parse(String.format("{}"));
		Bson update=Document.parse(String.format("{'$set':{'tuple.bondAmount':%s}}",balance));
		UpdateOptions uo=new UpdateOptions();
		uo.upsert(true);
		cube.updateDocOne(TABLE_NAME, filter, update,uo);
	}

	@Override
	public BigDecimal getBondQuantitiesBalance(String bank) {
		ICube cube = getBank(bank);
		String cjql = String.format("select {'tuple.bondQuantities':1} from tuple %s %s where {}", TABLE_NAME,
				Balance.class.getName());
		IQuery<Balance> q = cube.createQuery(cjql);
		IDocument<Balance> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple().getBondQuantities();
	}

	@Override
	public void updateBondQuantitiesBalance(String bank, BigDecimal balance) {
		ICube cube = getBank(bank);
		Bson filter=Document.parse(String.format("{}"));
		Bson update=Document.parse(String.format("{'$set':{'tuple.bondQuantities':%s}}",balance));
		UpdateOptions uo=new UpdateOptions();
		uo.upsert(true);
		cube.updateDocOne(TABLE_NAME, filter, update,uo);
	}

	@Override
	public BigDecimal getReserveAmountBalance(String bank) {
		ICube cube = getBank(bank);
		String cjql = String.format("select {'tuple.reserveAmount':1} from tuple %s %s where {}", TABLE_NAME,
				Balance.class.getName());
		IQuery<Balance> q = cube.createQuery(cjql);
		IDocument<Balance> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple().getReserveAmount();
	}

	@Override
	public void updateReserveAmountBalance(String bank, BigDecimal balance) {
		ICube cube = getBank(bank);
		Bson filter=Document.parse(String.format("{}"));
		Bson update=Document.parse(String.format("{'$set':{'tuple.reserveAmount':%s}}",balance));
		UpdateOptions uo=new UpdateOptions();
		uo.upsert(true);
		cube.updateDocOne(TABLE_NAME, filter, update,uo);
	}

	@Override
	public BigDecimal getFreeAmountBalance(String bank) {
		ICube cube = getBank(bank);
		String cjql = String.format("select {'tuple.freeAmount':1} from tuple %s %s where {}", TABLE_NAME,
				Balance.class.getName());
		IQuery<Balance> q = cube.createQuery(cjql);
		IDocument<Balance> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple().getFreeAmount();
	}

	@Override
	public void updateFreeAmountBalance(String bank, BigDecimal balance) {
		ICube cube = getBank(bank);
		Bson filter=Document.parse(String.format("{}"));
		Bson update=Document.parse(String.format("{'$set':{'tuple.freeAmount':%s}}",balance));
		UpdateOptions uo=new UpdateOptions();
		uo.upsert(true);
		cube.updateDocOne(TABLE_NAME, filter, update,uo);
	}

	@Override
	public BigDecimal getTailAmountBalance(String bank) {
		ICube cube = getBank(bank);
		String cjql = String.format("select {'tuple.tailAmount':1} from tuple %s %s where {}", TABLE_NAME,
				Balance.class.getName());
		IQuery<Balance> q = cube.createQuery(cjql);
		IDocument<Balance> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple().getTailAmount();
	}

	@Override
	public void updateTailAmountBalance(String bank, BigDecimal balance) {
		ICube cube = getBank(bank);
		Bson filter=Document.parse(String.format("{}"));
		Bson update=Document.parse(String.format("{'$set':{'tuple.tailAmount':%s}}",balance));
		UpdateOptions uo=new UpdateOptions();
		uo.upsert(true);
		cube.updateDocOne(TABLE_NAME, filter, update,uo);
	}


}
