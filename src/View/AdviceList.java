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

public class AdviceList extends JFrame {

	private JPanel contentPane,noticePane;
	private JScrollPane noticelist;
	private JLabel jb;
	private String schoolNum;
	
	public static  void  main(String[] args) {
		new AdviceList("");
	}
 
	public AdviceList(String schoolNum){
		this.schoolNum = schoolNum;
		setVisible(true);
		setResizable(false);
		this.schoolNum = schoolNum;
		setTitle(schoolNum);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);
			
		contentPane.setBackground(new Color(115,118,153));
		noticePane = new JPanel(new GridLayout(20, 1, 3, 3));
		noticePane.setBounds(100, 100, 450, 300);
		jb = new JLabel("²é¿´¸½¼Ó½¨Òé",JLabel.CENTER);
		jb.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		noticelist = new JScrollPane(noticePane);
		noticePane.setBackground(new Color(115,118,153));
		noticelist.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(jb,"North");
		contentPane.add(noticelist,"Center");
		}
	
		public void addAdvice(String info ,String i) {
			JTextArea jl = new JTextArea();
			jl.setLineWrap(true);
			jl.setEditable(false);
			jl.setBackground(new Color(153,180,209));
			jl.setText(info);
			jl.setForeground(Color.BLACK);
			jl.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
			LineBorder lb = new LineBorder(new Color(115,134,153), 2, false); 
			jl.setBorder(BorderFactory.createTitledBorder(lb, i , TitledBorder.LEFT, TitledBorder.TOP));
			noticePane.add(jl);
		}
	

}
