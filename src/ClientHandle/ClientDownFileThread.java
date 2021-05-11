package ClientHandle;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClientDownFileThread extends Thread{
	public Socket soc4;
	public String schoolNum;
	public String fileName;
	public Socket getSoc4() {
		return soc4;
	}
	public void setSoc4(Socket soc4) {
		this.soc4 = soc4;
	}
	public String getSchoolNum() {
		return schoolNum;
	}
	public void setSchoolNum(String schoolNum) {
		this.schoolNum = schoolNum;
	}
	
	public ClientDownFileThread(String schoolNum,Socket soc4){
		this.schoolNum = schoolNum;
		this.soc4 = soc4;
	}

	
	public void run() {
		while(true) {
			try {
				//���շ������������ļ�������
				DataInputStream dis = new DataInputStream(soc4.getInputStream());
				String filename = dis.readUTF();
				String filePath = ManageUpdown.getUpdown(schoolNum).getFilePath();
				ManageUpdown.deleteUpdown(schoolNum);
				System.out.println("�û����ص�ַ:"+filePath);
				System.out.println("�����ɵ��ļ�����"+filename);
				
				//������д�������ɵ��ļ�
				File file = new File(filePath);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] b = new byte[1024];
				int length = 0;
				long pro = 0;
				while((length = dis.read(b))!= -1){
					fos.write(b,0,length);
					pro += length;
					System.out.println("|"+(100*pro/file.length())+"%");//���ؽ���
				}
				fos.flush();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

}
