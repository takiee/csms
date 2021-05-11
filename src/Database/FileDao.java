package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FileDao {
	private String filePath;
	
	//向数据库中添加文件名以及文件的保存地址
	public  void addFile(String fileName,String filePath){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
		 			+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");
			
		 	PreparedStatement statement = connection.prepareStatement("INSERT INTO file VALUES(?,?)");
			statement.setString(1, fileName);
			statement.setString(2, filePath);
		    statement.executeUpdate();  //参数准备后执行语句
//		    statement.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();		
		}
	}
	
	//利用文件名，在数据库中得到文件的保存地址
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
	
	//得到文件列表
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
