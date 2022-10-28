package member;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberList {
	private Scanner sc;
	private ArrayList<MemberDTO> list;
	
	MemberList(ArrayList<MemberDTO> list, Scanner sc){
		this.list = list;
		this.sc = sc;
	}
	
	void display() {
		
		MemberDAO dao = new MemberDAO(list);
		ArrayList<MemberDTO> members = dao.list();
		
		System.out.println("==============================================");
		System.out.println("번호 \t 성명 \t\t성별 \t 전화번호");
		System.out.println("------+----------+------------+---------------");
		
		int no = 1;
		for( MemberDTO dto : members ) {
			System.out.printf("%d \t %s \t\t%s \t %s \n",
					no++, dto.getName(), dto.getGender(), dto.getPhone() );
		}
		System.out.println("==============================================");
		
		//표시된 회원목록 중 한 회원 선택하기 ( 수정, 삭제 )
		
		System.out.print("1.회원정보 보기   2.돌아가기   3.[저장]\n입력 : ");
		no = sc.nextInt();
		if( no == 1 ) {
			System.out.print("확인할 회원 번호 : ");
			int num = sc.nextInt();
			MemberView view = new MemberView(list, sc);
			view.display(num);
		}else if( no == 2 ) {
			// 메인화면으로 돌아가기
		}else if( no == 3 ) {
			// txt파일로 회원목록 저장하기 (new Class)
			System.out.println("저장하기 준비중...");
		}
		
	}
	
}
