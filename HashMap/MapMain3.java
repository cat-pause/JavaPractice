package map;

import java.io.*;
import java.util.*;

public class MapMain3 {
	public static void main(String[] args) {
		//명단.txt br로 읽어와서
		//HastMap에 데이터를 담는다 (StudentDTO)
		//Key : 이름, 데이터 : StudentDTO(이름,성별,전화번호)
		//키보드로 누구의 정보를 확인할지 입력한다.
		//해당 학생 정보를 출력
		//해당 학생이 없으면, "해당 학생 없음" 출력
		Scanner sc = new Scanner(System.in);
		
		String readFile = "D:\\io\\MemberList/명단.txt";
		StudentDTO dto;
		HashMap<String, StudentDTO> student = new HashMap<String, StudentDTO>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(readFile));
			String line;
			String[] datas;
			while( (line = br.readLine()) != null ) {
				datas = line.split(",");
				dto = new StudentDTO(datas[0],datas[1],datas[2]);
				student.put(dto.getName(), dto);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {br.close();} catch (IOException e) {e.printStackTrace();}
		}
		
		while(true) {
			System.out.print("이름 입력 : ");
			String name = sc.next();
			System.out.println("\n" + name + "의 정보를 찾습니다...\n");
			
			if( student.containsKey(name) ) {
				System.out.println("성명 : " + student.get(name).getName());
				System.out.println("성별 : " + student.get(name).getGender());
				System.out.println("전화번호 : " + student.get(name).getPhone());
				//한번더 찾을지 묻기
				System.out.print("\n1.돌아가기   2.종료");
				int no = sc.nextInt();
				if(no == 1) {
					continue;
				}else {
					System.out.println("종료...");
					break;
				}//안쪽if문
			}else {
				System.out.println("-- 해당 학생 없음 --\n");
				continue;
			}//if문
			
		}//while문
		
		sc.close();
	}//main()
}
