package Message;

public class MessageForVote implements java.io.Serializable{

	private static final long serialVersionUID = 682L;
	private String sender;
	private String title;
	private String getter;
	private String typeA,typeB,typeC,typeD;
	private int countA,countB,countC,countD;
	public int getCountA() {
		return countA;
	}
	public void setCountA(int countA) {
		this.countA = countA;
	}
	public int getCountB() {
		return countB;
	}
	public void setCountB(int countB) {
		this.countB = countB;
	}
	public int getCountC() {
		return countC;
	}
	public void setCountC(int countC) {
		this.countC = countC;
	}
	public int getCountD() {
		return countD;
	}
	public void setCountD(int countD) {
		this.countD = countD;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTypeA() {
		return typeA;
	}
	public void setTypeA(String typeA) {
		this.typeA = typeA;
	}
	public String getTypeB() {
		return typeB;
	}
	public void setTypeB(String typeB) {
		this.typeB = typeB;
	}
	public String getTypeC() {
		return typeC;
	}
	public void setTypeC(String typeC) {
		this.typeC = typeC;
	}
	public String getTypeD() {
		return typeD;
	}
	public void setTypeD(String typeD) {
		this.typeD = typeD;
	}

}
