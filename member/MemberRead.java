package ex;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
	String readFile = "D:\\io\\MemberList/7thSave.txt";		//파일명 scanner로 지정해야함
	ArrayList<MemberDTO> read() {
		String line;
		try {
			
			br = new BufferedReader(new FileReader(readFile));
			while( (line = br.readLine()) != null ) {
				dto= new MemberDTO(name, gender, phone);
				String[] datas = line.split(",");
				dto.setName(datas[0]);
				dto.setGender(datas[1]);
				dto.setPhone(datas[2]);
				list.add(dto);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	
}
