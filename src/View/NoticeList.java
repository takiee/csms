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
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import Message.MessageForNotice;

public class NoticeList extends JFrame {

	private JPanel contentPane,noticePane;
	private JScrollPane noticelist;
	private JLabel jb;
	private String schoolNum;

	public NoticeList(String schoolNum) {
		setResizable(false);
		this.schoolNum = schoolNum;
		setTitle(schoolNum);
	 	this.setVisible(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);
		
		contentPane.setBackground(new Color(115,118,153));
		noticePane = new JPanel(new GridLayout(20, 1, 3, 3));
		jb = new JLabel("²é¿´¹«¸æ",JLabel.CENTER);
		jb.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		noticelist = new JScrollPane(noticePane);
		noticePane.setBackground(new Color(115,118,153));
		noticelist.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		contentPane.add(jb,"North");
		contentPane.add(noticelist,"Center");
		this.setSize(287,418);
		
	}
	public void addNotice(MessageForNotice mfn) {
		String info = mfn.getNoticeContent();
		JTextArea jl = new JTextArea();
		jl.setLineWrap(true);
		jl.setEditable(false);
		jl.setBackground(new Color(115,118,153));
		jl.setText(info);
		jl.setForeground(Color.black);
		jl.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		LineBorder lb = new LineBorder(new Color(115,134,153), 2, false); 
		jl.setBorder(BorderFactory.createTitledBorder(lb, mfn.getNoticer() , TitledBorder.LEFT, TitledBorder.TOP));
		noticePane.add(jl);
		
	}
}
