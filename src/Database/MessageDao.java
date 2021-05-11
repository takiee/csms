package Database;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.text.StyledDocument;
import Message.MessageForChat;

public class MessageDao {
	
	//判断用户是否有未读私聊消息
	public static Boolean hasMessage(String getter){
		Boolean b = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
		 	Connection connection = DriverManager.getConnection(url,"root","Tj001020");	
		  	Statement statement = connection.createStatement();
			String sql = "SELECT*FROM message";
			ResultSet re = statement.executeQuery(sql);
			while(re.next()) {
				if(getter.equals(re.getString(2)))
					b =true;
			}
//			re.close();
//			statement.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();		
		}
		return b;
	}
	
	//将用户不在线时收到的私聊消息存入数据库中
	public  void restoreMessage(String sender,String getter,StyledDocument content,String sendTime){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");
			PreparedStatement statement = connection.prepareStatement("INSERT INTO message VALUES(?,?,?,?)");
			statement.setString(1, sender);
			statement.setString(2, getter);
			statement.setString(3, sendTime);
			statement.setObject(4, content);
		    statement.addBatch();
		    statement.executeBatch();
//		    statement.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();		
		}
	}
	
	//得到某位同学的全部离线私聊消息
	public MessageForChat[] getAnddeleteMessage(String getter){
		ArrayList<MessageForChat> aList=new ArrayList<MessageForChat>();
		MessageForChat[] array = null ;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");	
			PreparedStatement statement = connection.prepareStatement("SELECT*FROM message WHERE getter = ?");
			statement.setString(1, getter);
			ResultSet rs = statement.executeQuery();//参数准备后执行语句
			
			while(rs.next()) {
				MessageForChat mfc = new MessageForChat();
				mfc.setSender(rs.getString(1));
				mfc.setGetter(rs.getString(2));
				mfc.setSendTime(rs.getString(3));
				
				Blob inb = rs.getBlob(4);
				InputStream is = inb.getBinaryStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				
				byte[] buff = new byte[(int)inb.length()];
				while(-1 !=(bis.read(buff,0,buff.length))) {
					ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buff));
					StyledDocument obb = (StyledDocument)ois.readObject();
					mfc.setDoc(obb);
				}
				aList.add(mfc);
			}
			array = new MessageForChat[aList.size()];  
		        for(int i=0;i<aList.size();i++){  
		            array[i]=(MessageForChat)aList.get(i);  
		        } 
			String sql = " DELETE FROM message WHERE getter = "+getter;
			statement.executeUpdate(sql);
//			statement.close();
//			state.close();
//			connection.close();
			
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();	
		}
		return array;
	}
}
