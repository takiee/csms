/*
 * ����һ������ͻ��˺ͷ���������ͨѶ���߳���
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

