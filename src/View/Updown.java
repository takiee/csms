package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import ClientHandle.ManageCliDownSoc;
import Message.MessageForFile;

public class Updown extends JFrame implements ActionListener {
	private JPanel contentPane,panel;
	private JTextField textField;
	private String schoolNum;
	private String fileName;
	private JButton button,button1;
	private String filePath;
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public static void main(String[] args) {
		new Updown("123","");
	}
	public Updown() {
		
	}
	public Updown(String schoolNum,String fileName) {
		this.schoolNum = schoolNum;
		this.fileName = fileName;
	 	setResizable(false);
		setVisible(true);
		setTitle("文件下载——"+schoolNum);
		setBounds(100, 100, 480,232);
		setLocation(300, 50);
		panel = new JPanel();
		contentPane = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 480,232);
		panel.setLayout(null);
		panel.add(contentPane);
		JLabel back = new JLabel(new ImageIcon("image/file.jpg"));
		back.setBounds(0, 0,  480,232);
		panel.add(back);
		
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		
		JLabel label = new JLabel("输入文件下载路径：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label.setForeground(Color.white);
		label.setBounds(10, 24, 276, 37);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(19, 70, 426, 47);
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(textField);
		textField.setColumns(10);
		
		button = new JButton("下  载");
		button.addActionListener(this);
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button.setBounds(357, 133, 95, 33);
		contentPane.add(button);
		
		button1 = new JButton("");
		button1.addActionListener(this);
		button1.setBackground(new Color(0,102,204));
		button1.setBounds(177, 34, 20, 20);
		contentPane.add(button1);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1) {
			JFileChooser jfc=new JFileChooser("请选择文件夹");  
	        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
	        jfc.showDialog(new JLabel(), "选择");  
	        File f=jfc.getSelectedFile();
	        if(f.isDirectory()){  
	        	textField.setText(f.getAbsolutePath());
	        }else if(f.isFile()){  
	            System.out.println("文件:"+f.getAbsolutePath()); 
	            JOptionPane.showMessageDialog(this, "不支持选择文件，请重新选择一个文件夹！");	
	        }  
	        System.out.println(jfc.getSelectedFile().getName()); 
		}
		if(e.getSource() == button) {
		filePath = textField.getText()+"/"+fileName;
		System.out.println(filePath);
		try {
			MessageForFile mff = new MessageForFile();
			mff.setFileName(fileName);
			mff.setFilePath(filePath);
			mff.setSchoolNum(schoolNum);
			ObjectOutputStream oos = new ObjectOutputStream(ManageCliDownSoc.getSoc(schoolNum).getOutputStream());
			oos.writeObject(mff);
			System.out.println("传文件信息到服务器");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	}
}
