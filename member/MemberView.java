package ex;

import java.util.*;

public class MemberView {
	private Scanner sc;
	private ArrayList<MemberDTO> list;
	MemberView(Scanner sc, ArrayList<MemberDTO> list){
		this.sc = sc;
		this.list = list;
	}
	
	void display(int no) {
		// MemberList에서 선택한 회원의 정보(배열에서 조회) 출력
		MemberDAO dao =new MemberDAO(list);
		MemberDTO dto = dao.member_view(no);
		System.out.println("< 회원 정보 >");
		System.out.println("성명 : " + dto.getName());
		System.out.println("성별 : " + dto.getGender());
		System.out.println("전화번호 : " + dto.getPhone());
		
		System.out.print("1.정보수정   2.회원삭제");
		int button = sc.nextInt();
		if( button == 1 ) {
			//회원정보 수정화면:클래스 하나 만들어
			MemberUpdate update = new MemberUpdate(list, sc);
			update.update(no);
		}else if(button == 2) {
			// DB에서 해당 회원정보 삭제
			list.remove(no-1);
			// 후에 목록 화면으로 연결
			
		}
	}

	
} //class
