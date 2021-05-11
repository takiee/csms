package View;
import ClientHandle.ClientHandleUser;
import bean.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class Register extends JFrame implements ActionListener,java.io.Serializable{
	private JPanel contentPaneOfRegister,jp;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton registerButt;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
			 	} catch (Exception e) {
					System.out.println("登录处抛出异常");
					e.printStackTrace();
				}
			}
		});
	}

	public Register() {
		setUndecorated(true);
		setVisible(true);
		setAlwaysOnTop(true);
		setResizable(false); 
		
		setTitle("班级管理系统——登录");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 570);
		setLocation(225, 45);
//		this.setUndecorated(true);
		jp = new JPanel();
		contentPaneOfRegister = new JPanel();
		
		jp.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(jp);
		jp.setLayout(null);
		contentPaneOfRegister.setBounds(0, 0, 790, 590);
		jp.add(contentPaneOfRegister);
		JLabel back = new JLabel(new ImageIcon("image/登录1.jpg"));
		back.setBounds(0, 0, 790, 590);
		jp.add(back);
		
		contentPaneOfRegister.setOpaque(false);
		contentPaneOfRegister.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneOfRegister.setLayout(null);
		
		JButton exit = new JButton("登录");
		ImageIcon im = new ImageIcon("image/登录关闭.JPG");
		exit.setIcon(im);
		exit.addActionListener(this);
		exit.setBackground(Color.black);
		exit.setBorder(null);
		exit.setBounds(722, 20, im.getIconWidth(), im.getIconHeight());
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		contentPaneOfRegister.add(exit);
		
		JLabel schoolNumber = new JLabel("学号 :");
		schoolNumber.setFont(new Font("黑体", Font.PLAIN, 30));
		schoolNumber.setForeground(new Color(105,105,105));
		schoolNumber.setBounds(120, 200, 150, 85);
		contentPaneOfRegister.add(schoolNumber);
		
		
		textField = new JTextField();
		textField.setBackground(new Color(220,220,220));
		textField.setFont(new Font("宋体", Font.PLAIN, 30));
		textField.setBounds(240, 220, 360, 55);
		contentPaneOfRegister.add(textField);
		textField.setColumns(10);
		
		JLabel password = new JLabel("密码 : ");
		password.setFont(new Font("黑体", Font.PLAIN, 30));
		password.setForeground(new Color(105,105,105));
		password.setBounds(120, 270, 285, 124);
		contentPaneOfRegister.add(password);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(220,220,220));
		passwordField.setFont(new Font("宋体", Font.PLAIN, 30));
		passwordField.setBounds(240, 312, 360, 54);
		contentPaneOfRegister.add(passwordField);
		
		registerButt = new JButton("登录");
		registerButt.setIcon(new ImageIcon("image/登录4.jpg"));
		registerButt.setBackground(SystemColor.info);
		registerButt.addActionListener(this);
		registerButt.setFont(new Font("黑体", Font.PLAIN, 30));
		registerButt.setBounds(308, 400, 130, 65);
		contentPaneOfRegister.add(registerButt);
	
	} 

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == registerButt)
		{
			ClientHandleUser chs = new ClientHandleUser();
			System.out.println(11);
			Student stu = new Student(textField.getText().trim(),passwordField.getText());
			if(chs.checkStudent(stu).equals("1")) {
			 	System.out.println("new mainView");
				new MainView(textField.getText().trim()).setVisible(true);
				this.dispose();
			}else if(chs.checkStudent(stu).equals("2")){
				JOptionPane.showMessageDialog(this, "用户名不存在！");		
			}else if (chs.checkStudent(stu).equals("3")){
				JOptionPane.showMessageDialog(this, "用户名或密码错误！");
			}else {
				JOptionPane.showMessageDialog(this, "未知错误或网络连接失误！");
			}
		}
	}


}
