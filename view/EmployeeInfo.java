package view;

import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.util.Scanner;
import employees.EmployeeDAO;
import employees.EmployeeDTO;

public class EmployeeInfo {
	private Scanner sc;
	EmployeeInfo(Scanner sc){
		this.sc = sc;
	}
	
	public void displayHtml(int employee_id) {
		
		EmployeeDAO dao = new EmployeeDAO(); //DB연결하기 위한 객체
		EmployeeDTO dto = dao.employee_info(employee_id); //특정사번 사원의 정보
		
		String path = "d:/io/view/", filename = "emp_info.html";
		View.makeFolder(path);
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(path + filename);
			
			out.print("<html>");
			out.print("<head>");
			out.print("<link href='style.css' rel='stylesheet'>");
			out.print("</head>");
			out.print("<body>");
			
			out.print("<h2>사원정보</h2>");
			out.print("<table border=1 style='width:600px'>");
			out.print("<tr>");
			out.printf("<th style='width:160px'>사번</th><td>%d</td>", dto.getEmployee_id());
			out.print("</tr>");
			out.print("<tr>");
			out.printf("<th>성명</th><td>%s</td>", dto.getName());
			out.print("</tr>");
			out.print("<tr>");
			out.printf("<th>부서명</th><td>%s</td>", dto.getDepartment_name());
			out.print("</tr>");
			out.print("<tr>");
			out.printf("<th>이메일</th><td>%s</td>", dto.getEmail());
			out.print("</tr>");
			out.print("<tr>");
			out.printf("<th>전화번호</th><td>%s</td>", dto.getPhone_number());
			out.print("</tr>");
			out.print("<tr>");
			out.printf("<th>급여</th><td>%d</td>", dto.getSalary());
			out.print("</tr>");
			out.print("<tr>");
			out.printf("<th>입사일</th><td>%s</td>", dto.getHire_date());
			out.print("</tr>");
			out.print("</table>");
			out.print("</body>");
			out.print("</html>");
			
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		try {
			Desktop.getDesktop().browse(new URI("file://"+path+filename));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}//HTML
	
	public void display(int employee_id) {
		displayHtml(employee_id);
		
		EmployeeDAO dao = new EmployeeDAO();
		EmployeeDTO dto = dao.employee_info(employee_id);
		
		System.out.println("\t\t\t[ 사원정보 ]");
		if( dto == null ) {
			System.out.println("사원정보 없습니다");
		}else {
			System.out.printf("%-15s\t: %d\n",
									"사번", dto.getEmployee_id());
			System.out.printf("%-15s\t: %s\n",
					"성명", dto.getName());
			System.out.printf("%-10s\t: %s\n",
					"이메일", dto.getEmail());
			System.out.printf("%-10s\t: %s\n",
					"전화번호", dto.getPhone_number());
			System.out.printf("%-10s\t: %s\n",
					"입사일", dto.getHire_date());
			System.out.printf("%-15s\t: %d\n",
					"급여", dto.getSalary());
			System.out.printf("%-10s\t: %s\n",
					"부서명", dto.getDepartment_name());
		}//if
		
		System.out.println("1.홈화면   2.사원목록   0.종료");
		System.out.print("입력 : ");
		int menu = sc.nextInt();
		if( menu == 1 ) {
			View.home.display();
		}else if( menu == 2 ) {
			View.list.display();
		}else if( menu == 0 ) {
			System.exit(0);
		}
		
	}//display
	
}//class
