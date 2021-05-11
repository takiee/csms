package ServerHandle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Database.StudentDao;
import Database.VoteDao;
import Message.MessageForVote;

public class VoteThread extends Thread{
	private Socket soc6;

	public VoteThread(Socket socket6) {
		soc6 = socket6;
	}

	public Socket getSoc6() {
		return soc6;
	}

	public void setSoc6(Socket soc6) {
		this.soc6 = soc6;
	}
	
	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(soc6.getInputStream());
			MessageForVote mfv = (MessageForVote)ois.readObject();
			System.out.println("服务器端接收到了相关公告信息："+mfv.getTitle());
			
			StudentDao sd = new StudentDao();
			String[] stus = sd.getStudent();
			for(int i = 0; i<stus.length;i++) {
				if(stus[i].equals(mfv.getSender()))  continue;
				System.out.println(i+"here");
				Boolean b = ManageCliThread.hasClientThread(stus[i]);
				System.out.println(b);
				if(b) {
					VoteThread sc = ManageVoteThread.getVoteThread(stus[i]);
					ObjectOutputStream oos = new ObjectOutputStream(sc.getSoc6().getOutputStream());
					oos.writeObject(mfv);	
				}else {
					VoteDao nod = new VoteDao();
					nod.restoreVote(stus[i], mfv);
				}
			}
			VoteDao vd = new VoteDao();
			vd.restoreVote("0", mfv);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
