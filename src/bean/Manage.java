package bean;

import Database.*;
public class Manage extends ManageDao implements java.io.Serializable{
	//�������Ա���ԣ�����Ա�˺�
	public String manageNum;
	
	public String getManageNum() {
		return manageNum;
	}
	public void setManageNum(String manageNum) {
		this.manageNum = manageNum;
	}
}
