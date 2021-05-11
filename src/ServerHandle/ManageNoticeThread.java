package ServerHandle;

import java.util.HashMap;

public class ManageNoticeThread {
		public static HashMap<String, NoticeThread> hm = new HashMap<String,NoticeThread>();
		
		public static void addNoticeThread(String schoolNum,NoticeThread nt) {
			hm.put(schoolNum, nt);
		}
		public static NoticeThread getNoticeThread(String schoolNum) {
			return (NoticeThread)hm.get(schoolNum);
		}
		public static boolean hasNoticeThread(String schoolNum) {
			NoticeThread nt = (NoticeThread)hm.get(schoolNum);
			if(nt == null) {
				return false;
		 	}else return true;
		}
}
