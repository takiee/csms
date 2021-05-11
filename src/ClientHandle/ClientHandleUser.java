package ClientHandle;

import bean.Student;
/*
 * 把学生信息传到服务器
 * */
public  class ClientHandleUser{
	public String checkStudent(Student stu) {
		System.out.println("把学生信息传到服务器");
		return new ClientConServer().sendLoginInfoToServer(stu);
	}
}

