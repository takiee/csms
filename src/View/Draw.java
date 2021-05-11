package View;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Draw extends JFrame  implements MouseListener, MouseMotionListener, ActionListener {

    Color color;
    public Graphics2D g;
    JPanel colorPanel;
    public JComboBox<Integer> box;
    public int x1, y1;
    public BasicStroke strock;
	String schoolNum;
	String opSchoolNum;
	String opname;
	JPanel centerPanel;
	Chat chat;
	int i = 1;
	/*************************************************************************************************************
	 * 即时手绘功能实现
	 *************************************************************************************************************/

public Draw(Chat chat) {
	this.chat = chat;
	setResizable(false);
    JPanel drawPanel = new JPanel();
    drawPanel.setLayout(new BorderLayout());
    // 画面板的左右子面板
    JPanel drawLeftPanel = new JPanel();
    drawLeftPanel.setLayout(new BorderLayout());
    //左边板的中间面板
    centerPanel = new JPanel();
    centerPanel.setBackground(Color.white);
    //左面板下的颜色面板
     colorPanel = new JPanel();
    //给颜色面板设置空布局
    colorPanel.setLayout(null);
    colorPanel.setBackground(new Color(212,212,212));
    colorPanel.setPreferredSize(new Dimension(0, 60));
    //颜色面板的颜色按钮
    Color[] colors = { Color.black,Color.blue,Color.cyan, Color.green,new Color(182,177,103)
    		,Color.orange,Color.YELLOW, Color.pink,Color.red
              };
    //颜色按钮添加
    ActionListener btnlistener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JButton bt = (JButton) e.getSource();
            color = bt.getBackground();
            System.out.println("我已经将颜色切换为"+color);
        }
    };
    for (int i = 0; i < colors.length; i++) {
        JButton btn = new JButton();
        btn.setBackground(colors[i]);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        System.out.println(colors[i]);
        btn.addActionListener(btnlistener);
        btn.setBounds(20 + i * 18, 15, 18, 18);
        colorPanel.add(btn);
    }

    //添加画笔粗细
    box = new JComboBox<Integer>();
    box.setBounds(220, 15, 40, 20);
    for (int i = 0; i < 10; i++) {
        Integer intdata = new Integer(i + 1);
        box.addItem(intdata);
    }
    colorPanel.add(box);
    JButton jb = new JButton();
    jb.setBounds(340, 15, 30, 20);
    jb.addActionListener(this);
    colorPanel.add(jb);
    JPanel drawRightPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(0, 50));
    JLabel contant = new JLabel();
    drawRightPanel.add(buttonPanel, BorderLayout.SOUTH);
    contant.setPreferredSize(new Dimension(0, 20));
    drawLeftPanel.add(contant, BorderLayout.NORTH);
    drawLeftPanel.add(centerPanel, BorderLayout.CENTER);
    drawLeftPanel.add(colorPanel, BorderLayout.SOUTH);
    drawPanel.add(drawLeftPanel);
    drawPanel.add(drawRightPanel, BorderLayout.EAST);

    centerPanel.addMouseListener(ma);
    centerPanel.addMouseMotionListener(ma);
    drawPanel.setBounds(0,0,380,280);
    JFrame jfdraw = new JFrame("即时手绘");
    jfdraw.setLayout(null);
    jfdraw.setSize(400,300);
    jfdraw.setResizable(false);
    jfdraw.getContentPane().add(drawPanel);
    jfdraw.setVisible(true);
    g = (Graphics2D) centerPanel.getGraphics();
}

//鼠标监听器
MouseAdapter ma = new MouseAdapter() {

    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
    }

    public void mouseEntered(MouseEvent e) {

           if (color == null) {
               color = Color.black;}
           System.out.println(g);
           System.out.println(color);
           g.setColor(color);
           System.out.println("此时画笔的颜色为"+color);
    }

    public void mouseDragged(MouseEvent e) {
        int width = (int) box.getSelectedItem();
        System.out.println(width);
        strock = new BasicStroke(width);
        g.setStroke(strock);

        int x2 = e.getX();
        int y2 = e.getY();
        g.drawLine(x1, y1, x2, y2);
                x1 = x2;
                y1 = y2;
    	}
	};

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	Dimension imageSize = new Dimension();
	imageSize.setSize(50, 50);
	BufferedImage image;
	try {
		File file = new File("draw/draw"+i+".jpg");
		image = new Robot().createScreenCapture(new Rectangle(11,44,centerPanel.getWidth()-10,centerPanel.getHeight()));
		ImageIO.write(image, "jpg", file);
		 ImageIcon icon = new ImageIcon("draw/draw"+i+".jpg");
		 i++;
		 System.out.println(icon);
		chat.getSendText().insertIcon(icon);
	} catch (Exception e2) {
		e2.printStackTrace();
	}
}
@Override
public void mouseDragged(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseMoved(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
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
	
}
@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}}