package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import bean.Student;

public class StudentDao  {
	public static Boolean stIsfit = false;
	public static Boolean exist = false;

	//验证登录信息，判断学号是否存在，若存在，判断学号与密码是否匹配
	public void register(Object obj){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");
			Statement statement = connection.createStatement();
			String sql = "SELECT*FROM student";
			ResultSet re = statement.executeQuery(sql);
			Student stu = (Student)obj;
			while(re.next()) {
				String schoolnum = re.getString(1);	
				if(schoolnum.equals(stu.schoolNum)) {
					exist = true;	
					break;	
				}
				else continue;
			}
			String pass = re.getString(3);
			System.out.println("data:"+pass);
			stIsfit = pass.equals(stu.password);
//			re.close();
//			statement.close();
//			connection.close();
		}
		catch(Exception e) {
			System.out.println("databaseerror");
			e.printStackTrace();
		}
	}
	
	//得到数据库中的所有学生学号数组
	public String[] getStudent(){
		ArrayList<String> aList=new ArrayList<String>();
		String[] array = null ;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/test1?userSSL=false&useUnicode=true"
					+ "&characterEncoding=UTF8&serverTimezone=GMT";
			
			Connection connection = DriverManager.getConnection(url,"root","Tj001020");	
			PreparedStatement statement = connection.prepareStatement("SELECT schoolNum FROM student");
			ResultSet rs = statement.executeQuery();//参数准备后执行语句
			while(rs.next()) {
				String schoolNum = rs.getString(1);
				aList.add(schoolNum);
			}
			array = new String[aList.size()];  
		        for(int i=0;i<aList.size();i++){  
		            array[i]=(String)aList.get(i);  
		        } 
		}
		catch(Exception e) {
			System.out.println("database error");
			e.printStackTrace();	
		}
		return array;
	}
}
