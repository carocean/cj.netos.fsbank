package cj.netos.fsbank.plugin.FSBAEngine;

import io.netty.util.CharsetUtil;

public class TestBigDecimal {
	public static void main(String...strings)	{
//		BigDecimal bd=new BigDecimal(22.001640204);//,new MathContext(50, RoundingMode.FLOOR)
//		System.out.println(bd);
//		String v=bd.setScale(3,RoundingMode.FLOOR).toString();
//		System.out.println(v);
		String text="我是a个中国人。";
		byte[] b=text.getBytes(CharsetUtil.US_ASCII);
		System.out.println(new String(b,CharsetUtil.US_ASCII));
	}
}
