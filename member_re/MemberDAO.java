package member;

import java.util.ArrayList;

public class MemberDAO {
	
	private ArrayList<MemberDTO> list;
	
	MemberDAO(ArrayList<MemberDTO> list){
		this.list = list;
	}
	
	//회원정보 저장
	void insert(MemberDTO dto) {
		list.add(dto);
	}
	
	//"선택한" 회원정보 조회
	MemberDTO member_view(int no) {
		MemberDTO dto = list.get(no-1);
		return dto;
	}
	
	//회원정보 목록 조회
	ArrayList<MemberDTO> list(){
		return list;
	}
	
}
