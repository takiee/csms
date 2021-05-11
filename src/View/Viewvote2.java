package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Database.VoteDao;
import Message.MessageForVote;

public class Viewvote2 extends JFrame  implements ActionListener{

	private JPanel contentPane,panel;
	private String schoolNum;
	private JTextArea jl;
	private JLabel rdbtnNewRadioButton,radioButton_1,radioButton_2,radioButton;
	private MessageForVote mfv;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;

	public Viewvote2(String getter,MessageForVote mfv){
		this.mfv = mfv;
		this.schoolNum = getter;
		VoteDao vd = new VoteDao();
		setVisible(true);
		setResizable(false);		
		setTitle("班级管理系统——查看投票");
		setBounds(100, 100, 450, 492);
		setLocation(100, 20);
		panel = new JPanel();
		contentPane = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 590,788);
		panel.setLayout(null);
		panel.add(contentPane);
		JLabel back = new JLabel(new ImageIcon("image/not.jpg"));
		back.setBounds(0, 0, 590, 788);
		panel.add(back);
		
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("班级投票");
		label.setBounds(10, 10, 434, 65);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setForeground(Color.white);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label);
		
		jl = new JTextArea();
		jl.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		jl.setBounds(10, 97, 429, 101);
		jl.setLineWrap(true);
		jl.setEditable(false);
		jl.setBackground(Color.BLACK);
		jl.setText(mfv.getSender()+": +\n"+mfv.getTitle());
		jl.setForeground(Color.white);
		jl.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		LineBorder lb = new LineBorder(new Color(115,134,153), 2, false); 
		jl.setBorder(BorderFactory.createTitledBorder(lb,null , TitledBorder.LEFT, TitledBorder.TOP));
		contentPane.add(jl);
		
		rdbtnNewRadioButton = new JLabel("A  "+mfv.getTypeA()+"   当前票数为:"+vd.getCount(mfv, 1));
		rdbtnNewRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		rdbtnNewRadioButton.setForeground(Color.white);
		rdbtnNewRadioButton.setBounds(20, 270, 419, 40);
		rdbtnNewRadioButton.setOpaque(false);
		contentPane.add(rdbtnNewRadioButton);
		
		radioButton = new JLabel("B  "+mfv.getTypeB()+"   当前票数为:"+vd.getCount(mfv, 2));
		radioButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radioButton.setForeground(Color.white);
		radioButton.setBounds(20, 312, 419, 40);
		radioButton.setOpaque(false);
		contentPane.add(radioButton);
		
		radioButton_1 = new JLabel("C  "+mfv.getTypeC()+"   当前票数为:"+vd.getCount(mfv, 3));
		radioButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radioButton_1.setForeground(Color.white);
		radioButton_1.setBounds(20, 354, 419, 40);
		radioButton_1.setOpaque(false);
		contentPane.add(radioButton_1);
		
		
		radioButton_2 = new JLabel("D  "+mfv.getTypeD()+"   当前票数为:"+vd.getCount(mfv, 4));
		radioButton_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		radioButton_2.setForeground(Color.white);
		radioButton_2.setBounds(20, 396, 419, 40);
		radioButton_2.setOpaque(false);
		contentPane.add(radioButton_2);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(390, 235, 18, 18);
		btnNewButton_1.setBackground(new Color(125,109,109));
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel_1 = new JLabel("\u67E5\u770B\u5F53\u524D\u9644\u52A0\u5EFA\u8BAE");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(247, 227, 133, 33);
		contentPane.add(lblNewLabel_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AdviceList ud = new AdviceList(schoolNum);
		VoteDao rd = new VoteDao();
		String[] fs = rd.getAdvice(mfv.getTitle());
		for(int i = 0;i<fs.length;i++) {
			ud.addAdvice(fs[i],""+(i+1));;
		}
	}

}
