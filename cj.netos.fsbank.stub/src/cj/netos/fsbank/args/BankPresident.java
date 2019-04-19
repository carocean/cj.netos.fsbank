package cj.netos.fsbank.args;

/**
 * 行长信息表，一般将当前银行创建的资料抄录到此，大部分信息来源于平台统一用户中心，如果用户中心缺则让用户在界面填写
 * 
 * @author caroceanjofers
 *
 */
public class BankPresident {
	String userCode;
	String nickName;
	String realName;
	String sex;// male,female,unknown
	int age;
	String bank;// 一个银行只有一个行长
	String cellphone;
	String address;
	String idcardFrontPhotoFile;// 身份证正面照片，存到其银行文件系统
	String idcardBackPhotoFile;// 身份证背面照片，存到其银行文件系统

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdcardFrontPhotoFile() {
		return idcardFrontPhotoFile;
	}

	public void setIdcardFrontPhotoFile(String idcardFrontPhotoFile) {
		this.idcardFrontPhotoFile = idcardFrontPhotoFile;
	}

	public String getIdcardBackPhotoFile() {
		return idcardBackPhotoFile;
	}

	public void setIdcardBackPhotoFile(String idcardBackPhotoFile) {
		this.idcardBackPhotoFile = idcardBackPhotoFile;
	}

}
