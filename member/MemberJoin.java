package ex;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberJoin {
	private Scanner sc;
	private ArrayList<MemberDTO> list;
	
	void display() {
		System.out.println("< 회원가입 >");
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("성별 : ");
		String gender = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
//		System.out.print("1.가입하기\n");
//		if(sc.nextInt() == 1) {
		System.out.println();
			MemberDTO dto = new MemberDTO(name, gender, phone); // DB에 저장
			//DB에 저장( 배열에 저장 )
			MemberDAO dao = new MemberDAO(list);
			dao.insert(dto);
//			list.add(dto);
//		}//if
	}
	
	MemberJoin(Scanner sc, ArrayList<MemberDTO> list){
		this.sc = sc;
		this.list = list;
		
		
	}
	
}
