package cj.netos.fsbank.args;

public class BankCompany {
	String name;
	String code;// 该企业如果在netos的统一用户中心登记则有此值
	String address;
	String contact;
	String orgCode;// 营业执照的组织代码号
	String bscope;// 经营范围
	String aboutUS;// 简介，即关于我们
	String blicensePhotoFile;// 营业执照电子照
	String bank;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getBscope() {
		return bscope;
	}
	public void setBscope(String bscope) {
		this.bscope = bscope;
	}
	public String getAboutUS() {
		return aboutUS;
	}
	public void setAboutUS(String aboutUS) {
		this.aboutUS = aboutUS;
	}
	public String getBlicensePhotoFile() {
		return blicensePhotoFile;
	}
	public void setBlicensePhotoFile(String blicensePhotoFile) {
		this.blicensePhotoFile = blicensePhotoFile;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	
}
