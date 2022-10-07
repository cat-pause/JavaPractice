package ex;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberList {
	private ArrayList<MemberDTO> list;
	private Scanner sc;
	PrintWriter out = null;
	
	MemberList(ArrayList<MemberDTO> list, Scanner sc){
		this.list = list;
		this.sc = sc;
	}
	void display(MemberView view) {
		// 회원목록을 조회
		MemberDAO dao = new MemberDAO(list);
		ArrayList<MemberDTO> members = dao.list();
		
		System.out.println("번호 \t 성명 \t\t 성별 \t 전화번호");
		int no = 1;
		for( MemberDTO dto : members) {
			System.out.printf("%d \t %s \t %s \t %s \n", 
					no++, dto.getName(), dto.getGender(), dto.getPhone());
		}
		
		System.out.print("1.회원정보 보기   2.돌아가기   3.[저장]");
		no = sc.nextInt();
		if(no == 1) {
			System.out.print("확인할 회원 번호 : ");
			int num = sc.nextInt();
			view.display(num);
		}else if( no == 2 ) {
			// 돌아가기 ( if문 탈출 )
		}else if( no == 3 ) {
			//path에 메모리 데이터 filename.txt로 저장
			System.out.print("파일 이름 : ");
			String filename = sc.next();
			String path = "d:/io/MemberList";
			File folder = new File(path);
			if(! folder.exists()) folder.mkdirs();
			
			try {
				out = new PrintWriter(path + "/"+filename + ".txt");
				for(int i = 0; i < list.size(); i++) {
					out.print(members.get(i).getName()+",");
					out.print(members.get(i).getGender()+",");
					out.print(members.get(i).getPhone()+"\n");	//arrayList 인덱스 구분
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				out.close();
			}
			
		}
		//1.매번 보기화면 생성
//		MemberView html = new MemberView(sc, list).display(no);
//		html.display(no);
	}
	
}
