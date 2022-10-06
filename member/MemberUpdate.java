package ex;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberUpdate {
	private Scanner sc;
	private ArrayList<MemberDTO> list;
	MemberUpdate(ArrayList<MemberDTO> list, Scanner sc){
		this.list = list;
		this.sc = sc;
	}
	void update(int no) {
		MemberDAO dao = new MemberDAO(list);
		MemberDTO dto = dao.member_view(no);
		System.out.println("< 회원 정보 수정 >");
		System.out.print(dto.getName() + " --> ");
		String inputName = sc.next();
		System.out.print(dto.getGender() + " --> ");
		String inputGender = sc.next();
		System.out.print(dto.getPhone() + " --> ");
		String inputPhone = sc.next();
		System.out.println();
		
		dto.setName(inputName);
		dto.setGender(inputGender);
		dto.setPhone(inputPhone);
		
	}
}
