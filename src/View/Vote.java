package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import ClientHandle.ManageCliVoteThread;
import Database.RecordDao;
import Message.MessageForRecord;
import Message.MessageForVote;

public class Vote extends JFrame implements ActionListener{

	private JPanel contentPane,panel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextArea textArea;
	private String schoolNum;
	public Vote(String schoolNum) {
		this.schoolNum = schoolNum;
		setVisible(true);
		setResizable(false);
		setBounds(100, 100, 459, 635);
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
		
		JLabel lblNewLabel = new JLabel("∑¢≤ºÕ∂∆±");
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 18));
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setBounds(144, 23, 149, 57);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Õ∂∆±ƒ⁄»›£∫");
		lblNewLabel_1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(24, 89, 104, 43);
		lblNewLabel_1.setForeground(Color.white);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("…Ë÷√—°œÓ£∫");
		label.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 18));
		label.setBounds(13, 251, 104, 43);
		label.setForeground(Color.white);
		contentPane.add(label);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 18));
		textArea.setBounds(13, 128, 420, 100);
		textArea.setLineWrap(true);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("∑¢   ÀÕ");
		btnNewButton.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 18));
		btnNewButton.setBounds(315, 553, 116, 33);
		btnNewButton.addActionListener(this);
		btnNewButton.setForeground(Color.white);
		btnNewButton.setBackground(Color.BLACK);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 18));
		textField.setBounds(113, 265, 320, 43);
		textField.setBackground(new Color(220,220,220));
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(220,220,220));
		textField_1.setBounds(113, 335, 320, 43);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(113, 401, 320, 43);
		textField_2.setBackground(new Color(220,220,220));
		contentPane.add(textField_2);
		
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(113, 468, 320, 43);
		textField_3.setBackground(new Color(220,220,220));
		contentPane.add(textField_3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MessageForVote mfv = new MessageForVote();
		mfv.setSender(schoolNum);
		mfv.setTitle(textArea.getText());
		mfv.setTypeA(textField.getText());
		mfv.setTypeB(textField_1.getText());
		mfv.setTypeC(textField_2.getText());
		mfv.setTypeD(textField_3.getText());
		mfv.setCountA(0);
		mfv.setCountB(0);
		mfv.setCountC(0);
		mfv.setCountD(0);
		
		MessageForRecord mfr = new MessageForRecord();
		mfr.setSchoolNum(schoolNum);
	     Date dNow = new Date( );
	      SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		mfr.setBehave(" ±º‰£∫ "+ft.format(dNow)+"∑¢≤º¡ÀÕ∂∆±°£\nÕ∂∆±µƒ±ÍÃ‚Œ™"+textArea.getText());
		RecordDao rd = new RecordDao();
		rd.restoreRecord(mfr);
		this.dispose();
		JOptionPane.showMessageDialog(this, "∑¢ÀÕ≥…π¶£°");
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(ManageCliVoteThread.getClientVoteThread(schoolNum).getSoc6().getOutputStream());
			oos.writeObject(mfv);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
