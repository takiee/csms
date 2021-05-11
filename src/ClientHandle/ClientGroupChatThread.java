package ClientHandle;

import java.io.ObjectInputStream;
import java.net.Socket;

import Message.MessageForGroupChat;
import View.GroupChat;

public class ClientGroupChatThread extends Thread{
	private Socket soc1;
	private String schoolNum;
	
	public ClientGroupChatThread(String schoolNum,Socket soc1) {
		this.schoolNum = schoolNum;
		this.soc1 = soc1;
	}
	
	public Socket getSoc1() {
		return soc1;
	}
	public void setSoc1(Socket soc1) {
		this.soc1 = soc1;
	}
	
	public void run() {
		while(true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(soc1.getInputStream());
				MessageForGroupChat mfgc = (MessageForGroupChat)ois.readObject();
				
				GroupChat gc = ManageGroupChat.getGroupChat(mfgc.getGetter());
				if(gc == null) {
					GroupChat gct = new GroupChat(mfgc.getGetter());
					ManageGroupChat.addGroupChat(mfgc.getGetter(),gct);
					gct.setVisible(true);
					gct.appendView(mfgc);
				}else {
					gc.appendView(mfgc);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
