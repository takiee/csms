package bean;

import Database.StudentDao;


public class Student extends StudentDao implements java.io.Serializable{
	//定义学生属性
	public String schoolNum ;
	public String password ;
	public String name;
	
	public Student(String schoolnum,String pass) {
		schoolNum = schoolnum;
		password = pass;
		
	}
	public Student(String schoolnum,String pass,String name) {
		this(schoolnum,pass);
		this.name = name;
	}
	public String getSchoolNum() {
		return schoolNum;
	}
	public String getName() {
		return name; 
	}
	public void setPass(String password) {
		this.password = password;
	}
	public String getPass() {
		return password;
	}
	

}
