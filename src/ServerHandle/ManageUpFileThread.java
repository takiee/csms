package ServerHandle;

import java.util.HashMap;

public class ManageUpFileThread {
	public static HashMap<String, UpFileThread> hm = new HashMap<String,UpFileThread>();
	
	public static void addUpFileThread(String schoolNum,UpFileThread uft) {
		hm.put(schoolNum, uft);
	}
	public static UpFileThread getUpFileThread(String schoolNum) {
		return (UpFileThread)hm.get(schoolNum);
	}
	public static boolean hasUpFileThread(String schoolNum) {
		UpFileThread uft = (UpFileThread)hm.get(schoolNum);
		if(uft == null) {
			return false;
	 	}else return true;
	}

}
