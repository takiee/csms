package ClientHandle;

import bean.Student;
/*
 * ��ѧ����Ϣ����������
 * */
public  class ClientHandleUser{
	public String checkStudent(Student stu) {
		System.out.println("��ѧ����Ϣ����������");
		return new ClientConServer().sendLoginInfoToServer(stu);
	}
}

