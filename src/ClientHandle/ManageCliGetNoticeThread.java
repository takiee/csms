package ClientHandle;

import java.util.HashMap;

public class ManageCliGetNoticeThread {
	private static HashMap<String, ClientGetNoticeThread> hm = new HashMap<String,ClientGetNoticeThread>();
	public static void addClientGetNoticeThread(String Num,ClientGetNoticeThread cgnt) {
		hm.put(Num, cgnt);
		}
	public static ClientGetNoticeThread getClientGetNoticeThread(String Num) {
		return (ClientGetNoticeThread)hm.get(Num);
	}
}
