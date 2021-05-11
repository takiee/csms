package ClientHandle;

import java.util.HashMap;

public class ManageCliDownFileThread {
	private static HashMap<String, ClientDownFileThread> hm = new HashMap<String,ClientDownFileThread>();
	public static void addClientDownFileThread(String Num,ClientDownFileThread cdft) {
		hm.put(Num, cdft);
		}
	public static ClientDownFileThread getClientDownFileThreadd(String Num) {
		return (ClientDownFileThread)hm.get(Num);
	}

}
