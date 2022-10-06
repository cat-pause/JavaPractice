package ex;

import java.util.ArrayList;

public class MemberDAO {
	private ArrayList<MemberDTO> list;
	
	MemberDAO(ArrayList<MemberDTO> list){
		// DB연결 (배열에 접근)
		this.list = list;
	}
	// CRUD
	// 회원정보 저장 : Create
	void insert(MemberDTO dto) {
		//main에서 입력한 정보를 배열에 저장(DB)
		list.add(dto);
	}
	// 선택한 회원정보 조회 : Read
	MemberDTO member_view(int no) {
		// DB에서 선택한 회원의 정보 조회
		// 회원정보 화면에 출력하도록 데이터 리턴
		MemberDTO dto = list.get(no-1);
		return dto;
	}
	// 회원정보 목록 조회 : Read
	ArrayList<MemberDTO> list() {
		// list에서 조회해온 정보를 목록화면에 출력하도록 리턴
		return list;
	}
	// 회원정보 변경 : Update
	
	// 회원정보 삭제 : Delete
	
}
