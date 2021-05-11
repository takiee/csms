package View;
import java.util.Date;
import java.awt.*;
import java.text.SimpleDateFormat;
import Message.MessageForGroupChat;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import ClientHandle.ManageCliGroupChatThread;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class GroupChat extends JFrame implements ActionListener, MouseListener{
//	public static void main(String[] args) throws IOException {
//		new Chat("ss","ss").setVisible(true);;
//	}

	private JPanel contentPane;
	private JScrollPane tscoll,sscoll;
	private JTextPane sendText;
	private JTextPane textArea;
	private JButton send_button;
	private JLabel imag_button,draw_button;
    Color color;
    public Graphics2D g;
    JPanel colorPanel;
    public JComboBox<Integer> box;
    public int x1, y1;
    public BasicStroke strock;
	String schoolNum;
	String opSchoolNum;
	String opname;
	
	public JLabel getImag_button() {
		return imag_button;
	} 
	public void setImag_button(JLabel imag_button) {
		this.imag_button = imag_button;
	}
	public JLabel getDraw_button() {
		return draw_button;
	}
	public void setDraw_button(JLabel draw_button) {
		this.draw_button = draw_button;
	}
	
	
	public JTextPane getSendText() {
		return sendText;
	}
	public void setSendText(JTextPane sendText) {
		this.sendText = sendText;
	}
	
	public GroupChat(String schoolNum) {
		this.schoolNum = schoolNum;
		
		setTitle(schoolNum+"群聊");
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 826, 677);
		setLocation(10, 10);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextPane();
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textArea.setBounds(10, 12, 791, 368);
		tscoll = new JScrollPane(textArea);
		tscoll.setBounds(10, 12, 792, 369);
		tscoll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(tscoll);
		
		send_button = new JButton("发   送");
		send_button.addActionListener(this);
		
		JPanel sendpanel = new JPanel();
		sendpanel.setBounds(10, 388, 791, 206);
		contentPane.add(sendpanel);
		sendpanel.setLayout(null);
		
		sendText = new JTextPane();
		sendText.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		sendText.setBounds(0, 34, 791, 116);
		sscoll = new JScrollPane(sendText);
		sscoll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sscoll.setBounds(0, 38, 792, 168);;
		sendText.setVisible(true);
		sendpanel.add(sscoll);
		
		imag_button = new JLabel(new ImageIcon("image/111.jpg"));
		imag_button.setFont(new Font("微软雅黑", Font.PLAIN, 11));
		imag_button.setBounds(5,0, 40,40);
		imag_button.addMouseListener(this);
		sendpanel.add(imag_button);
		
		draw_button = new JLabel(new ImageIcon("image/27.jpg"));
		draw_button.setFont(new Font("微软雅黑", Font.PLAIN, 11));
		draw_button.addMouseListener(this);
		draw_button.setBounds(46, 0, 40,40);
		sendpanel.add(draw_button);
		send_button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		send_button.setBounds(555, 606, 110, 34);
		contentPane.add(send_button);
		
		JButton cancle_button = new JButton("取   消");
		cancle_button.addActionListener(this);
		cancle_button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		cancle_button.setBounds(692, 606, 110, 34);
		contentPane.add(cancle_button);
	
	}

	//显示消息
	public void appendView(MessageForGroupChat m) throws BadLocationException {
		 StyledDocument recDoc = textArea.getStyledDocument(); 
		 SimpleAttributeSet as = new SimpleAttributeSet();
		 String str = m.getSender() + "    " + m.getSendTime() + "\n";
		 recDoc.insertString(recDoc.getLength(), str, as);
		 int end = 0;
		 StyledDocument sd = m.getDoc();
		 while(end < sd.getLength()) {
			 Element e = sd.getCharacterElement(end);
			 SimpleAttributeSet as1 = new SimpleAttributeSet();
	         StyleConstants.setForeground(as1, StyleConstants.getForeground(e.getAttributes()));
	         StyleConstants.setFontSize(as1, StyleConstants.getFontSize(e.getAttributes()));
	         StyleConstants.setFontFamily(as1, StyleConstants.getFontFamily(e.getAttributes()));
			 str = e.getDocument().getText(end, e.getEndOffset() - end);
			 if("icon".equals(e.getName())) {
				recDoc.insertString(recDoc.getLength(), str, e.getAttributes());
	         }else {
	        	 	recDoc.insertString(recDoc.getLength(), str, new SimpleAttributeSet());
	         }
			 end = e.getEndOffset();
		 }
		 recDoc.insertString(recDoc.getLength(), "\n", as);
		 textArea.setCaretPosition(recDoc.getLength());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//监听发送按钮
		if(e.getSource() == send_button) {
			MessageForGroupChat mes = new MessageForGroupChat();
			mes.setSender(schoolNum);
			Date dNow = new Date();
		    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		    mes.setSendTime(ft.format(dNow));
		    mes.setDoc(sendText.getStyledDocument());
		    try {
				this.appendView(mes);
				ObjectOutputStream oos = new ObjectOutputStream
						((ManageCliGroupChatThread.getClientGroupChatThread(schoolNum)).getSoc1().getOutputStream());
				oos.writeObject(mes);
				System.out.println("群聊： 发送客户端输出流");
				sendText.setText("");
		     } catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		//监听表情按钮
				if(e.getSource() == imag_button) {
					new GroupChatbq(this).setVisible(true);;
		}
	}




    //鼠标监听器
    MouseAdapter ma = new MouseAdapter() {

        public void mousePressed(MouseEvent e) {
        }


        public void mouseEntered(MouseEvent e) {
        }


        public void mouseDragged(MouseEvent e) {

    }
};
@Override
public void mouseClicked(MouseEvent e) {
	if(e.getSource() == imag_button) {
		new GroupChatbq(GroupChat.this).setVisible(true);;
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
//	if(e.getSource() == imag_button) 
//		new cbqg(GroupChat.this).setVisible(true);
	
}
	

@Override
public void mouseExited(MouseEvent e) {
	
}}

//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.EventQueue;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.util.Date;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.border.EmptyBorder;
//
//import ClientHandle.ManageChat;
//import ClientHandle.ManageCliConServerThread;
//import ClientHandle.ManageCliGroupChatThread;
//import ClientHandle.ManageGroupChat;
//import Message.MessageForChat;
//import Message.MessageForGroupChat;
//
//public class GroupChat extends JFrame implements ActionListener{
//	
//	String schoolNum;
//	private JTextArea sendText;
//	public JTextArea textArea;
//
//	private JPanel contentPane;
//	private JScrollPane tscoll,sscoll;
//	
//	public static void main(String[] args) {
//		new GroupChat("").setVisible(true);
//	}
//	public GroupChat(String schoolNum) {
//		this.schoolNum = schoolNum;
//		setTitle("群聊");
//		setResizable(false);
//		setBounds(100, 100, 826, 623);
//		
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		textArea = new JTextArea();
//		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		textArea.setBounds(10, 10, 791, 368);
//		tscoll = new JScrollPane(textArea);
//		tscoll.setBounds(10, 10, 792, 369);
//		tscoll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		textArea.setLineWrap(true);
//		textArea.setWrapStyleWord(true);
//		
//		JLabel jl = new JLabel("hhhhh");
//		jl.setBackground(Color.black);
//		textArea.add(jl);
//		
//		contentPane.add(tscoll);
//		
//		JButton send_button = new JButton("发   送");
//		send_button.addActionListener(this);
//		
//		JPanel sendpanel = new JPanel();
//		sendpanel.setBounds(10, 388, 791, 150);
//		contentPane.add(sendpanel);
//		sendpanel.setLayout(null);
//		
//		sendText = new JTextArea();
//		sendText.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		sendText.setBounds(0, 34, 791, 116);
//		sscoll = new JScrollPane(sendText);
//		sscoll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		sscoll.setBounds(0, 34, 792, 117);;
//		sendText.setVisible(true);
//		sendText.setLineWrap(true);
//		sendText.setWrapStyleWord(true);
//		sendpanel.add(sscoll);
//		
//		JButton imag_button = new JButton("图片");
//		imag_button.setFont(new Font("微软雅黑", Font.PLAIN, 11));
//		imag_button.setBounds(0, 1, 72, 23);
//		sendpanel.add(imag_button);
//		
//		JButton draw_button = new JButton("手绘");
//		draw_button.setFont(new Font("微软雅黑", Font.PLAIN, 11));
//		draw_button.addActionListener(this);
//		draw_button.setBounds(85, 1, 72, 23);
//		sendpanel.add(draw_button);
//		send_button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		send_button.setBounds(550, 548, 110, 34);
//		contentPane.add(send_button);
//		
//		JButton cancle_button = new JButton("取   消");
//		cancle_button.addActionListener(this);
//		cancle_button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		cancle_button.setBounds(677, 548, 110, 34);
//		contentPane.add(cancle_button);
//	}
//	
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		String tex =( (JButton)e.getSource()).getText();
//
//		if(tex.equals("发   送")) {
//			
//			MessageForGroupChat mfgc = new MessageForGroupChat();
//			mfgc.setSender(schoolNum);
//			mfgc.setContent(sendText.getText());
//			mfgc.setSendTime(new Date().toString());
//			showMessageInMy(mfgc);//在自己的聊天界面里显示消息
//
//			//发送服务器
//			try {
//				ObjectOutputStream oos = new ObjectOutputStream
//						(ManageCliGroupChatThread.getClientGroupChatThread(schoolNum).getSoc1().getOutputStream());
//				oos.writeObject(mfgc);
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			sendText.setText("");
//		}
//	}
//
//	//显示消息
//		public void  showMessageInOP(MessageForGroupChat mes) {
//			String info = mes.getSendTime()+":  "+mes.getSender()+"对所有人说："+mes.getContent()+"\r\n";
//			ManageGroupChat.getGroupChat(schoolNum).textArea.append(info);
//		//	ManageGroupChat.getGroupChat(schoolNum).textArea.append("hhha");
//
//		}
//		public void  showMessageInMy(MessageForGroupChat mes) {
//			String info = mes.getSendTime()+":  "+mes.getSender()+"对所有人说："+mes.getContent()+"\r\n";
//			this.textArea.append(info);
//			
//		}
//}
