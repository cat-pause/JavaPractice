package member;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberMain {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		MemberJoin join = new MemberJoin(list, sc);
		MemberList members = new MemberList(list, sc);

		// Main 화면
		int inputKey = 0;
		do {
			
			System.out.println(" ◈◈ 회원관리 프로그램 ◈◈ ");
			System.out.print("1.회원가입   2.회원목록   3.불러오기   0.종료\n입력 : ");
			inputKey = sc.nextInt();
			if(inputKey == 1) {
				//회원가입
				join.display();
			}else if(inputKey == 2) {
				//회원목록 조회
				members.display();
			}else if(inputKey == 3) {
				System.out.println("불러오기 준비중...");
			}
			
		}while(inputKey != 0);
		System.out.println("프로그램 종료...");
		sc.close();
	}
}
