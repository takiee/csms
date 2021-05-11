package View;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import ClientHandle.ManageCliUpFileThread;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.event.ActionEvent;

public class Upload extends JFrame implements ActionListener{

	private JPanel contentPane,panel;
	private JTextField textField;
	private JButton button,button1;
	String schoolNum;
	public static void main(String[] args) {
		new Upload("123");
	}
	public Upload(String schoolNum) {
		this.schoolNum = schoolNum;
	 	setResizable(false);
		setVisible(true);
		setTitle("文件上传——"+schoolNum);
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
		
		JLabel label = new JLabel("输入文件路径：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label.setForeground(Color.white);
		label.setBounds(10, 24, 276, 37);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(19, 70, 426, 47);
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(textField);
		textField.setColumns(10);
		
		button = new JButton("上  传");
		button.addActionListener(this);
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button.setBounds(357, 133, 95, 33);
		contentPane.add(button);
		
		button1 = new JButton("");
		button1.addActionListener(this);
		button1.setBackground(new Color(0,102,204));
		button1.setBounds(157, 34, 20, 20);
		contentPane.add(button1);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1) {

			JFileChooser jfc=new JFileChooser("请选择文件");  
	        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
	        jfc.showDialog(new JLabel(), "选择");  
	        File f=jfc.getSelectedFile();
	        
	        if(f.isDirectory()){  
	        	JOptionPane.showMessageDialog(this, "不支持选择文件夹，请重新选择文件！");	
	        }else if(f.isFile()){  
	            System.out.println("文件:"+f.getAbsolutePath()); 
	            textField.setText(f.getAbsolutePath());
	        }  
	        System.out.println(jfc.getSelectedFile().getName()); 
		}
		
		if(e.getSource() == button) {
			
			try {
				File file = new File(textField.getText());
				String fileName = file.getName();
				System.out.println("文件长度："+(int)file.length());
			
				InputStream is = new FileInputStream(file);
				DataOutputStream dos = new DataOutputStream(ManageCliUpFileThread.getClientUpFileThreadd(schoolNum).getSoc3().getOutputStream());
				dos.flush();
				dos.writeUTF(fileName);
				dos.flush();
				int bufferSize = 8192;
				byte[] buf = new byte[bufferSize];
				int length = 0;
				while((length = is.read(buf)) != -1) {
					dos.write(buf,0,length);
				}
				dos.flush();
				JOptionPane.showMessageDialog(this, "上传成功！");	
				this.dispose();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		}
		
		
		
	}
}
