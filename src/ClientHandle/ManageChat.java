/*
 * 这是一个管理用户聊天的类
 * */
package ClientHandle;
import java.util.*;
import View.Chat;
public class ManageChat {
	static HashMap<String, Chat> hm = new HashMap<String,Chat>();
	public static void addChat(String schoolNumAnopSchoolNum,Chat chat) {
		hm.put(schoolNumAnopSchoolNum, chat);
	}
	
	public static Chat getChat(String schoolNumAnopSchoolNum) {
		 return (Chat)hm.get(schoolNumAnopSchoolNum);
	}
	
	public static boolean hasChat(String schoolNumAnopSchoolNum) {
		Chat chat = (Chat)hm.get(schoolNumAnopSchoolNum);
		if(chat == null) {
			return false;
		}else return true;
	}
}
