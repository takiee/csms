/*
 * ���ܣ��Ƿ�������ĳ���ͻ��˵�ͨ���߳�
 * */
package ServerHandle;
import java.net.*;
import Database.MessageDao;
import Message.MessageForChat;
import java.io.*;

public class ServerConClientThread extends Thread{
	public Socket soc;
	public Socket soc5;
	public Socket getSoc() {
		return soc;
	}
	public void setSoc(Socket soc) {
		this.soc = soc;
	}
	public Socket getSoc5() {
		return soc5;
	}
	public void setSoc5(Socket soc5) {
		this.soc5 = soc5;
	}
	public ServerConClientThread(Socket soc) {
		this.soc = soc;//�ѷ�������ÿͻ��˵��� �Ӹ���soc
		
	}
	//���߳̽��տͻ��˵���Ϣ
	public void run() {
		while(true){	
		try {
			MessageForChat mfc = new MessageForChat();
			ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
			Object o = ois.readObject();
			String getter = "";
			mfc = (MessageForChat)o;
			getter = mfc.getGetter();
			         
			//�������ж϶Է��Ƿ�������
			Boolean b = ManageCliThread.hasClientThread(getter);
			try {
				if(b) {
					ServerConClientThread sc = ManageCliThread.getClientThread(getter);
					ObjectOutputStream oos = new ObjectOutputStream(sc.soc.getOutputStream());
					oos.writeObject(mfc);
					}
				else {
					System.out.println("�Է�������");
					MessageDao md = new MessageDao();
					md.restoreMessage(mfc.getSender(), mfc.getGetter(), mfc.getDoc(), mfc.getSendTime());
						}
					}
			catch(Exception e) {
				e.printStackTrace();
					}
				}
			 catch (Exception e) {
				e.printStackTrace();
		}}
	}
}
