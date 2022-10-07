package ex;

import java.io.*;
import java.util.*;

public class MemberRead {
	private Scanner sc;
	private ArrayList<MemberDTO> list;
	private String name, gender, phone;
	MemberDTO dto;
	
	MemberRead(ArrayList<MemberDTO> list, Scanner sc){
		this.list = list;
		this.sc = sc;
	}
	
	BufferedReader br = null;
	ArrayList<MemberDTO> read() {
		String line;
		try {
			String inputFileName = null;
			System.out.print("불러올 파일명(.txt) : ");
			inputFileName = sc.next();
			String readFile = "D:\\io\\MemberList/" +inputFileName+ ".txt";		//파일명 scanner로 지정해야함
			br = new BufferedReader(new FileReader(readFile));
			while( (line = br.readLine()) != null ) {
				dto= new MemberDTO(name, gender, phone);
				String[] datas = line.split(",");
				dto.setName(datas[0]);
				dto.setGender(datas[1]);
				dto.setPhone(datas[2]);
				list.add(dto);
			}
			System.out.println(" ===== 불러오기 완료 ===== \n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	
}
