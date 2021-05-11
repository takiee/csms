package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClientHandle.ManageGroupChat;
import Database.FileDao;
import Database.ManageDao;
import Database.NoticeDao;
import Database.VoteDao;
import Message.MessageForNotice;
import Message.MessageForVote;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainView extends JFrame implements ActionListener,MouseListener{

	private JPanel contentPane,panel;
	private String schoolNum;
	
	public static void main(String[] args) {
		new MainView("ssss");
	}
	public MainView(String schoolNum) {
		
	 	this.schoolNum = schoolNum;
		setVisible(true);
		setResizable(false);
		setTitle("班级管理系统——"+schoolNum);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 600);
		setLocation(25, 25);
		contentPane = new JPanel();
		panel = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 1020, 600);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBounds(0, 0,  1020, 600);
		contentPane.add(panel);
		
		JLabel back = new JLabel(new ImageIcon("image/main.png"));
		back.setBounds(0, 0,  1020, 600);
		contentPane.add(back);
		
		JLabel qun = new JLabel(new ImageIcon("image/球.JPG"));
		qun.setBounds(10, 35,  100, 90);
		panel.add(qun);
		
		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
//		
		JButton exit = new JButton("登录");
		ImageIcon im = new ImageIcon("image/登录关闭.JPG");
		exit.setIcon(im);
		exit.addActionListener(this);
		exit.setBackground(Color.black);
		exit.setBorder(null);
		exit.setBounds(950, 20, im.getIconWidth(), im.getIconHeight());
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(exit);
		
		JButton notice = new JButton();
		notice.setBackground(new Color(51,102,153));
		notice.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		notice.addActionListener(this);
		notice.setBounds(130, 180, 154, 54);
		notice.setText("查看公告");
		panel.add(notice);
		
		JButton vote = new JButton("查看投票");
		vote.setBackground(new Color(51,102,153));
		vote.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		vote.addActionListener(this);
		vote.setBounds(130, 260, 154, 54);
		panel.add(vote);
		
		JButton upload = new JButton("文件上传");
		upload.setBackground(new Color(51,102,153));
		upload.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		upload.setBounds(130, 340, 154, 54);
		panel.add(upload);
		upload.addActionListener(this);
		
		JButton updown = new JButton("文件下载");
		updown.setBackground(new Color(51,102,153));
		updown.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		updown.setBounds(130, 420, 154, 54);
		panel.add(updown);
		updown.addActionListener(this);
		
		JButton chat = new JButton("私聊");
		chat.setBackground(new Color(51,102,153));
		chat.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		chat.setBounds(614, 447, 154, 54);
		panel.add(chat);
		chat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChatList(schoolNum).setVisible(true);}
		});
		
		JButton groupChat = new JButton("群聊");
		groupChat.setBackground(new Color(51,102,153));
		groupChat.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		groupChat.addActionListener(this);
		groupChat.setBounds(809, 447, 154, 54);
		panel.add(groupChat);
		
		ManageDao dao = new ManageDao();
		dao.register();
		for(int i = 0;i<ManageDao.array.length;i++) {
			String exist =ManageDao.array[i];
			System.out.println(exist);
			if(schoolNum.equals(exist)){
				JButton button_1 = new JButton("管理员登录");
				button_1.addActionListener(this);
				button_1.setBounds(115, 85, 103, 23);
				button_1.setForeground(new Color(51,102,113));
				button_1.setBackground(Color.black);
				panel.add(button_1);
			}	
		}
		
		JLabel lchat = new JLabel(new ImageIcon("image/群聊.jpg"));
		lchat.setBounds(400, 20,  699, 392);
		panel.add(lchat);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String txt = ((JButton)e.getSource()).getText();
		if(txt.equals("退出登录"))
		{
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.dispose();
		}
		else if(txt.equals("查看公告")) {
	 		NoticeList nl = new NoticeList(schoolNum);
			NoticeDao nd = new NoticeDao();
			MessageForNotice[] mfns = nd.getNotice("0");
			for(int i = 0;i<mfns.length;i++) {
				nl.addNotice(mfns[i]);
			}
		}
		else if(txt.equals("查看投票")) {
			VoteList ud = new VoteList(schoolNum);
			VoteDao fd = new VoteDao();
			MessageForVote[] fs = fd.getVote();
			for(int i = 0;i<fs.length;i++) {
				ud.addVote(fs[i]);;
			}
			new VoteList(schoolNum).setVisible(true);;
		}
		else if(txt.equals("管理员登录")) {
			new ManageView(schoolNum).setVisible(true);;
		}
		
		else if(txt.equals("群聊")) {
			GroupChat gc = ManageGroupChat.getGroupChat(schoolNum);
			if(gc == null) {
				gc = new GroupChat(schoolNum);
				gc.setVisible(true);
				ManageGroupChat.addGroupChat(schoolNum, gc);
			}
			gc.show();
			
		}
		else if(txt.endsWith("文件上传")) {
			Upload ul = new Upload(schoolNum);
			
		}
		else if(txt.equals("文件下载")){
			FileList ud = new FileList(schoolNum);
			FileDao fd = new FileDao();
			String[] fs = fd.getFileList();
			for(int i = 0;i<fs.length;i++) {
				ud.addFile(fs[i]);;
			}
		}
	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
