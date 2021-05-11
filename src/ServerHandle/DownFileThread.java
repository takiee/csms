package ServerHandle;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import Database.FileDao;
import Message.MessageForFile;

public class DownFileThread extends Thread{
	Socket soc4;
	String fileName,schoolNum;
	public DownFileThread(Socket soc4,String schoolNum) {
		this.soc4 = soc4;
		this.schoolNum = schoolNum;
	}

	public Socket getSoc4() {
		return soc4;
	}

	public void setSoc4(Socket soc4) {
		this.soc4 = soc4;
	}
	
	public void run() {	
		try {
			//�������������������ļ����ļ���
			ObjectInputStream ois = new ObjectInputStream(ManageDownSoc.getSoc(schoolNum).getInputStream());
			MessageForFile mff = (MessageForFile)ois.readObject();
			fileName = mff.getFileName();
			FileDao fd = new FileDao();
			String serverfilePath = fd.readFile(fileName);
			
			//����ļ�����
			File file = new File(serverfilePath);
			InputStream is = new FileInputStream(file);
			DataOutputStream dos = new DataOutputStream(ManageDownFileThread.getDownFileThread(schoolNum).getSoc4().getOutputStream());
			//���ļ��������ȴ����ͻ���
			dos.writeUTF(file.getName());
			System.out.println("downFile  file.getName "+file.getName());
			dos.flush();
			int bufferSize = 8192;
			int length = 0;
			byte[] buf = new byte[bufferSize];
			while((length = is.read(buf)) != -1) {
				dos.write(buf,0,length);
			}
			dos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
