package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import Message.MessageForRecord;

public class RecordList extends JFrame {

	private JPanel contentPane,noticePane;
	private JScrollPane noticelist;
	private JLabel jb;
	private String schoolNum;

	public RecordList(String schoolNum) {
		setResizable(false);
		setVisible(true);
		this.schoolNum = schoolNum;
		setTitle(schoolNum);
	 	this.setVisible(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);
		
		contentPane.setBackground(new Color(115,118,153));
		noticePane = new JPanel(new GridLayout(20, 1, 3, 3));
		jb = new JLabel("查看管理员日志",JLabel.CENTER);
		jb.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		noticelist = new JScrollPane(noticePane);
		noticePane.setBackground(new Color(115,118,153));
		
		contentPane.add(jb,"North");
		contentPane.add(noticelist,"Center");
		this.setSize(697,418);
		
	}
	public void addRecord(MessageForRecord mfn) {
		String info = mfn.getBehave();
		JTextArea jl = new JTextArea();
		jl.setLineWrap(true);
		jl.setEditable(false);
		jl.setBackground(new Color(115,118,153));
		jl.setText(info);
		jl.setForeground(Color.black);
		jl.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		LineBorder lb = new LineBorder(new Color(115,134,153), 2, false); 
		jl.setBorder(BorderFactory.createTitledBorder(lb, mfn.getSchoolNum() , TitledBorder.LEFT, TitledBorder.TOP));
		noticePane.add(jl);
	}

}
