package Message;

public class MessageForNotice implements java.io.Serializable{
	private static final long serialVersionUID = 657L;
	private String noticer;
	private String title;
	private String noticeContent;
	private String noticeTime;
	private String getter;

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getNoticer() {
		return noticer;
	}

	public void setNoticer(String noticer) {
		this.noticer = noticer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}
}
