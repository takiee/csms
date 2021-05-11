package ServerHandle;

import java.util.HashMap;

public class ManageDownFileThread {
	public static HashMap<String, DownFileThread> hm = new HashMap<String,DownFileThread>();
	
	public static void addDownFileThread(String schoolNum,DownFileThread dft) {
		hm.put(schoolNum, dft);
	}
	public static DownFileThread getDownFileThread(String schoolNum) {
		return (DownFileThread)hm.get(schoolNum);
	}
	public static boolean hasDownFileThread(String schoolNum) {
		DownFileThread dft = (DownFileThread)hm.get(schoolNum);
		if(dft == null) {
			return false;
	 	}else return true;
	}
}
