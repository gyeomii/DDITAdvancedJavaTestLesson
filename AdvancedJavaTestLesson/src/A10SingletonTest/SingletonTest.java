package A10SingletonTest;

public class SingletonTest {
	public static void main(String[] args) {
		//MySingletonTest test1 = new MySingleton(); //new 명령 사용 불가
		
		//getInstance()메소드를 이용하여 객체 생성
		MySingleton test2 = MySingleton.getInstance();
		
		MySingleton test3 = MySingleton.getInstance();
		
		System.out.println("test2 : " + test2);
		System.out.println("test3 : " + test3);
	}
}
