package ServerHandle;

import java.net.Socket;
import java.util.HashMap;

public class ManageDownSoc {
	public static HashMap<String, Socket> hm = new HashMap<String,Socket>();
	
	//向hm中添加一个客户端通讯线程
	public static void addSoc(String schoolNum,Socket soc7) {
		hm.put(schoolNum, soc7);
	}
	public static Socket getSoc(String schoolNum) {
		return (Socket)hm.get(schoolNum);
	}
	public static boolean hasSoc(String schoolNum) {
		Socket dft = (Socket)hm.get(schoolNum);
		if(dft == null) {
			return false;
	 	}else return true;
	}
}
