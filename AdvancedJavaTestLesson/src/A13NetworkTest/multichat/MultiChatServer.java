package A13NetworkTest.multichat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiChatServer {
	// 대화명, 클라이언트의 Socket을 저장하기 위한 Map변수 선언
	Map<String, Socket> clients;

	public MultiChatServer() {
		// 동기화 처리가 가능하도록 Map객체 생성하기
		clients = Collections.synchronizedMap(new HashMap<String, Socket>());
	}

	// 서버 시작 메소드
	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;

		try {
			System.out.println("\t서버가 시작되었습니다 \\(^@^)/");
			System.out.println("\t|\\_/|");
			System.out.println("\t|q p|   /}");
			System.out.println("\t( 0 )\"\"\"\\");
			System.out.println("\t|\"^\"`    |");
			System.out.println("\t||_/=\\\\__|");

			server = new ServerSocket(7777);

			while (true) {
				// 클라이언트의 접속을 대기한다.
				socket = server.accept();
				System.out.println("접속 주소 : [" + socket.getInetAddress() + " : " + socket.getLocalPort() + "]");
				// 메세지 전송 처리를 하는 스레드 객체 생성 및 실행
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
			}

		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e2) {
				}
			}
		}
	}
	
	/**
	 * 대화방 즉, Map에 저장된 전체 유저에게 안내메시지를 전송하는 메서드
	 * @param msg 안내 메시지
	 */
	public void sendMessage(String msg) { //[성겸] >> 안녕
		Iterator<String> it = clients.keySet().iterator();
		while (it.hasNext()) {
			try {
				String name = it.next();
				DataOutputStream dos = new DataOutputStream(clients.get(name).getOutputStream());
				
				dos.writeUTF(msg);//메세지 보내기
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 대화방 즉, Map에 저장된 전체 유저에게 대화메세지를 전송하는 메소드
	 * @param msg
	 * @param from
	 */
	public void sendMessage(String msg, String from) { // 안녕, 성겸
		sendMessage("[" + from + "] >> " + msg); //[성겸] >> 안녕
	}
	
	/**
	 * 서버에서 클라이언트로 메시지를 전송할 Thread를 Inner클래스로 정의 (Inner클래스 에서는 부모클래스의 멤버들을 직접 사용할 수
	 * 있다.)
	 * 
	 * @author PC 06
	 */
	class ServerReceiver extends Thread {
		private Socket socket;
		private DataInputStream dis;
		private String name;

		public ServerReceiver(Socket socket) {
			this.socket = socket;

			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				// 서버에서 클라이언트가 보내는 최초의 메세지 즉, 대화명을 수신해야한다.
				name = dis.readUTF();

				// 대화명을 받아서 다른 모든 클라이언트에세 대화방 참여 메시지를 보낸다.
				sendMessage("#" + name + "님이 입장~! \\(^@^)/");

				// 대화명과 소켓 정보를 Map에 저장한다.
				clients.put(name, socket);

				System.out.println("현재 서버 접속자 수 : " + clients.size() + "명");

				// 이후 메세지 처리는 반복문으로 처리한다.
				// 한 클라이언트가 보낸 메세지를 다른 모든 클라이언트에게 보내준다.
				while (dis != null) {
					sendMessage(dis.readUTF(), name); //안녕, 성겸
				}
			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				// 이 finally 영역이 실행된다는 의미는 클라이언트의 접속이 종료되었다는 의미
				sendMessage(name + "님이 퇴장... (T^T)");

				// Map에서 해당 대화명을 삭제한다.
				clients.remove(name);

				System.out.println("접속 종료 : [" + socket.getInetAddress() + " : " + socket.getLocalPort() + "]");
				System.out.println("현재 서버 접속자 수 : " + clients.size() + "명");
			}

		}
	}
	
	public static void main(String[] args) {
		new MultiChatServer().serverStart();
	}
}
