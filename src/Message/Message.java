package Message;

import javax.swing.ImageIcon;
import javax.swing.text.StyledDocument;

public class Message  implements java.io.Serializable{

	private static final long serialVersionUID = 761L;
	private String str;
	private ImageIcon ic;
	public StyledDocument doc;
	public StyledDocument getDoc() {
		return doc;
	}
	public void setDoc(StyledDocument doc) {
		this.doc = doc;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public ImageIcon getIc() {
		return ic;
	}
	public void setIc(ImageIcon ic) {
		this.ic = ic;
	}
	
	public Message(String str) {
		this.str = str;
	}
	
	public Message(ImageIcon ic) {
		this.ic = ic;
	}
	
	public Message(String str,ImageIcon ic) {
		this.str = str;
		this.ic = ic;
		
	}

}
