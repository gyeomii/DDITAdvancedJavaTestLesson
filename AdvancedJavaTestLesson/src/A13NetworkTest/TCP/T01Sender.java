package A13NetworkTest.TCP;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class T01Sender extends Thread{
	private Scanner scanner;
	private String name;
	private DataOutputStream dos;
	
	public T01Sender(Socket socket) {
		name = "[" + socket.getInetAddress() + " : " + socket.getLocalPort() + "]";
		scanner = new Scanner(System.in);
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(dos != null) {
			try {
				dos.writeUTF(name + " >> " + scanner.nextLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
