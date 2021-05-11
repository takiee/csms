/*
 * 这是一个管理客户端和服务器保持通讯的线程类
 * */
package ClientHandle;
import java.util.*;
public class ManageCliConServerThread {
	
	private static HashMap<String, ClientConServerThread> hm = new HashMap<String,ClientConServerThread>();
	public static void addClientConServerThread(String Num,ClientConServerThread ssct) {
		hm.put(Num, ssct);
		}
	public static ClientConServerThread getClientConServerThread(String Num) {
		return (ClientConServerThread)hm.get(Num);
	}
}

