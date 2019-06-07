package cj.netos.inform;

import cj.studio.ecm.EcmException;
import cj.ultimate.util.StringUtil;

 class InformAddress {
	private static String PARAM_KEY_HTTPVERSION= "http-version";
	private String address;
	
	public InformAddress(String address) {
		int pos = address.indexOf("://");
		if (pos < 0) {
			throw new EcmException("回调地址错误，缺少协议:" + address);
		}
		String remain = address.substring(pos + 1, address.length());
		if (StringUtil.isEmpty(remain)) {
			throw new EcmException("回调地址错误，缺少domain:" + address);
		}
		this.address = address;
	}

	public String address() {
		return address;
	}
	/**
	 * 例：http://news.163.com:80/xxx/eee.html?http-version=1.0
	 * protocol=http/1.0,如果没有http-version参数，则默认为1.1版本
	 * @return
	 */
	public String protocol() {
		int pos = address.indexOf("://");
		String noversion= address.substring(0,pos);
		
		pos=address.indexOf(PARAM_KEY_HTTPVERSION);
		if(pos>0) {
			String remainning=address.substring(pos+PARAM_KEY_HTTPVERSION.length(),address.length());
			if(StringUtil.isEmpty(remainning)) {
				return String.format("%s/1.1", noversion);
			}
			while(remainning.startsWith(" ")) {
				remainning=remainning.substring(1,remainning.length());
			}
			while(remainning.startsWith("=")) {
				remainning=remainning.substring(1,remainning.length());
			}
			pos=remainning.indexOf("&");
			if(pos<0) {
				return String.format("%s/%s", noversion,remainning.trim());
			}
			return String.format("%s/%s", noversion,remainning.subSequence(0, pos));
		}
		return String.format("%s/1.1", noversion);
	}
	/**
	 * 例：http://news.163.com:80/xxx/eee.html
	 * host=news.163.com:80
	 * @return
	 */
	public String host() {
		int pos = address.indexOf("://");
		String remain = address.substring(pos+3, address.length());
		while (remain.startsWith("/")) {
			remain = remain.substring(0, remain.length());
		}
		pos = remain.indexOf("/");
		if (pos < 0)
			return remain;
		remain = remain.substring(0, pos);
		return remain;
	}
	/**
	 * 例：http://news.163.com:80/xxx/eee.html
	 * url=/xxx/eee.html
	 * @return
	 */
	public String url() {
		int pos = address.indexOf("://");
		String remain = address.substring(pos+3, address.length());
		while (remain.startsWith("/")) {
			remain = remain.substring(0, remain.length());
		}
		pos = remain.indexOf("/");
		if(pos<0) {
			pos=remain.indexOf("?");
			if(pos>-1) {
				return "/"+remain.substring(pos,remain.length());
			}
			return "/";
		}
		remain = remain.substring(pos,remain.length());
		return remain;
	}
	
}
