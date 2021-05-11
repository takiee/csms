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
 * public  Socket socket;//登录，私聊
 *public  Socket socket1;//群聊
 *public  Socket socket2;//发布公告
 *public  Socket socket3;//文件上传
 *public  Socket socket4;//文件下载
 *public  Socket socket5;//手绘
 *public  Socket socket6;//投票
 *public  Socket socket7 //传所需下载的文件名
 *****************************************************************/

public class ServerHandle1 {
	static ServerSocket serverSocket,serverSocket1,serverSocket2,serverSocket3,
						serverSocket4,serverSocket6,serverSocket7;

	
	public final static int PORT = 57508,PORT1 = 41038,PORT3 = 54602,
							PORT2 = 41019,PORT4=34502,
							PORT6 = 41089,PORT7 = 40678;//端口
	
	public static void main(String[] args) throws IOException {
	 	new ServerHandle1();
		System.out.println("服务器已启动");
	}
	public ServerHandle1() throws IOException{
		try {
	 		System.out.println("=========创建服务器端套接字前=========");
			serverSocket = new ServerSocket(PORT);//创建服务器端的套接字
			serverSocket1 = new ServerSocket(PORT1);
	 		serverSocket2 = new ServerSocket(PORT2);
	 		serverSocket3 = new ServerSocket(PORT3);
	 		serverSocket4 = new ServerSocket(PORT4);
			serverSocket6 = new ServerSocket(PORT6);
			serverSocket7 = new ServerSocket(PORT7);
			System.out.println("==========创建服务器端套接字结束==========");

			while(true) {
			Socket socket = serverSocket.accept();
			Socket socket1 = serverSocket1.accept();
			Socket socket2 = serverSocket2.accept();
			Socket socket3 = serverSocket3.accept();
			Socket socket4 = serverSocket4.accept();
			Socket socket6 = serverSocket6.accept();
			Socket socket7 = serverSocket7.accept();
			
			System.out.println("=======服务器端接收套接字成功===========");

			//接收登录信息
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Student stu = (Student)ois.readObject();
			System.out.println("服务器接收登录信息： 接收学号："+stu.getSchoolNum()+"密码"+stu.getPass());
			
			//输出登录信息类型
			MessageForRegister mes = new MessageForRegister();
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			stu.register(stu);
			if(StudentDao.exist&&StudentDao.stIsfit) {
				mes.setMesType("1");
				oos.writeObject(mes);
				
				//单开一个线程，让该线程与该客户端保持通讯，启动与该客户端通讯的线程
	 			ServerConClientThread scct = new ServerConClientThread(socket);
				ManageCliThread.addClientThread(stu.getSchoolNum(), scct);
				scct.start();
				System.out.println("服务器与客户端连接的线程开启：用于完成登录和私聊信息发送");
				
				//启动与公告相关的线程
				NoticeThread nt = new NoticeThread(socket2);
				ManageNoticeThread.addNoticeThread(stu.getSchoolNum(), nt);
				nt.start();
				System.out.println("与公告相关的线程线程开启");
			
				//启动与群聊相关的线程
				GroupChatThread gct = new GroupChatThread(socket1);
				ManageGroupChatThread.addGroupChatThread(stu.getSchoolNum(), gct);
				gct.start();
				System.out.println("启动与群聊相关线程");
				
				//启动与投票相关线程
				VoteThread vt = new VoteThread(socket6);
				ManageVoteThread.addVoteThread(stu.getSchoolNum(), vt);
				vt.start();
				System.out.println("启动与投票相关线程");
				
				//启动与文件上传相关的线程
				UpFileThread uft = new UpFileThread(socket3);	
				ManageUpFileThread.addUpFileThread(stu.getSchoolNum(),uft);
				uft.start();
				System.out.println("启动文件上传服务器端线程");
				
				ManageDownSoc.addSoc(stu.getSchoolNum(), socket7);
				//启动文件下载服务器端线程
				DownFileThread dft = new DownFileThread(socket4,stu.getSchoolNum());
				ManageDownFileThread.addDownFileThread(stu.getSchoolNum(),dft);
				dft.start();
				System.out.println("启动文件下载服务器端线程");
				
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



