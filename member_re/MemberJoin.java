package member;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberJoin {
	
	private Scanner sc;
	private ArrayList<MemberDTO> list;
	
	void display() {
		System.out.println(" ◈ 회원가입 ◈ ");
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("성별 : ");
		String gender = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.println();
		
		// ArrayList에 입력한 값 저장
		MemberDTO dto = new MemberDTO(name, gender, phone); //DTO에 저장
		MemberDAO dao = new MemberDAO(list); //ArrayList<MemberDTO> list를 MemberDAO에서 dao로 불러오기
		dao.insert(dto); //입력했던 값(name,gender,phone)을 윗줄의 dao에 있는 list에 담기
	}
	
	MemberJoin(ArrayList<MemberDTO> list, Scanner sc){
		this.list = list;
		this.sc = sc;
	}
	
}
