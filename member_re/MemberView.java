package member;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberView {
	
	private Scanner sc;
	private ArrayList<MemberDTO> list;
	
	MemberView(ArrayList<MemberDTO> list, Scanner sc){
		this.list = list;
		this.sc = sc;
	}
	
	void display(int no) {
		
		// MemberList 에서 선택한 회원(no)의 정보 출력하기
		MemberDAO dao = new MemberDAO(list);
		MemberDTO dto = dao.member_view(no);
		
		System.out.println();
		System.out.println("========================");
		System.out.println("< 회원 정보 >");
		System.out.println("------------------------");
		System.out.println("성명 : " + dto.getName());
		System.out.println("성별 : " + dto.getGender());
		System.out.println("전화번호 : " + dto.getPhone());
		System.out.println("========================");
		
		// 출력한 회원정보 수정/삭제/돌아가기
		System.out.print("1.정보수정   2.회원삭제   3.돌아가기\n입력 : ");
		int button = sc.nextInt();
		if( button == 1 ) {
			// 회원 정보 수정 (new Class)
			MemberUpdate update = new MemberUpdate(list, sc);
			update.update(no);
		}else if( button == 2 ) {
			// 회원 정보 삭제
			System.out.println(dto.getName() + "의 정보를 삭제하시겠습니까?");
			System.out.print("1.yes   2.no");
			int re = sc.nextInt();
			if( re == 1 ) {
				list.remove(no-1);
			} else if( re == 2 ) {
				// 메인화면으로 돌아가기
			}
		}else if( button == 3 ) {
			// 메인화면으로 돌아가기
		}
		
	}
	
}
