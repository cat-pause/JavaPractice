package view;

import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.util.Scanner;

public class EmployeeHome {
	private Scanner sc;
	
	EmployeeHome(Scanner sc){
		this.sc = sc;
	}
	public void displayHtml() {
		String path = "d:/io/view/", filename = "home.html";
		View.makeFolder(path);
		//쓰기작업
		PrintWriter out = null;
		try {
			out = new PrintWriter(path + filename);
			
			out.print("<html>");
			out.print("<head>");
			out.print("<link href='style.css' rel='stylesheet'>");
			out.print("</head>");
			out.print("<body>");
			
			out.print("<ul>");
			out.print("<li><a href='emp_list.html'>사원목록</a></li>");
			out.print("<li><a href='dept_list.html'>부서목록</a></li>");
			out.print("</ul>");
			
			out.print("</body>");
			out.print("</html>");
			
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		try {
			Desktop.getDesktop().browse(
					new URI("file://"+ path + filename));
		}catch(Exception e) {}
	}//HTML
	public void display() {
		
		displayHtml();
		
		System.out.println("1.사원목록   2.부서목록   0.[종료]");
		System.out.print("입력 : ");
		int menu = sc.nextInt();
		if( menu == 1 ) {
			//사원목록
			View.list.display();
		}else if( menu == 2 ) {
			//부서목록 - 사원이 있는 부서 목록만 조회 (쿼리문에서 not null 부서)
			//부서 선택시 해당 부서코드, 부서명, 부장이름, 부서도시명
			View.dList.display();
		}else if( menu == 0 ) {
			System.exit(0);
		}
	}//display
	
}//class
