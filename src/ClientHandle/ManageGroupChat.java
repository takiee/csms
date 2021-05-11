package ClientHandle;

import java.util.HashMap;
import View.GroupChat;

public class ManageGroupChat {
	private static HashMap<String, GroupChat> hm = new HashMap<String,GroupChat>();
	public static void addGroupChat(String schoolNum,GroupChat groupchat) {
		hm.put(schoolNum, groupchat);
	}
	public static GroupChat getGroupChat(String schoolNum) {
		return (GroupChat)hm.get(schoolNum);
	}
	public static boolean hasGroupChat(String schoolNum) {
		GroupChat gchat = (GroupChat)hm.get(schoolNum);
		if(gchat == null) {
			return false;
		}else return true;
	}
}
