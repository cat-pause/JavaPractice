package view;

import java.awt.Desktop;
import java.io.*;
import java.net.*;
import java.util.*;
import employees.EmployeeDAO;
import employees.EmployeeDTO;

public class DeptInfo {
	private Scanner sc;
	DeptInfo(Scanner sc){
		this.sc = sc;
	}
	
	public void displayHtml(int dept_id) {
		DeptDAO dao = new DeptDAO();
		DeptDTO dto = dao.DeptInfo(dept_id);
		ArrayList<EmployeeDTO> list = new EmployeeDAO().ed_info(dept_id);
		
		String path = "d:/io/view/", filename = "dept_info.html";
		View.makeFolder(path);
		PrintWriter out = null;
		try {
			out = new PrintWriter(path + filename);
			out.print("<html>");
			out.print("<head>");
			out.print("<link href='style.css' rel='stylesheet'>");
			out.print("</head>");
			out.print("<body>");
			
			out.print("<h2>부서정보</h2>");
			out.print("<table border=1 style='width:500px'>");
			out.printf("<tr><th>부서코드</th><th>%d</th></tr>", dto.getDepartment_id());
			out.printf("<tr><th>부서명</th><th>%s</th></tr>", dto.getDepartment_name());
			out.printf("<tr><th>부서장명</th><th>%s</th></tr>", dto.getManager_name());
			out.printf("<tr><th>도시명</th><th>%s</th></tr>", dto.getCity());
			out.print("</table>");
			
			//해당 부서 사원목록
			out.printf("<h2>%d번 부서 사원목록</h2>", dept_id);
			out.print("<table border=1>");
			out.print("<tr><th>부서명</th><th>사번</th><th>성명</th><th>업무</th><th>급여</th><th>입사일</th></tr>");
			for( EmployeeDTO edto : list ) {
				out.print("<tr>");
				out.printf("<td>%s</td>", edto.getDepartment_name());
				out.printf("<td>%d</td>", edto.getEmployee_id());
				out.printf("<td><a href='emp_info.html'>%s</a></td>", edto.getName());
				out.printf("<td>%s</td>", edto.getJob_title());
				out.printf("<td>%d</td>", edto.getSalary());
				out.printf("<td>%s</td>", edto.getHire_date());
				out.print("</tr>");
			}
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
	
	public void display(int dept_id) {
		displayHtml(dept_id);
		
		DeptDAO dao = new DeptDAO();
		DeptDTO dto = dao.DeptInfo(dept_id);
		
		System.out.println("\t\t[부서정보]");
		if( dto == null ) {
			System.out.println("부서 정보가 없습니다");
		}else {
			System.out.printf("%s\t%d","부서코드",dto.getDepartment_id());
			System.out.printf("%s\t%s","부서명",dto.getDepartment_name());
			System.out.printf("%s\t%s","부서장명",dto.getManager_name());
			System.out.printf("%s\t%s\n","도시명",dto.getCity());
			
//			//해당 부서원 목록
//			System.out.println("["+dept_id+"]번 부서 사원목록");
		}
		System.out.println("1.홈으로   2.부서목록   0.종료");
		System.out.print("입력 : ");
		int menu = sc.nextInt();
		if( menu == 1 ) {
			View.home.display();
		}else if( menu == 2 ) {
			View.dList.display();
		}else if ( menu == 3 ) {
			System.exit(0);
		}
	}//display
	
}//class
