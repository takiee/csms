package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import ClientHandle.ManageUpdown;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;

public class FileList extends JFrame implements MouseListener{

	private JPanel contentPane,filePane;
	private JScrollPane fileList;
	private JLabel jb;
	private String schoolNum;

	public FileList(String schoolNum) {
		this.schoolNum = schoolNum;
		setResizable(false);
		setVisible(true);
		setTitle("文件下载——"+schoolNum);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);
		contentPane.setBackground(new Color(115,118,153));
		filePane = new JPanel(new GridLayout(15,1,4,4));
		jb = new JLabel("文件下载列表",JLabel.CENTER);
		jb.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		fileList = new JScrollPane(filePane);
		filePane.setBackground(new Color(115,118,153));
		contentPane.add(jb,"North");
		contentPane.add(fileList,"Center");
		this.setSize(287,418);
	}
	public void addFile(String fileName) {
		JLabel jl = new JLabel();
		jl.setText(fileName);
		jl.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		LineBorder lb = new LineBorder(new Color(115,134,153), 1, false); 
		jl.setBorder(BorderFactory.createTitledBorder(lb,"11", TitledBorder.LEFT, TitledBorder.TOP));
		filePane.add(jl);
		jl.addMouseListener(this);
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount() == 2) {
			JLabel jl =(JLabel)e.getSource();
			jl.setBackground(new Color(115,134,153));
			jl.setForeground(new Color(105,105,105));
			String fileName = ((JLabel)e.getSource()).getText();
			Updown up = new Updown(schoolNum,fileName);
			ManageUpdown.addUpdown(schoolNum,up);
		}
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

		JLabel jl =(JLabel)e.getSource();
		jl.setForeground(Color.white);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl =(JLabel)e.getSource();
		jl.setForeground(Color.BLACK);
	}


}
