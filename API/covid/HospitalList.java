package covid;

import java.awt.Desktop;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
//import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import common.PublicAPI;

// 병원 목록 화면
public class HospitalList {
	private PublicAPI api;	//데이터 담는 방법 : 필드초기화(생성자,setter메소드)
	
	public HospitalList(PublicAPI api) {
		this.api = api;
	}
	
	public void display() throws JSONException, IOException {
		// API 요청
		// https://api.odcloud.kr/api/apnmOrg/v1/list?page=1&perPage=10&
		// serviceKey=data-portal-test-key
		// &returnType=JSON
		StringBuffer url = new StringBuffer("https://api.odcloud.kr/api/apnmOrg/v1/list");
		url.append("?serviceKey=").append(api.getKey());
		
	
		// 응답받은 정보 보기 (console.log)
//		viewConsole( url );
		viewHtml( url );
	}
	
	private void viewHtml(StringBuffer url) throws JSONException, IOException {
		JSONArray array = api.covidAPItoJSON(url);
		String path = "d:/io/api/covid";
		File dir = new File(path);
		if( ! dir.exists() ) dir.mkdirs();
		String filename = "hospital.html";
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(path + "/" + filename);
			
			out.print("<html>");
			out.print("<body>");
			out.print("<h3>코로나 예방접종 사전예약 의료기관</h3>");
			out.print("<table border='1' style='width:1000px'>");
			out.print("<tr>");
			out.print("<th style='width:220px'>병원명</th>");
			out.print("<th style='width:140px'>전화번호</th>");
			out.print("<th style='width:140px'>주소</th>");
			for(int i = 0; i < array.length(); i++) {
				JSONObject hospital = array.getJSONObject(i);
				out.printf("<tr><th>%s</th><th>%s</th><th>%s</th></tr>"
						,hospital.getString("orgnm")
						,hospital.getString("orgTlno")
						,hospital.getString("orgZipaddr"));
			}
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
		
		// 해당 경로 파일 열어주기
		String link = "file://"+path+"/"+filename;
		try {
			Desktop.getDesktop().browse(new URI(link));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
//	private void viewConsole( StringBuffer url ) throws JSONException, IOException {
//		System.out.println("병원명\t전화번호\t주소");
//		JSONArray array =  api.covidAPItoJSON( url );
//		for(int i = 0; i < array.length(); i++) {
//			JSONObject hospital = array.getJSONObject(i);
//			System.out.printf("%s\t%s\t%s\n",hospital.getString("orgnm"),hospital.getString("orgTlno"),hospital.getString("orgZipaddr"));
//		}
//		
//	}
	
}//class
