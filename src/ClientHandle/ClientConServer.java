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
	 * �ͻ��˽���¼��Ϣ���͸�������
	 **********************************************************/
	public String sendLoginInfoToServer(Object obj) {
		this.schoolNum =((Student)obj).getSchoolNum();
		String b = "";
		try {
			//soc:���͵�¼��Ϣ�����ͺͽ���˽����Ϣ
			Socket soc = new Socket("127.0.0.1",ServerHandle1.PORT);
			//soc1�����ͺͽ���Ⱥ����Ϣ
			Socket soc1 = new Socket("127.0.0.1",ServerHandle1.PORT1);
			//soc2�����ͺͽ��չ���
		 	Socket soc2 = new Socket("127.0.0.1",ServerHandle1.PORT2);
		 	//soc3��ʵ���ļ��ϴ�
		 	Socket soc3 = new Socket("127.0.0.1",ServerHandle1.PORT3);
		 	//soc4��ʵ���ļ�����
		 	Socket soc4 = new Socket("127.0.0.1",ServerHandle1.PORT4);
		 	//soc6�����ͺͽ���ͶƱ
			Socket soc6 = new Socket("127.0.0.1",ServerHandle1.PORT6);
			//soc7��������Ҫ���ص��ļ��������������һ��HashMap��
			Socket soc7 = new Socket("127.0.0.1",ServerHandle1.PORT7);
			ManageCliDownSoc.addSoc(schoolNum, soc7);
			
			//���û��ĵ�¼��Ϣ��������������֤
			ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
		 	oos.writeObject(obj);
		 	System.out.println("���û��ĵ�¼��Ϣ��������������֤");
			
			//�����Ƿ��¼�ɹ��ĵ�¼��Ϣ
			ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
			MessageForRegister mes =(MessageForRegister)ois.readObject();
			System.out.println("���յ�¼��Ϣ���ͣ� "+mes.getMesType());
			
			if(mes.getMesType().equals("1")) {
				b = "1";
				System.out.println("��֤��¼�ɹ�");
				
				//������������������˱���ͨѶ���ӵ��߳�
				ClientConServerThread ccst = new ClientConServerThread(soc);
				ManageCliConServerThread.addClientConServerThread(((Student)obj).getSchoolNum(),ccst);
				ccst.start();
				System.out.println("�����̴߳������");
				
				ClientGroupChatThread cgct = new ClientGroupChatThread(((Student)obj).getSchoolNum(),soc1);
				ManageCliGroupChatThread.addClientGroupChatThread(((Student)obj).getSchoolNum(),cgct);	
				cgct.start();
				System.out.println("Ⱥ���̴߳������");
				
				ClientGetNoticeThread cgnt = new ClientGetNoticeThread(((Student)obj).getSchoolNum(),soc2);
				ManageCliGetNoticeThread.addClientGetNoticeThread(((Student)obj).getSchoolNum(), cgnt);
				cgnt.start();
				System.out.println("�����̴߳������");
				
				ClientUpFileThread cuft = new ClientUpFileThread(((Student)obj).getSchoolNum(),soc3);
				ManageCliUpFileThread.addClientUpFileThread(schoolNum,cuft);
				cuft.start();
				System.out.println("�ļ��ϴ��̴߳������");
				
				ClientDownFileThread cdft = new ClientDownFileThread(((Student)obj).getSchoolNum(),soc4);
				ManageCliDownFileThread.addClientDownFileThread(schoolNum,cdft);
				cdft.start();
				System.out.println("�ļ������̴߳������");

				ClientVoteThread cvt = new ClientVoteThread(((Student)obj).getSchoolNum(),soc6);
				ManageCliVoteThread.addClientVoteThread(((Student)obj).getSchoolNum(), cvt);
				cvt.start();
				System.out.println("ͶƱ�̴߳������");
				
				
				//�ж��Ƿ���������Ϣ�����߹��棬����ͶƱ������У�һ��½�ɹ�����ʾ
				try{
					//˽��
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
					
					//Ⱥ��
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
					
					//����
					if(NoticeDao.hasNotice(((Student)obj).getSchoolNum()))  {
						NoticeDao nd = new NoticeDao();
						MessageForNotice[] unnot = nd.getAnddeleteNotice(((Student)obj).getSchoolNum());
						for(int i = 0;i<unnot.length;i++) {
							Viewnotice vn = new Viewnotice(unnot[i].getGetter());
							vn.setVisible(true);
							vn.showNoticeMessage(unnot[i]);
								}
							}
					
					//ͶƱ
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
