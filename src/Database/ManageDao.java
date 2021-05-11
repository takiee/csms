package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ManageDao  {
	public static String[] array; 
	public void register(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
						+ "&characterEncoding=UTF8&serverTimezone=GMT";
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");
			Statement statement = connection.createStatement();
			String sql = "SELECT*FROM manage";
			ResultSet re = statement.executeQuery(sql);
			ArrayList<String> aList=new ArrayList<String>();
			while(re.next()) {
				String manageNum = re.getString(1);	
				aList.add(manageNum);
				}
			 array = new String[aList.size()];  
			 for(int i=0;i<aList.size();i++){  
				 array[i]=(String)aList.get(i);  
			 }  
//				re.close();
//				statement.close();
//				connection.close();
			}
			catch(Exception e) {
				System.out.println("databaseerror");
				e.printStackTrace();		
			}
		}
	}

