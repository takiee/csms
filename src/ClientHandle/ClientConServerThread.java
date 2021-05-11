package ClientHandle;

import java.net.*;
import Message.MessageForChat;
import View.Chat;
import java.io.*;
public class ClientConServerThread extends Thread {
	private Socket soc;
	public ClientConServerThread(Socket soc){
		this.soc = soc;}

	public Socket getSoc() {
		return soc;
	}
	public void setSoc(Socket soc) {
		this.soc = soc;
	}

	public void run(){
		while(true) {
		try {
			ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
			Object o = ois.readObject();
			MessageForChat mes = new MessageForChat();
			mes = (MessageForChat)o;
			Chat opchat = ManageChat.getChat(mes.getGetter()+" "+mes.getSender());
			if(opchat == null) {
				Chat opc = new Chat(mes.getGetter(),mes.getSender());
				ManageChat.addChat(mes.getGetter()+" "+mes.getSender(), opc);
				opc.setVisible(true);
				opc.appendView(mes);
				}else {
					opchat.appendView(mes);
							}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}}

}
