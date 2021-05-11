package ServerHandle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Database.NoticeDao;
import Database.StudentDao;
import Message.MessageForNotice;

public class NoticeThread extends Thread{
	Socket soc2 ;
	public Socket getSoc2() {
		return soc2;
	}
	public void setSoc2(Socket soc2) {
		this.soc2 = soc2;
	}
	public NoticeThread(Socket soc2) {
		this.soc2 = soc2;
	}
	public void run() {
		while(true) {
			//���߳����������빫���йص���Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(soc2.getInputStream());
				MessageForNotice mfn = (MessageForNotice)ois.readObject();
				System.out.println("�������˽��յ�����ع�����Ϣ��"+mfn.getTitle());
				
				StudentDao sd = new StudentDao();
				String[] stus = sd.getStudent();
				System.out.println(stus.toString());
				for(int i = 0; i<stus.length;i++) {
					System.out.println(i+"here");
					Boolean b = ManageCliThread.hasClientThread(stus[i]);
					System.out.println(b);
					if(b) {
						NoticeThread sc = ManageNoticeThread.getNoticeThread(stus[i]);
						ObjectOutputStream oos = new ObjectOutputStream(sc.getSoc2().getOutputStream());
						oos.writeObject(mfn);	
						System.out.println("3���յ��˹��棡");
					}else {
						NoticeDao nod = new NoticeDao();
						nod.restoreNotice(stus[i], mfn);
						System.out.println(3);
					}
				}	
				NoticeDao nod = new NoticeDao();
				nod.restoreNotice("0", mfn);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
