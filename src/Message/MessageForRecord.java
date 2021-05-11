package Message;

public class MessageForRecord  implements java.io.Serializable{

	private static final long serialVersionUID = 908L;
	private String schoolNum;
	private String behave;
	public String getSchoolNum() {
		return schoolNum;
	}
	public void setSchoolNum(String schoolNum) {
		this.schoolNum = schoolNum;
	}
	public String getBehave() {
		return behave;
	}
	public void setBehave(String behave) {
		this.behave = behave;
	}
}
