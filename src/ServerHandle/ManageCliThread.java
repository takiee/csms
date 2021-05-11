package ServerHandle;
import java.util.*;

public class ManageCliThread {
	public static HashMap<String, ServerConClientThread> hm = new HashMap<String,ServerConClientThread>();
	
	public static void addClientThread(String schoolNum,ServerConClientThread scc) {
		hm.put(schoolNum, scc);
	}
	public static ServerConClientThread getClientThread(String schoolNum) {
		return (ServerConClientThread)hm.get(schoolNum);
	}
	public static boolean hasClientThread(String schoolNum) {
		ServerConClientThread scct = (ServerConClientThread)hm.get(schoolNum);
		if(scct == null) {
			return false;
	 	}else return true;
	}
}
