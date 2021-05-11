package View;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ClientHandle.ManageCliGetNoticeThread;
import Database.RecordDao;
import Message.MessageForNotice;
import Message.MessageForRecord;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Notice extends JFrame implements ActionListener{

	private JPanel contentPane,panel;
	private JTextField textField;
	private JTextArea textArea;
	private String schoolNum;

	public Notice(String schoolNum) {
		this.schoolNum = schoolNum;
		setVisible(true);
		setResizable(false);
		setBounds(100, 100, 490, 588);
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
		
		JLabel lblNewLabel = new JLabel("发布公告");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setBounds(144, 23, 149, 57);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("标题：");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(24, 89, 104, 43);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("内容：");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(24, 131, 104, 43);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField.setBounds(79, 90, 371, 43);
		textField.setBackground(new Color(220,220,220));
		contentPane.add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textArea.setLineWrap(true);
		textArea.setBounds(24, 176, 443, 288);
		textArea.setBackground(new Color(220,220,220));
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("发   送");
		btnNewButton.setBackground(Color.black);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnNewButton.setBounds(334, 483, 116, 33);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MessageForNotice mes = new MessageForNotice();
		mes.setNoticer(schoolNum);
		mes.setTitle(textField.getText());
		mes.setNoticeContent(textArea.getText());
		Date dNow = new Date();
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		mes.setNoticeTime(ft.format(dNow));
		JOptionPane.showMessageDialog(this, "发送成功！");	
		this.dispose();
		MessageForRecord mfr = new MessageForRecord();
		mfr.setSchoolNum(schoolNum);
	     Date dNow1 = new Date( );
	      SimpleDateFormat ft1 = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
	 
		mfr.setBehave("时间： "+ft1.format(dNow1)+"发布了公告。\n投票的标题为"+textField.getText());
		RecordDao rd = new RecordDao();
		rd.restoreRecord(mfr);
		//发送服务器
		try {		
			System.out.println("notice: "+(ManageCliGetNoticeThread.getClientGetNoticeThread(schoolNum)).getSoc2());
			ObjectOutputStream oos = new ObjectOutputStream
					((ManageCliGetNoticeThread.getClientGetNoticeThread(schoolNum)).getSoc2().getOutputStream());
			oos.writeObject(mes);
			System.out.println(1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
