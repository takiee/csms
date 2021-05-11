package ServerHandle;

import java.util.HashMap;

public class ManageVoteThread {
	public static HashMap<String, VoteThread> hm = new HashMap<String,VoteThread>();
	
	public static void addVoteThread(String schoolNum,VoteThread vt) {
		hm.put(schoolNum, vt);
	}
	public static VoteThread getVoteThread(String schoolNum) {
		return (VoteThread)hm.get(schoolNum);
	}
	public static boolean hasNoticeThread(String schoolNum) {
		VoteThread vt = (VoteThread)hm.get(schoolNum);
		if(vt == null) {
			return false;
	 	}else return true;
	}
}
