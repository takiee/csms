package Message;
/*
 * 1 ��¼�ɹ�
 * 2 �û���������
 * 3 �û������������
 * 5 δ֪�������������ʧ��
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
