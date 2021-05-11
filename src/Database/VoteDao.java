package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import Message.MessageForVote;

public class VoteDao {
	
	//�ж��Ƿ���δ��ͶƱ
	public static Boolean hasVote(String getter){
		Boolean b = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");	
			Statement statement = connection.createStatement();
			String sql = "SELECT*FROM vote";
			ResultSet re = statement.executeQuery(sql);
			while(re.next()) {
				if(getter.equals(re.getString(7)))
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
	
	//��δ��ͶƱ�洢�����ݿ���
	public void restoreVote(String getter, MessageForVote mfv) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");
			PreparedStatement statement = connection.prepareStatement("INSERT INTO vote VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString(1, mfv.getSender());
			statement.setString(2, mfv.getTitle());
			statement.setString(3, mfv.getTypeA());
			statement.setString(4, mfv.getTypeB());
			statement.setString(5, mfv.getTypeC());
			statement.setString(6, mfv.getTypeD());
			statement.setString(7, getter);
			statement.setInt(8,mfv.getCountA());
			statement.setInt(9,mfv.getCountB());
			statement.setInt(10,mfv.getCountC());
			statement.setInt(11,mfv.getCountD());
			
		    statement.executeUpdate();  //����׼����ִ�����
//		    statement.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();		
		}
	}
	
	//�õ�ĳλ�û���δ��ͶƱ��ɾ��
	public MessageForVote[] getAnddeleteVote(String getter){
		ArrayList<MessageForVote> aList=new ArrayList<MessageForVote>();
		MessageForVote[] array = null ;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");	
			PreparedStatement statement = connection.prepareStatement("SELECT*FROM vote WHERE getter = ?");
			statement.setString(1, getter);
			ResultSet rs = statement.executeQuery();//����׼����ִ�����
			
			while(rs.next()) {
				MessageForVote mfn = new MessageForVote();
				mfn.setSender(rs.getString(1));
				mfn.setTitle(rs.getString(2));
				mfn.setTypeA(rs.getString(3));
				mfn.setTypeB(rs.getString(4));
				mfn.setTypeC(rs.getString(5));
				mfn.setTypeD(rs.getString(6));
				mfn.setGetter(rs.getString(7));
				mfn.setCountA(rs.getInt(8));
				mfn.setCountB(rs.getInt(9));
				mfn.setCountC(rs.getInt(10));
				mfn.setCountD(rs.getInt(11));
				aList.add(mfn);
			}
			array = new MessageForVote[aList.size()];  
		        for(int i=0;i<aList.size();i++){  
		            array[i]=(MessageForVote)aList.get(i);  
		        } 
			String sql = " DELETE FROM vote WHERE getter = "+getter;
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
	
	//�õ�ͶƱ����ȷ����ͶƱ��Ϣ
	public MessageForVote getVote(String title){
		MessageForVote mfn = new MessageForVote();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");	
			PreparedStatement statement = connection.prepareStatement("SELECT*FROM vote WHERE title = ? AND getter = 0");
			statement.setString(1, title);
			ResultSet rs = statement.executeQuery();//����׼����ִ�����
			while(rs.next()) {
				mfn.setSender(rs.getString(1));
				mfn.setTitle(rs.getString(2));
				mfn.setTypeA(rs.getString(3));
				mfn.setTypeB(rs.getString(4));
				mfn.setTypeC(rs.getString(5));
				mfn.setTypeD(rs.getString(6));
				mfn.setGetter(rs.getString(7));
				mfn.setCountA(rs.getInt(8));
				mfn.setCountB(rs.getInt(9));
				mfn.setCountC(rs.getInt(10));
				mfn.setCountD(rs.getInt(11));
			}
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();	
		}
		return mfn;
	}
	
	//�õ�ͶƱ�б�
	public MessageForVote[] getVote(){
		ArrayList<MessageForVote> aList=new ArrayList<MessageForVote>();
		MessageForVote[] array = null ;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");	
			PreparedStatement statement = connection.prepareStatement("SELECT*FROM vote WHERE getter = 0");
			ResultSet rs = statement.executeQuery();//����׼����ִ�����
			while(rs.next()) {
				MessageForVote mfn = new MessageForVote();
				mfn.setSender(rs.getString(1));
				mfn.setTitle(rs.getString(2));
				mfn.setTypeA(rs.getString(3));
				mfn.setTypeB(rs.getString(4));
				mfn.setTypeC(rs.getString(5));
				mfn.setTypeD(rs.getString(6));
				mfn.setCountA(rs.getInt(8));
				mfn.setCountB(rs.getInt(9));
				mfn.setCountC(rs.getInt(10));
				mfn.setCountD(rs.getInt(11));
				aList.add(mfn);
			}
			array = new MessageForVote[aList.size()];  
		        for(int i=0;i<aList.size();i++){  
		            array[i]=(MessageForVote)aList.get(i);  
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
	
	//����ͶƱ�ļ���
	public void countType(MessageForVote mfv,int i) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");
			PreparedStatement statement1 = connection.prepareStatement("SELECT*FROM vote WHERE title = ?");
			statement1.setString(1, mfv.getTitle());
			ResultSet rs = statement1.executeQuery();//����׼����ִ�����
			
			int count = 0;
			while(rs.next()) {
				count =Integer.parseInt(rs.getString(i+7));	
			}
			String type = "";
			if(i+7 == 8) {
				type = "countA";
			}
			if(i+7 == 9) {
				type = "countB";
			}
			if(i+7 == 10) {
				type = "countC";
			}
			if(i+7 == 11) {
				type = "countD";
			}
			count++;
			String counts = ""+count;
			Statement stmt = connection.createStatement();
			System.out.println("UPDATE vote SET "+type+" = "+counts+" WHERE title = \'"+mfv.getTitle()+"\' AND getter = 0");
			stmt.executeUpdate("UPDATE vote SET "+type+" = "+counts+" WHERE title = \'"+mfv.getTitle()+"\' AND getter = 0");
//		    stmt.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();		
		}
	}
	
	//�õ�ĳ��ͶƱ��ĳ��ѡ���ͶƱ��
	public String getCount(MessageForVote mfv,int i) {
		String count = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020"); 
			PreparedStatement statement1 = connection.prepareStatement("SELECT*FROM vote WHERE title = ? AND getter = 0");
			statement1.setString(1, mfv.getTitle());
			ResultSet rs = statement1.executeQuery();//����׼����ִ�����
			while(rs.next()) {
				count = rs.getString(i+7);	
			}
//		    stmt.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();		
		}
		return count;
	}
	
	//��ĳ��ͶƱ�ĸ��ӽ���洢����
	public void restoreAdvice(String title,String ad) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");
			
			PreparedStatement statement = connection.prepareStatement("INSERT INTO advice VALUES(?,?)");
			statement.setString(1, ad);
			statement.setString(2, title);
		    statement.executeUpdate();  //����׼����ִ�����
//		    statement.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();		
		}
		
	}
	
	//�õ�ĳ��ͶƱ�����и��ӽ���
	public String[] getAdvice(String title) {
		ArrayList<String> aList=new ArrayList<String>();
		String[] array = null ;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");	
			PreparedStatement statement = connection.prepareStatement("SELECT*FROM advice WHERE title = ?");
			statement.setString(1, title);
			ResultSet rs = statement.executeQuery();//����׼����ִ�����
			while(rs.next()) {
				String ad = rs.getString(1);
				aList.add(ad);
			}
			array = new String[aList.size()];  
		        for(int i=0;i<aList.size();i++){  
		            array[i]=(String)aList.get(i);  
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
