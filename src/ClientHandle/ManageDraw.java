package ClientHandle;

import java.util.HashMap;
import View.Draw;

public class ManageDraw {
	static HashMap<String, Draw> hm = new HashMap<String,Draw>();
	public static void addDraw(String schoolNumAnopSchoolNum,Draw chat) {
		hm.put(schoolNumAnopSchoolNum, chat);
	}
	public static Draw getDraw(String schoolNumAnopSchoolNum) {
		 return (Draw)hm.get(schoolNumAnopSchoolNum);
	}
	public static boolean hasDraw(String schoolNumAnopSchoolNum) {
		Draw chat = (Draw)hm.get(schoolNumAnopSchoolNum);
		if(chat == null) {
			return false;
		}else return true;
	}
}
