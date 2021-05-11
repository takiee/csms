package ServerHandle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Database.GroupDao;
import Database.StudentDao;
import Message.MessageForGroupChat;

public class GroupChatThread extends Thread{
	Socket soc1;

	public Socket getSoc1() {
		return soc1;
	}

	public void setSoc1(Socket soc1) {
		this.soc1 = soc1;
	}
	 
	public GroupChatThread(Socket soc1) {
		this.soc1 = soc1;
	}
	
	public void run() {
		while(true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(soc1.getInputStream());
				MessageForGroupChat mfgc = (MessageForGroupChat)ois.readObject();
				System.out.println("服务器端接收到群聊消息：");
	
				StudentDao sd = new StudentDao();
				String[] stus = sd.getStudent();
				for(int i = 0;i<stus.length;i++) {
					if(stus[i].equals(mfgc.getSender())) {
						continue;
					}else {
						Boolean b = ManageCliThread.hasClientThread(stus[i]);
						mfgc.setGetter(stus[i]);
					if(b) {
						GroupChatThread gct = ManageGroupChatThread.getGroupChatThread(stus[i]);
						ObjectOutputStream oos = new ObjectOutputStream(gct.getSoc1().getOutputStream());
						oos.writeObject(mfgc);	
					}
					//处理离线群聊消息
					else {
						GroupDao gd = new GroupDao();
						gd.restoreGroupMessage(mfgc,stus[i]);
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
