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
				//接收服务器传来的文件数据流
				DataInputStream dis = new DataInputStream(soc4.getInputStream());
				String filename = dis.readUTF();
				String filePath = ManageUpdown.getUpdown(schoolNum).getFilePath();
				ManageUpdown.deleteUpdown(schoolNum);
				System.out.println("用户下载地址:"+filePath);
				System.out.println("新生成的文件名："+filename);
				
				//将数据写入新生成的文件
				File file = new File(filePath);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] b = new byte[1024];
				int length = 0;
				long pro = 0;
				while((length = dis.read(b))!= -1){
					fos.write(b,0,length);
					pro += length;
					System.out.println("|"+(100*pro/file.length())+"%");//下载进度
				}
				fos.flush();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

}
