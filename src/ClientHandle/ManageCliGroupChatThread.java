package ClientHandle;

import java.util.HashMap;

public class ManageCliGroupChatThread {
	private static HashMap<String, ClientGroupChatThread> hm = new HashMap<String,ClientGroupChatThread>();
	public static void addClientGroupChatThread(String Num,ClientGroupChatThread cgct) {
		hm.put(Num, cgct);
		}
	public static ClientGroupChatThread getClientGroupChatThread(String Num) {
		return (ClientGroupChatThread)hm.get(Num);
	}

}
