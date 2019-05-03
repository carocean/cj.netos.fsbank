package cj.netos.fsbank.plugin.FSBAEngine;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TestBigDecimal {
	public static void main(String...strings)	{
		BigDecimal bd=new BigDecimal(22.001640204);//,new MathContext(50, RoundingMode.FLOOR)
		System.out.println(bd);
		String v=bd.setScale(3,RoundingMode.FLOOR).toString();
		System.out.println(v);
	}
}
