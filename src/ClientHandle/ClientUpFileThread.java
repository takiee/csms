package ClientHandle;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import ServerHandle.ServerHandle1;

public class ClientUpFileThread extends Thread{
	public Socket soc3;
	public String schoolNum;
	public String filePath;

	public ClientUpFileThread(String schoolNum,Socket soc3){
		this.soc3 = soc3;
		this.schoolNum = schoolNum;
	}
	public Socket getSoc3() {
		return soc3;
	}

	public void setSoc3(Socket soc3) {
		this.soc3 = soc3;
	}

	public String getSchoolNum() {
		return schoolNum;
	}

	public void setSchoolNum(String schoolNum) {
		this.schoolNum = schoolNum;
	}
	public void run() {

	}
	

}
