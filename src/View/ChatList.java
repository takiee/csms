/*
 * ˽�Ķ���ѡ���б�
 * */
package View;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ClientHandle.ManageChat;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChatList extends JFrame implements MouseListener {

	private JPanel contentPanel,studentListPanel;
	private JLabel jb;
	private JScrollPane list;
	String schoolNum;

	public ChatList(String schoolNum) {
	 	setResizable(false);
		setVisible(true);
		this.schoolNum = schoolNum;
	 	setTitle(schoolNum);
		//�����һ�ſ�Ƭ
		contentPanel = new JPanel(new BorderLayout());
		//�ٶ���20������
		studentListPanel = new JPanel(new GridLayout(20,1,0,0));
		jb = new JLabel("˽�Ķ���ѡ��",JLabel.CENTER);
		jb.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		jb.setBackground(new Color(51,86,153));
		list = new JScrollPane(studentListPanel);
		//��studentListPanel��ʼ��20������
		JLabel []jbls = new JLabel[20];
		
		jbls[0] = new JLabel("�� 漣�201800301204��",new ImageIcon("image/1.jpg"),JLabel.LEFT);
		jbls[0].addMouseListener(this);
		studentListPanel.add(jbls[0]);
		
		jbls[1] = new JLabel("��������201800301043��",new ImageIcon("image/2.jpg"),JLabel.LEFT);
		jbls[1].addMouseListener(this);
		studentListPanel.add(jbls[1]);
		
		jbls[2] = new JLabel("������201800301061��",new ImageIcon("image/3.jpg"),JLabel.LEFT);
		jbls[2].addMouseListener(this);
		studentListPanel.add(jbls[2]);
		
		jbls[3] = new JLabel("�����루201800301079��",new ImageIcon("image/4.jpg"),JLabel.LEFT);
		jbls[3].addMouseListener(this);
		studentListPanel.add(jbls[3]);
		
		jbls[4] = new JLabel("�� 輣�201800301020��",new ImageIcon("image/5.jpg"),JLabel.LEFT);
		jbls[4].addMouseListener(this);
		studentListPanel.add(jbls[4]);
		
		jbls[5] = new JLabel("����ޣ�201800301032��",new ImageIcon("image/6.jpg"),JLabel.LEFT);
		jbls[5].addMouseListener(this);
		studentListPanel.add(jbls[5]);
		
		jbls[6] = new JLabel("�����壨201800301136��",new ImageIcon("image/7.jpg"),JLabel.LEFT);
		jbls[6].addMouseListener(this);
		studentListPanel.add(jbls[6]);
		
		jbls[7] = new JLabel("�����Σ�201800301222��",new ImageIcon("image/9.jpg"),JLabel.LEFT);
		jbls[7].addMouseListener(this);
		studentListPanel.add(jbls[7]);
		
		jbls[8] = new JLabel("�� �ԣ�201800301031��",new ImageIcon("image/10.jpg"),JLabel.LEFT);
		jbls[8].addMouseListener(this);
		studentListPanel.add(jbls[8]);
		
		jbls[9] = new JLabel("��С�ࣨ201800301206��",new ImageIcon("image/11.jpg"),JLabel.LEFT);
		jbls[9].addMouseListener(this);
		studentListPanel.add(jbls[9]);
		
		jbls[10] = new JLabel("Ѧ֮Ԩ��201800301343��",new ImageIcon("image/12.jpg"),JLabel.LEFT);
		jbls[10].addMouseListener(this);
		studentListPanel.add(jbls[10]);
		
		jbls[11] = new JLabel("�� ˧��201800301249��",new ImageIcon("image/13.jpg"),JLabel.LEFT);
		jbls[11].addMouseListener(this);
		studentListPanel.add(jbls[11]);
		
		jbls[12] = new JLabel("��������201800301181��",new ImageIcon("image/14.jpg"),JLabel.LEFT);
		jbls[12].addMouseListener(this);
		studentListPanel.add(jbls[12]);
		
		jbls[13] = new JLabel("�����201800301349��",new ImageIcon("image/15.jpg"),JLabel.LEFT);
		jbls[13].addMouseListener(this);
		studentListPanel.add(jbls[13]);
		
		jbls[14] = new JLabel("���㣨201800301113��",new ImageIcon("image/16.jpg"),JLabel.LEFT);
		jbls[14].addMouseListener(this);
		studentListPanel.add(jbls[14]);
		
		jbls[15] = new JLabel("�¼��أ�201800301219��",new ImageIcon("image/17.jpg"),JLabel.LEFT);
		jbls[15].addMouseListener(this);
		studentListPanel.add(jbls[15]);
		
		jbls[16] = new JLabel("��������201800301007��",new ImageIcon("image/18.jpg"),JLabel.LEFT);
		jbls[16].addMouseListener(this);
		studentListPanel.add(jbls[16]);
		
		jbls[17] = new JLabel("�̺���201800301188��",new ImageIcon("image/19.jpg"),JLabel.LEFT);
		jbls[17].addMouseListener(this);
		studentListPanel.add(jbls[17]);
		
		jbls[18] = new JLabel("��־����201800301149��",new ImageIcon("image/20.jpg"),JLabel.LEFT);
		jbls[18].addMouseListener(this);
		studentListPanel.add(jbls[18]);
		
		jbls[19] = new JLabel("�� �⣨201800301112��",new ImageIcon("image/8.jpg"),JLabel.LEFT);
		jbls[19].addMouseListener(this);
		studentListPanel.add(jbls[19]);
		
		for(int i = 0;i<jbls.length;i++) {
			jbls[i].setOpaque(true);
			jbls[i].setBackground(new Color(70,130,180));
		}

		
		contentPanel.add(jb,"North");
		contentPanel.add(list,"Center");
		getContentPane().add(contentPanel,"Center");
		this.setSize(287,437);
		this.setVisible(true);
	}

@Override
public void mouseClicked(MouseEvent e) {

	//��Ӧ�û�˫�����¼������õ����ѵı��
	if(e.getClickCount() == 2) {
		//�õ����ѱ��
		JLabel jl =(JLabel)e.getSource();
		jl.setForeground(new Color(135,206,235));
		String information = ((JLabel)e.getSource()).getText();
		String opName = information.substring(0,3);
		String opSchoolNum = information.substring(4, 16);
		System.out.println("��ϣ����"+opName+"����");
		
		Chat chat = ManageChat.getChat(schoolNum+" "+opSchoolNum);
		if(chat == null) {
			chat = new Chat(schoolNum,opSchoolNum);
			chat.setVisible(true);
			//�����������������
			ManageChat.addChat(schoolNum+" "+opSchoolNum, chat);
		} 
		chat.show();
	}
}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	JLabel jl =(JLabel)e.getSource();
	jl.setForeground(new Color(199,206,235));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel jl =(JLabel)e.getSource();
		jl.setForeground(Color.BLACK);
	}
}
