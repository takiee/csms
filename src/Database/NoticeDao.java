package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import Message.MessageForNotice;

public class NoticeDao {
	
	//判断用户是否有未读公告
	public static Boolean hasNotice(String getter){
		Boolean b = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");	
			Statement statement = connection.createStatement();
			String sql = "SELECT*FROM notice";
			ResultSet re = statement.executeQuery(sql);
			while(re.next()) {
				if(getter.equals(re.getString(5)))
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
	
	//将用户未读公告存入数据库中
	public  void restoreNotice(String getter ,MessageForNotice mfn){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");
			PreparedStatement statement = connection.prepareStatement("INSERT INTO notice VALUES(?,?,?,?,?)");
			statement.setString(1, mfn.getNoticer());
			statement.setString(2, mfn.getTitle());
			statement.setString(3, mfn.getNoticeContent());
			statement.setString(4, mfn.getNoticeTime());
			statement.setString(5, getter);
		    statement.executeUpdate();  //参数准备后执行语句
//		    statement.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();		
		}
	}
	
	//得到某位用户的所有未读公告
	public MessageForNotice[] getAnddeleteNotice(String getter){
		ArrayList<MessageForNotice> aList=new ArrayList<MessageForNotice>();
		MessageForNotice[] array = null ;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");	
			PreparedStatement statement = connection.prepareStatement("SELECT*FROM notice WHERE getter = ?");
			statement.setString(1, getter);
			ResultSet rs = statement.executeQuery();//参数准备后执行语句
			
			while(rs.next()) {
				MessageForNotice mfn = new MessageForNotice();
				mfn.setNoticer(rs.getString(1));
				mfn.setTitle(rs.getString(2));
				mfn.setNoticeContent(rs.getString(3));
				mfn.setNoticeTime(rs.getString(4));
				mfn.setGetter(rs.getString(5));
				aList.add(mfn);
			}
			array = new MessageForNotice[aList.size()];  
		        for(int i=0;i<aList.size();i++){  
		            array[i]=(MessageForNotice)aList.get(i);  
		        } 
			String sql = " DELETE FROM notice WHERE getter = "+getter;
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
	
	//得到公告列表
	public MessageForNotice[] getNotice(String getter){
		ArrayList<MessageForNotice> aList=new ArrayList<MessageForNotice>();
		MessageForNotice[] array = null ;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");	
			PreparedStatement statement = connection.prepareStatement("SELECT*FROM notice WHERE getter = 0");
			ResultSet rs = statement.executeQuery();//参数准备后执行语句
			
			while(rs.next()) {
				MessageForNotice mfn = new MessageForNotice();
				mfn.setNoticer(rs.getString(1));
				mfn.setTitle(rs.getString(2));
				mfn.setNoticeContent(rs.getString(3));
				mfn.setNoticeTime(rs.getString(4));
				mfn.setGetter(rs.getString(5));
				aList.add(mfn);
			}
			array = new MessageForNotice[aList.size()];  
		        for(int i=0;i<aList.size();i++){  
		            array[i]=(MessageForNotice)aList.get(i);  
		        } 
//			statement.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();	
		}
		return array;
	}
	

}
