package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Database.VoteDao;
import Message.MessageForVote;

public class VoteList extends JFrame implements MouseListener {

	private JPanel contentPane,filePane;
	private JScrollPane fileList;
	private JLabel jb;
	private String schoolNum;

	public VoteList(String schoolNum) {
		this.schoolNum = schoolNum;
		setResizable(false);
		setVisible(true);
		setTitle("查看投票——"+schoolNum);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);
		contentPane.setBackground(new Color(115,118,153));
		filePane = new JPanel(new GridLayout(15,1,4,4));
		jb = new JLabel("当前列表",JLabel.CENTER);
		jb.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		fileList = new JScrollPane(filePane);
		filePane.setBackground(new Color(115,118,153));
		contentPane.add(jb,"North");
		contentPane.add(fileList,"Center");
		this.setSize(287,418);
	}
	public void addVote(MessageForVote mfv) {
		JTextArea jl = new JTextArea();
		jl.setLineWrap(true);
		jl.setEditable(false);
		jl.setBackground(new Color(115,118,153));
		jl.setText(mfv.getTitle());
		jl.setForeground(Color.BLACK);
		jl.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		LineBorder lb = new LineBorder(new Color(115,134,153), 2, false); 
		jl.setBorder(BorderFactory.createTitledBorder(lb, mfv.getSender() , TitledBorder.LEFT, TitledBorder.TOP));
		filePane.add(jl);
		jl.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount() == 2) {
			JTextArea jl =(JTextArea)e.getSource();
			jl.setBackground(new Color(153,180,209));
			jl.setForeground(new Color(105,105,105));
			String title = ((JTextArea)e.getSource()).getText();
			MessageForVote mfv = new MessageForVote();
			VoteDao vd =new VoteDao();
			mfv = vd.getVote(title);
			new Viewvote2(schoolNum,mfv);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

		JTextArea jl =(JTextArea)e.getSource();
		jl.setForeground(Color.white);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JTextArea jl =(JTextArea)e.getSource();
		jl.setForeground(Color.BLACK);
	}

}
