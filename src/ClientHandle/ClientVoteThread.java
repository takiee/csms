package ClientHandle;

import java.io.ObjectInputStream;
import java.net.Socket;
import Message.MessageForVote;
import View.Viewvote;

public class ClientVoteThread extends Thread{
	private Socket soc6;
	private String schoolNum;
	
	public ClientVoteThread(String schoolNum, Socket soc6) {
		this.schoolNum = schoolNum;
		this.soc6 = soc6;
	}
	public Socket getSoc6() {
		return soc6;
	}
	public void setSoc6(Socket soc6) {
		this.soc6 = soc6;
	}
	public String getString() {
		return schoolNum;
	}
	public void setString(String schoolNum) {
		this.schoolNum = schoolNum;
	}
	
	public void run() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(soc6.getInputStream());
			MessageForVote mfv = (MessageForVote)ois.readObject();
			Viewvote vv = new Viewvote(mfv.getGetter(),mfv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
