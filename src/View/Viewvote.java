package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Database.VoteDao;
import Message.MessageForVote;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Viewvote extends JFrame implements ItemListener,ActionListener{

	private JPanel contentPane,panel;
	private String schoolNum;
	private JTextArea jl;
	private JRadioButton rdbtnNewRadioButton,radioButton_1,radioButton_2,radioButton;
	private MessageForVote mfv;
	private JTextArea textArea;
	private JButton btnNewButton,btnNewButton_1;
	private static int i = 0;
	
	public Viewvote(String getter,MessageForVote mfv) {
		this.mfv = mfv;
		this.schoolNum = getter;
		setVisible(true);
		setResizable(false);
		setTitle("°à¼¶¹ÜÀíÏµÍ³¡ª¡ª²é¿´Í¶Æ±");
		setBounds(100, 100, 450, 665);
		setLocationRelativeTo(null);
		panel = new JPanel();
		contentPane = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 444,788);
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
		
		JLabel label = new JLabel("°à¼¶Í¶Æ±");
		label.setBounds(5, 6, 434, 65);
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		label.setForeground(Color.white);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label);
		
		jl = new JTextArea();
		jl.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		jl.setBounds(10, 97, 429, 141);
		jl.setLineWrap(true);
		jl.setEditable(false);
		jl.setBackground(Color.BLACK);
		jl.setText(mfv.getSender()+":  \n"+mfv.getTitle());
		jl.setForeground(Color.white);
		jl.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		LineBorder lb = new LineBorder(new Color(115,134,153), 2, false); 
		jl.setBorder(BorderFactory.createTitledBorder(lb,null , TitledBorder.LEFT, TitledBorder.TOP));
		contentPane.add(jl);
		
		rdbtnNewRadioButton = new JRadioButton(mfv.getTypeA());
		rdbtnNewRadioButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		rdbtnNewRadioButton.setForeground(Color.white);
		rdbtnNewRadioButton.setBounds(20, 270, 419, 40);
		rdbtnNewRadioButton.setOpaque(false);
		contentPane.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.addItemListener(this);
		
		radioButton = new JRadioButton(mfv.getTypeB());
		radioButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		radioButton.setForeground(Color.white);
		radioButton.setBounds(20, 312, 419, 40);
		radioButton.setOpaque(false);
		contentPane.add(radioButton);
		radioButton.addItemListener(this);
		
		radioButton_1 = new JRadioButton(mfv.getTypeC());
		radioButton_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		radioButton_1.setForeground(Color.white);
		radioButton_1.setBounds(20, 354, 419, 40);
		radioButton_1.setOpaque(false);
		contentPane.add(radioButton_1);
		radioButton_1.addItemListener(this);
		
		
		radioButton_2 = new JRadioButton(mfv.getTypeD());
		radioButton_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		radioButton_2.setForeground(Color.white);
		radioButton_2.setBounds(20, 396, 419, 40);
		radioButton_2.setOpaque(false);
		contentPane.add(radioButton_2);
		radioButton_2.addItemListener(this);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 442, 429, 123);
		contentPane.add(panel1);
		panel1.setLayout(null);
		panel1.setOpaque(false);
		
		textArea = new JTextArea();
		textArea.setBounds(20, 65, 382, 43);
		textArea.setLineWrap(true);
		panel1.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Ìí¼Ó²¹³ä½¨Òé£º");
		lblNewLabel_1.setBounds(10, 10, 133, 43);
		lblNewLabel_1.setForeground(Color.white);
		panel1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(390, 23, 18, 15);
		btnNewButton_1.setBackground(new Color(125,109,109));
		btnNewButton_1.addActionListener(this);
		panel1.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u67E5\u770B\u9644\u52A0\u5EFA\u8BAE");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(272, 10, 108, 32);
		panel1.add(lblNewLabel_2);
		
		btnNewButton = new JButton("È·ÈÏ");
		btnNewButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.black);
		btnNewButton.setBounds(273, 575, 129, 40);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewButton) {
	 		VoteDao vd = new VoteDao();
			vd.countType(mfv, i);
			vd.restoreAdvice(mfv.getTitle(),textArea.getText());
			JOptionPane.showMessageDialog(this, "Í¶Æ±³É¹¦£¡");	
			this.dispose();
			new Viewvote2(schoolNum,mfv);
		}
		if(e.getSource() == btnNewButton_1) {
			
			AdviceList ud = new AdviceList(schoolNum);
			VoteDao rd = new VoteDao();
			String[] fs = rd.getAdvice(mfv.getTitle());
			for(int i = 0;i<fs.length;i++) {
				ud.addAdvice(fs[i],""+(i+1));;
			}
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getSource() == rdbtnNewRadioButton) {
			i = 1;
		}
		if(e.getSource() == radioButton) {
			i = 2;
		}
		if(e.getSource() == radioButton_1) {
			i = 3;
		}
		if(e.getSource() == radioButton_2) {
			i = 4;
		}
	}
}
