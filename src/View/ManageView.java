package View;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Database.RecordDao;
import Message.MessageForRecord;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageView extends JFrame {

	private JPanel contentPane,panel;
	private String schoolNum;
	public ManageView(String schoolNum) {
		this.schoolNum = schoolNum;
		setResizable(false);
		setTitle("管理员（"+schoolNum+"）登录");
		setVisible(true);
		setBounds(100, 100, 450, 629);
		setLocation(100, 20);
		panel = new JPanel();
		contentPane = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 450, 674);
		panel.setLayout(null);
		panel.add(contentPane);
		JLabel back = new JLabel(new ImageIcon("image/99.jpg"));
		back.setBounds(0, 0, 450, 674);
		panel.add(back);
		
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 430, 70);
		contentPane.add(panel_1);
		panel_1.setOpaque(false);
		panel_1.setLayout(null);
	
		JLabel lbll = new JLabel("\u7BA1\u7406\u5458\u83DC\u5355");
		lbll.setHorizontalAlignment(SwingConstants.CENTER);
		lbll.setBounds(73, 10, 266, 55);
		lbll.setBackground(new Color(77,13,109));
		lbll.setForeground(new Color(153,153,255));
		lbll.setFont(new Font("黑体", Font.PLAIN, 33));
		panel_1.add(lbll);
		
		JButton button = new JButton("发布投票");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button.setBounds(29, 501, 179, 60);
		button.setBackground(new Color(107,64,130));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Vote(schoolNum).setVisible(true);
			}
		});
		
		contentPane.add(button);
		
		JButton button_1 = new JButton("发布公告");
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_1.setBounds(246, 501, 179, 60);
		button_1.setBackground(new Color(107,64,130));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Notice(schoolNum).setVisible(true);
			}
		});
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("查看管理员日志");
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_2.setBounds(261, 90, 179, 43);
		button_2.setBackground(new Color(91,64,130));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecordList ud = new RecordList(schoolNum);
				RecordDao rd = new RecordDao();
				MessageForRecord[] fs = rd.getRecord();
				for(int i = 0;i<fs.length;i++) {
					ud.addRecord(fs[i]);;
				}
			}
		});
		contentPane.add(button_2);
	}
}
