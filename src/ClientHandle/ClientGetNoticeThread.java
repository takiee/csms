package ClientHandle;

import java.io.ObjectInputStream;
import java.net.Socket;

import Message.MessageForNotice;
import View.Viewnotice;

public class ClientGetNoticeThread extends Thread{
	private Socket soc2;
	private String schoolNum;

	public ClientGetNoticeThread(String schoolNum,Socket soc2){
		this.soc2 = soc2;
		this.schoolNum = schoolNum;
		}

	public Socket getSoc2() {
		return soc2;
		}

	public void run(){
		while(true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(soc2.getInputStream());
				MessageForNotice mfn = (MessageForNotice)ois.readObject();
				System.out.println("读取到从服务器发来的公告"+mfn.getTitle());
				Viewnotice not = new Viewnotice(mfn.getGetter());
				not.showNoticeMessage(mfn);
				not.setVisible(true);
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
}
}
