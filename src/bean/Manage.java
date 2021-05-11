package bean;

import Database.*;
public class Manage extends ManageDao implements java.io.Serializable{
	//定义管理员属性：管理员账号
	public String manageNum;
	
	public String getManageNum() {
		return manageNum;
	}
	public void setManageNum(String manageNum) {
		this.manageNum = manageNum;
	}
}
