package Message;

import javax.swing.text.StyledDocument;
import Database.MessageDao;
public class MessageForChat extends MessageDao implements java.io.Serializable {
	
	private static final long serialVersionUID = 2845L;
	private String sender;
	private String getter;
	private String sendTime;
	private StyledDocument doc;
	
	public StyledDocument getDoc() {
		return doc;
	}
	public void setDoc(StyledDocument doc) {
		this.doc = doc;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getGetter() {
		return getter;
	}
	public void setGetter(String getter) {
		this.getter = getter;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}


}
