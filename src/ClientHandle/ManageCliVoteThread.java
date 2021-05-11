package ClientHandle;

import java.util.HashMap;

public class ManageCliVoteThread {
	private static HashMap<String, ClientVoteThread> hm = new HashMap<String,ClientVoteThread>();
	public static void addClientVoteThread(String Num,ClientVoteThread cvt) {
		hm.put(Num, cvt);
		}
	public static ClientVoteThread getClientVoteThread(String Num) {
		return (ClientVoteThread)hm.get(Num);
	}
}
