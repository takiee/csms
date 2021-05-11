package ClientHandle;

import java.util.HashMap;

public class ManageCliUpFileThread {
	private static HashMap<String, ClientUpFileThread> hm = new HashMap<String,ClientUpFileThread>();
	public static void addClientUpFileThread(String Num,ClientUpFileThread cuft) {
		hm.put(Num, cuft);
		}
	public static ClientUpFileThread getClientUpFileThreadd(String Num) {
		return (ClientUpFileThread)hm.get(Num);
	}
}
