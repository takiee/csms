package Message;
/*
 * 1 登录成功
 * 2 用户名不存在
 * 3 用户名或密码错误
 * 5 未知错误或网络连接失误
 * */
public class MessageForRegister implements java.io.Serializable{
	private static final long serialVersionUID = 495L;
	private String mesType;
	public String getMesType() {
		return mesType;
	}
	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
}
