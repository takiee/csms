package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Message.MessageForRecord;

public class RecordDao {
	
	//�洢����Ա��־
	public void restoreRecord(MessageForRecord mfr) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");
			PreparedStatement statement = connection.prepareStatement("INSERT INTO record VALUES(?,?)");
			statement.setString(1, mfr.getSchoolNum());
			statement.setString(2, mfr.getBehave());
		    statement.executeUpdate();  //����׼����ִ�����
//		    statement.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();		
		}
	}
	
	//��ù���Ա��־�б�
	public MessageForRecord[] getRecord(){
		ArrayList<MessageForRecord> aList=new ArrayList<MessageForRecord>();
		MessageForRecord[] array = null ;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");	
			PreparedStatement statement = connection.prepareStatement("SELECT*FROM record");
			ResultSet rs = statement.executeQuery();//����׼����ִ�����
			while(rs.next()) {
				MessageForRecord mfr = new MessageForRecord();
				mfr.setSchoolNum(rs.getString(1));
				mfr.setBehave(rs.getString(2));
				aList.add(mfr);
			}
			array = new MessageForRecord[aList.size()];  
		        for(int i=0;i<aList.size();i++){  
		            array[i]=(MessageForRecord)aList.get(i);  
		        } 
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();	
		}
		return array;
	}
}
