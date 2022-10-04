package ex;

import java.io.*;
import java.util.Scanner;

public class StudentMain {
	public static void main(String[] args) {
		String readFile = "D:\\명단.txt";
		Scanner sc = new Scanner(System.in);
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(readFile));
			String temp;
			System.out.print("이름 입력 : ");
			String inputName = sc.nextLine();
			while((temp=br.readLine()) != null) {
//				System.out.println(temp);
				String[] array = temp.split(",");
				if(inputName.equals(array[0])) {
					System.out.println("성명 : " + array[0]);
					System.out.println("성별 : " + array[1]);
					System.out.println("전화번호 : " + array[2]);
				} //if
			} //while
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {br.close();} catch (IOException e) {e.printStackTrace();}
		}
		sc.close();
		
	} //main()
}//class
