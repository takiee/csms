package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Message.MessageForNotice;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Viewnotice extends JFrame {

	private JPanel contentPane,panel;
	private JLabel lblNewLabel;
	private JTextArea jl ;
	public Viewnotice(String schoolNum) {
		setVisible(true);
		setResizable(false);
		setTitle("\u73ED\u7EA7\u7BA1\u7406\u7CFB\u7EDF\u2014\u2014\u67E5\u770B\u516C\u544A");
		setBounds(100, 100, 528, 392);
		setLocationRelativeTo(null);
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
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setBounds(32, 31, 455, 81);
		contentPane.add(lblNewLabel);
		
		jl = new JTextArea("");
		jl.setBackground(Color.PINK);
		jl.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		jl.setBounds(32, 143, 455, 184);
		contentPane.add(jl);
	}
	public void  showNoticeMessage(MessageForNotice mfn) {
		String title = mfn.getNoticeTime()+"   "+mfn.getTitle();
		String info = mfn.getNoticeContent();
		lblNewLabel.setText(title);
		lblNewLabel.setOpaque(false);
		lblNewLabel.setForeground(Color.white);
		
		jl.setLineWrap(true);
		jl.setEditable(false);
		jl.setBackground(Color.BLACK);
		jl.setText(info);
		jl.setForeground(Color.white);
		jl.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		LineBorder lb = new LineBorder(new Color(115,134,153), 2, false); 
		jl.setBorder(BorderFactory.createTitledBorder(lb,null , TitledBorder.LEFT, TitledBorder.TOP));
		
	}
}
