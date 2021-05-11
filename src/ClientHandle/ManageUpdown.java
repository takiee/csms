package ClientHandle;

import java.util.HashMap;
import View.Updown;

public class ManageUpdown {
public static HashMap<String, Updown> hm = new HashMap<String,Updown>();
	public static void addUpdown(String schoolNum,Updown up) {
		hm.put(schoolNum, up);
	}
	public static Updown getUpdown(String schoolNum) {
		return (Updown)hm.get(schoolNum);
	}
	public static void deleteUpdown(String schoolNum) {
		hm.remove(schoolNum);
	}
	public static boolean hasUpdown(String schoolNum) {
		Updown dft = (Updown)hm.get(schoolNum);
		if(dft == null) {
			return false;
	 	}else return true;
	}
}
