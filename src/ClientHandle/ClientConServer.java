package ClientHandle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import bean.Student;

import Database.GroupDao;
import Database.MessageDao;
import Database.NoticeDao;
import Database.VoteDao;

import Message.MessageForChat;
import Message.MessageForGroupChat;
import Message.MessageForNotice;
import Message.MessageForRegister;
import Message.MessageForVote;

import ServerHandle.ManageGroupChatThread;
import ServerHandle.ManageNoticeThread;
import ServerHandle.ManageUpFileThread;
import ServerHandle.NoticeThread;
import ServerHandle.ServerHandle1;

import View.Chat;
import View.GroupChat;
import View.Viewnotice;
import View.Viewvote;

public class ClientConServer {
	private String schoolNum;

	/**********************************************************
	 * 客户端将登录信息发送给服务器
	 **********************************************************/
	public String sendLoginInfoToServer(Object obj) {
		this.schoolNum =((Student)obj).getSchoolNum();
		String b = "";
		try {
			//soc:发送登录信息，发送和接收私聊消息
			Socket soc = new Socket("127.0.0.1",ServerHandle1.PORT);
			//soc1：发送和接收群聊消息
			Socket soc1 = new Socket("127.0.0.1",ServerHandle1.PORT1);
			//soc2：发送和接收公告
		 	Socket soc2 = new Socket("127.0.0.1",ServerHandle1.PORT2);
		 	//soc3：实现文件上传
		 	Socket soc3 = new Socket("127.0.0.1",ServerHandle1.PORT3);
		 	//soc4：实现文件下载
		 	Socket soc4 = new Socket("127.0.0.1",ServerHandle1.PORT4);
		 	//soc6：发送和接收投票
			Socket soc6 = new Socket("127.0.0.1",ServerHandle1.PORT6);
			//soc7：发送所要下载的文件名，并将其加入一个HashMap中
			Socket soc7 = new Socket("127.0.0.1",ServerHandle1.PORT7);
			ManageCliDownSoc.addSoc(schoolNum, soc7);
			
			//将用户的登录信息发送至服务器验证
			ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
		 	oos.writeObject(obj);
		 	System.out.println("将用户的登录信息发送至服务器验证");
			
			//接收是否登录成功的登录信息
			ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
			MessageForRegister mes =(MessageForRegister)ois.readObject();
			System.out.println("接收登录消息类型： "+mes.getMesType());
			
			if(mes.getMesType().equals("1")) {
				b = "1";
				System.out.println("验证登录成功");
				
				//创建并启动与服务器端保持通讯连接的线程
				ClientConServerThread ccst = new ClientConServerThread(soc);
				ManageCliConServerThread.addClientConServerThread(((Student)obj).getSchoolNum(),ccst);
				ccst.start();
				System.out.println("连接线程创建完毕");
				
				ClientGroupChatThread cgct = new ClientGroupChatThread(((Student)obj).getSchoolNum(),soc1);
				ManageCliGroupChatThread.addClientGroupChatThread(((Student)obj).getSchoolNum(),cgct);	
				cgct.start();
				System.out.println("群聊线程创建完毕");
				
				ClientGetNoticeThread cgnt = new ClientGetNoticeThread(((Student)obj).getSchoolNum(),soc2);
				ManageCliGetNoticeThread.addClientGetNoticeThread(((Student)obj).getSchoolNum(), cgnt);
				cgnt.start();
				System.out.println("公告线程创建完毕");
				
				ClientUpFileThread cuft = new ClientUpFileThread(((Student)obj).getSchoolNum(),soc3);
				ManageCliUpFileThread.addClientUpFileThread(schoolNum,cuft);
				cuft.start();
				System.out.println("文件上传线程创建完毕");
				
				ClientDownFileThread cdft = new ClientDownFileThread(((Student)obj).getSchoolNum(),soc4);
				ManageCliDownFileThread.addClientDownFileThread(schoolNum,cdft);
				cdft.start();
				System.out.println("文件下载线程创建完毕");

				ClientVoteThread cvt = new ClientVoteThread(((Student)obj).getSchoolNum(),soc6);
				ManageCliVoteThread.addClientVoteThread(((Student)obj).getSchoolNum(), cvt);
				cvt.start();
				System.out.println("投票线程创建完毕");
				
				
				//判断是否有离线消息，离线公告，离线投票，如果有，一登陆成功就显示
				try{
					//私聊
					if(MessageDao.hasMessage(((Student)obj).getSchoolNum()))  {
						MessageDao md = new MessageDao();
						MessageForChat[] unmes = md.getAnddeleteMessage(((Student)obj).getSchoolNum());
						for(int i = 0;i<unmes.length;i++) {
								Chat chat = new Chat(unmes[i].getGetter(),unmes[i].getSender());
								ManageChat.addChat(unmes[i].getGetter()+" "+unmes[i].getSender(), chat);
								chat.setVisible(true);
								chat.appendView(unmes[i]);
								}
					}
					
					//群聊
					if(GroupDao.hasGroupMessage(((Student)obj).getSchoolNum()))  {
						GroupDao gd = new GroupDao();
						MessageForGroupChat[] ungmes = gd.getAnddeleteGroupMessage(((Student)obj).getSchoolNum());
						for(int i = 0;i<ungmes.length;i++) {
							GroupChat gchat = new GroupChat(ungmes[i].getGetter());
							ManageGroupChat.addGroupChat(ungmes[i].getGetter(), gchat);
							gchat.setVisible(true);
							gchat.appendView(ungmes[i]);
							}
						}
					
					//公告
					if(NoticeDao.hasNotice(((Student)obj).getSchoolNum()))  {
						NoticeDao nd = new NoticeDao();
						MessageForNotice[] unnot = nd.getAnddeleteNotice(((Student)obj).getSchoolNum());
						for(int i = 0;i<unnot.length;i++) {
							Viewnotice vn = new Viewnotice(unnot[i].getGetter());
							vn.setVisible(true);
							vn.showNoticeMessage(unnot[i]);
								}
							}
					
					//投票
					if(VoteDao.hasVote(((Student)obj).getSchoolNum()))  {
						VoteDao vd = new VoteDao();
						MessageForVote[] unnot = vd.getAnddeleteVote(((Student)obj).getSchoolNum());
						for(int i = 0;i<unnot.length;i++) {
							Viewvote vn = new Viewvote(unnot[i].getGetter(),unnot[i]);
							vn.setVisible(true);
							}
						}
					}
				catch(Exception e) {
						e.printStackTrace();
						}
				}
			else if(mes.getMesType().equals("2")) {
				b = "2";
			}else if(mes.getMesType().equals("3")) {
				b = "3";
			}else if(mes.getMesType().equals("5")) {
				b = "5";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}return b;
	}
	
}
