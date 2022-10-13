package covid;

import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import org.json.*;

import common.PublicAPI;

public class CenterList {
private PublicAPI api;	
	
	public CenterList(PublicAPI api) {
		this.api = api;
	}
	
	public void display() throws JSONException, IOException {
		// https://api.odcloud.kr/api/15077586/v1/centers
		// ?page=1&perPage=10
		// &serviceKey=<<KEY>>
		StringBuffer url = new StringBuffer("https://api.odcloud.kr/api/15077586/v1/centers");
		url.append("&serviceKey=").append(api.getKey());
		
		viewHtml( url );	// Exception ( covidAPItoJSON )
	} //display()
	
	public void viewHtml( StringBuffer url ) throws JSONException, IOException {
		
			JSONArray array = api.covidAPItoJSON(url);		// Exception (throws)
			String path = "d:/io/api/covid";
			File dir = new File(path);
			if( ! dir.exists() ) dir.mkdirs();
			String fileName = "center.html";
			
			PrintWriter out = null;
			try {
				out = new PrintWriter(path + "/" + fileName);	// Exception ( try/catch )
				
				out.print("<html>");
				out.print("<body>");
				out.print("<h3>코로나 예방접종센터 조회</h3>");
				out.print("<table border='1' style='width:1000px'>");
				out.print("<tr>");
				out.print("<th style='width:150px'>센터명</th>");
				out.print("<th style='width:100px'>시설명</th>");
				out.print("<th style='width:100px'>전화번호</th>");
				out.print("<th style='width:100px'>주소</th>");
				
				for(int i = 0; i < array.length(); i++) {
					JSONObject center = array.getJSONObject(i);
					out.printf("<tr><th>%s</th><th>%s</th><th>%s</th><th>%s</th></tr>"
							,center.getString("centerName")
							,center.getString("facilityName")
							,center.getString("phoneNumber")
							,center.getString("address"));
				}
				
				out.print("</tr>");
				out.print("</table>");
				out.print("</body>");
				out.print("</html>");
				out.flush();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			String link = "file://"+path+"/"+fileName;
			try {
				Desktop.getDesktop().browse(new URI(link));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
	}//viewHtml()
}//class
