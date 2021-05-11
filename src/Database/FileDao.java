package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FileDao {
	private String filePath;
	
	//�����ݿ�������ļ����Լ��ļ��ı����ַ
	public  void addFile(String fileName,String filePath){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
		 			+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");
			
		 	PreparedStatement statement = connection.prepareStatement("INSERT INTO file VALUES(?,?)");
			statement.setString(1, fileName);
			statement.setString(2, filePath);
		    statement.executeUpdate();  //����׼����ִ�����
//		    statement.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();		
		}
	}
	
	//�����ļ����������ݿ��еõ��ļ��ı����ַ
	public  String readFile(String fileName){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");
			PreparedStatement statement = connection.prepareStatement("SELECT*FROM file WHERE fileName = ?");
			statement.setString(1, fileName);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				filePath = rs.getString(2);
			}
//		    statement.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();		
		}
		return filePath;
			}
	
	//�õ��ļ��б�
	public String[] getFileList() {
		ArrayList<String> aList = new ArrayList<String>();
		String[] array = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");
			PreparedStatement statement = connection.prepareStatement("SELECT*FROM file ");
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				aList.add(rs.getString(1));
			}
			array = new String[aList.size()];
		     for(int i=0;i<aList.size();i++){  
		            array[i]=(String)aList.get(i);  
		        } 
//		    statement.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();		
		}
		return array;
	}
}
