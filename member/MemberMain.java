package ex;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		MemberJoin join = new MemberJoin(sc, list);
		MemberList members = new MemberList(list, sc);
		MemberView view = new MemberView(sc, list);
		MemberRead read = new MemberRead(list, sc);
		
		// MemberMain : 화면
		int fnKey = 0;
		do {
			System.out.println(" << 회원관리 프로그램 >> ");
			System.out.print("1.회원가입    2.회원목록   3.불러오기   0.종료");
			fnKey = sc.nextInt();
			if(fnKey == 1) {
				join.display();
			}else if(fnKey == 2) {
				members.display(view);
			}else if(fnKey == 3) {
				read.read();
			}
			
		}while(fnKey != 0);
		System.out.println("프로그램 종료...");
		
		
	} //main()
}
