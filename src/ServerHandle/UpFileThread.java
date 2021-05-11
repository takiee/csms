package ServerHandle;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import Database.FileDao;

public class UpFileThread extends Thread{
	Socket soc3;
	public UpFileThread(Socket soc3) {
		this.soc3 = soc3;
	}
	public Socket getSoc3() {
		return soc3;
	}
	public void setSoc3(Socket soc3) {
		this.soc3 = soc3;
	}
	
	public void run() {
		while(true) {
			try {
				DataInputStream dis = new DataInputStream(soc3.getInputStream());
				//确定共享空间
				String filePath = "E:\\服务器端共享空间\\";
				String fileName = dis.readUTF();
				filePath += fileName;
				FileDao fd = new FileDao();
				System.out.println(fileName+filePath);
				fd.addFile(fileName, filePath);
				System.out.println(fileName);
				System.out.println(fd.readFile(fileName));
				System.out.println("新生成的文件名为："+fileName);
				
				File file = new File(filePath);
	 			FileOutputStream fos = new FileOutputStream(file);
				byte[] b = new byte[1024];
				int length = 0;
				while((length = dis.read(b))!= -1) {
					fos.write(b,0,length);
				}
				fos.flush();
//				fos.close();
//				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
