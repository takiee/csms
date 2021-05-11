package ServerHandle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import Database.StudentDao;
import Message.MessageForRegister;
import bean.Student;
/*******************************************************************
 * public  Socket socket;//��¼��˽��
 *public  Socket socket1;//Ⱥ��
 *public  Socket socket2;//��������
 *public  Socket socket3;//�ļ��ϴ�
 *public  Socket socket4;//�ļ�����
 *public  Socket socket5;//�ֻ�
 *public  Socket socket6;//ͶƱ
 *public  Socket socket7 //���������ص��ļ���
 *****************************************************************/

public class ServerHandle1 {
	static ServerSocket serverSocket,serverSocket1,serverSocket2,serverSocket3,
						serverSocket4,serverSocket6,serverSocket7;

	
	public final static int PORT = 57508,PORT1 = 41038,PORT3 = 54602,
							PORT2 = 41019,PORT4=34502,
							PORT6 = 41089,PORT7 = 40678;//�˿�
	
	public static void main(String[] args) throws IOException {
	 	new ServerHandle1();
		System.out.println("������������");
	}
	public ServerHandle1() throws IOException{
		try {
	 		System.out.println("=========�������������׽���ǰ=========");
			serverSocket = new ServerSocket(PORT);//�����������˵��׽���
			serverSocket1 = new ServerSocket(PORT1);
	 		serverSocket2 = new ServerSocket(PORT2);
	 		serverSocket3 = new ServerSocket(PORT3);
	 		serverSocket4 = new ServerSocket(PORT4);
			serverSocket6 = new ServerSocket(PORT6);
			serverSocket7 = new ServerSocket(PORT7);
			System.out.println("==========�������������׽��ֽ���==========");

			while(true) {
			Socket socket = serverSocket.accept();
			Socket socket1 = serverSocket1.accept();
			Socket socket2 = serverSocket2.accept();
			Socket socket3 = serverSocket3.accept();
			Socket socket4 = serverSocket4.accept();
			Socket socket6 = serverSocket6.accept();
			Socket socket7 = serverSocket7.accept();
			
			System.out.println("=======�������˽����׽��ֳɹ�===========");

			//���յ�¼��Ϣ
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Student stu = (Student)ois.readObject();
			System.out.println("���������յ�¼��Ϣ�� ����ѧ�ţ�"+stu.getSchoolNum()+"����"+stu.getPass());
			
			//�����¼��Ϣ����
			MessageForRegister mes = new MessageForRegister();
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			stu.register(stu);
			if(StudentDao.exist&&StudentDao.stIsfit) {
				mes.setMesType("1");
				oos.writeObject(mes);
				
				//����һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ��������ÿͻ���ͨѶ���߳�
	 			ServerConClientThread scct = new ServerConClientThread(socket);
				ManageCliThread.addClientThread(stu.getSchoolNum(), scct);
				scct.start();
				System.out.println("��������ͻ������ӵ��߳̿�����������ɵ�¼��˽����Ϣ����");
				
				//�����빫����ص��߳�
				NoticeThread nt = new NoticeThread(socket2);
				ManageNoticeThread.addNoticeThread(stu.getSchoolNum(), nt);
				nt.start();
				System.out.println("�빫����ص��߳��߳̿���");
			
				//������Ⱥ����ص��߳�
				GroupChatThread gct = new GroupChatThread(socket1);
				ManageGroupChatThread.addGroupChatThread(stu.getSchoolNum(), gct);
				gct.start();
				System.out.println("������Ⱥ������߳�");
				
				//������ͶƱ����߳�
				VoteThread vt = new VoteThread(socket6);
				ManageVoteThread.addVoteThread(stu.getSchoolNum(), vt);
				vt.start();
				System.out.println("������ͶƱ����߳�");
				
				//�������ļ��ϴ���ص��߳�
				UpFileThread uft = new UpFileThread(socket3);	
				ManageUpFileThread.addUpFileThread(stu.getSchoolNum(),uft);
				uft.start();
				System.out.println("�����ļ��ϴ����������߳�");
				
				ManageDownSoc.addSoc(stu.getSchoolNum(), socket7);
				//�����ļ����ط��������߳�
				DownFileThread dft = new DownFileThread(socket4,stu.getSchoolNum());
				ManageDownFileThread.addDownFileThread(stu.getSchoolNum(),dft);
				dft.start();
				System.out.println("�����ļ����ط��������߳�");
				
			}else if(!StudentDao.exist) {
				mes.setMesType("2");
				oos.writeObject(mes);
				socket.close();
			}else if(StudentDao.exist&&!StudentDao.stIsfit) {
				mes.setMesType("3");
				oos.writeObject(mes);
				socket.close();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}



