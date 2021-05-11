package Message;

import javax.swing.text.StyledDocument;

public class MessageForGroupChat implements java.io.Serializable {
	private static final long serialVersionUID = 224L;
	
	private String sender;
	private String sendTime;
	private String getter;
	private StyledDocument doc;
	public String getGetter() {
		return getter;
	}
	public void setGetter(String getter) {
		this.getter = getter;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public StyledDocument getDoc() {
		return doc;
	}
	public void setDoc(StyledDocument doc) {
		this.doc = doc;
	}

	

}
