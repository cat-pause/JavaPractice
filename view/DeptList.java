package view;

import java.awt.Desktop;
import java.io.PrintWriter;
import java.net.URI;
import java.util.*;

public class DeptList {
	private Scanner sc;
	DeptList(Scanner sc) {
		this.sc = sc;
	}
	
	public void displayHtml() {
		ArrayList<DeptDTO> dList = new DeptDAO().DeptList();
		String path = "d:/io/view/", filename = "dept_list.html";
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
			out.print("<tr>");
			out.print("<th>부서코드</th>");
			out.print("<th>부서명</th>");
			out.print("<th>관리자코드</th>");
			out.print("<th>도시코드</th>");
			out.print("</tr>");
			for( DeptDTO dto : dList ) {
				out.print("<tr>");
				out.printf("<td>%d</td>",dto.getDepartment_id());
				out.printf("<td><a href='dept_info.html'>%s</a></td>",dto.getDepartment_name());
				out.printf("<td>%d</td>",dto.getManager_id());
				out.printf("<td>%d</td>",dto.getLocation_id());
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
	}
	
	public void display() {
		displayHtml();
		DeptDAO dao = new DeptDAO();
		ArrayList<DeptDTO> dList = dao.DeptList();
		System.out.println("\t\t[ 부서목록 ]");
		System.out.println("부서코드\t부서명\t관리자코드\t지역코드");
		for( DeptDTO dto : dList ) {
			System.out.print(dto.getDepartment_id() + "\t");
			System.out.print(dto.getDepartment_name() + "\t");
			System.out.print(dto.getManager_id() + "\t");
			System.out.print(dto.getLocation_id() + "\n");
		}//for
		
		System.out.println("1.홈으로   2.부서정보   0.종료");
		System.out.print("입력 : ");
		int menu = sc.nextInt();
		if( menu == 1 ) {
			View.home.display();
		}else if( menu == 2 ) {
			//부서 정보 DeptInfo
			System.out.print("부서 코드 : ");
			int dept_id = sc.nextInt();
			View.dInfo.display(dept_id);
		}else if( menu == 3 ) {
			System.exit(0);
		}
	}//display
}//class
