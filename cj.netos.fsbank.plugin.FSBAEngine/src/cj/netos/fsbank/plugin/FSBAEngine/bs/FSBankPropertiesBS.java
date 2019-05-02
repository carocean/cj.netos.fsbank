package cj.netos.fsbank.plugin.FSBAEngine.bs;

import java.util.ArrayList;
import java.util.List;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.netos.fsbank.bs.IFSBankPropertiesBS;
import cj.netos.fsbank.plugin.FSBAEngine.bo.BankProperty;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.ultimate.util.StringUtil;

@CjService(name = "fSBankPropertiesBS")
public class FSBankPropertiesBS implements IFSBankPropertiesBS {
	@CjServiceRef(refByName = "mongodb.fsbank.home")
	ICube home;

	@Override
	public void remove(String bank, String key) {
		home.deleteDocOne(TABLE_KEY, String.format("{'tuple.bank':'%s','tuple.key':'%s'}", bank, key));
	}

	@Override
	public void put(String bank, String key, String value) {
		if (StringUtil.isEmpty(value) && containsKey(bank, key)) {
			remove(bank, key);
		}
		BankProperty property = new BankProperty(bank, key, value);
		home.saveDoc(TABLE_KEY, new TupleDocument<>(property));
	}

	@Override
	public boolean containsKey(String bank, String key) {
		return home.tupleCount(TABLE_KEY, String.format("{'tuple.bank':'%s','tuple.key':'%s'}", bank, key)) > 0;
	}

	@Override
	public String get(String bank, String key) {
		String cjql = String.format("select {'tuple':'*'} from tuple %s %s where {'tuple.bank':'%s','tuple.key':'%s'}",
				TABLE_KEY, BankProperty.class.getName(), bank, key);
		IQuery<BankProperty> q = home.createQuery(cjql);
		IDocument<BankProperty> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple().getValue();
	}

	@Override
	public String[] enumKey(String bank) {
		String cjql = String.format("select {'tuple.key':1} from tuple %s %s where {'tuple.bank':'%s'}", TABLE_KEY,
				BankProperty.class.getName(), bank);
		IQuery<BankProperty> q = home.createQuery(cjql);
		List<String> list = new ArrayList<String>();
		List<IDocument<BankProperty>> docs = q.getResultList();
		for (IDocument<BankProperty> doc : docs) {
			list.add(doc.tuple().getKey());
		}
		return list.toArray(new String[0]);
	}

	@Override
	public String[] pageKeys(String bank, int currPage, int pageSize) {
		String cjql = String.format(
				"select {'tuple.key':1}.limit(%s).skip(%s) from tuple %s %s where {'tuple.bank':'%s'}", pageSize,
				currPage, TABLE_KEY, BankProperty.class.getName(), bank);
		IQuery<BankProperty> q = home.createQuery(cjql);
		List<String> list = new ArrayList<String>();
		List<IDocument<BankProperty>> docs = q.getResultList();
		for (IDocument<BankProperty> doc : docs) {
			list.add(doc.tuple().getKey());
		}
		return list.toArray(new String[0]);
	}

	@Override
	public long count(String bank) {
		return home.tupleCount(TABLE_KEY, String.format("{'tuple.bank':'%s'}", bank));
	}

}
