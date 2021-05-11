package ServerHandle;

import java.util.HashMap;

public class ManageGroupChatThread {
	public static HashMap<String, GroupChatThread> hm = new HashMap<String,GroupChatThread>();
	
	public static void addGroupChatThread(String schoolNum,GroupChatThread gct) {
		hm.put(schoolNum, gct);
	}
	public static GroupChatThread getGroupChatThread(String schoolNum) {
		return (GroupChatThread)hm.get(schoolNum);
	}
	public static boolean hasGroupChatThread(String schoolNum) {
		GroupChatThread gct = (GroupChatThread)hm.get(schoolNum);
		if(gct == null) {
			return false;
	 	}else return true;
	}

}
