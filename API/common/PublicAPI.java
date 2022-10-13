package common;

import java.io.*;
import java.net.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PublicAPI {
	private String key = "FPgj2NXbJw46TcGkmAfZEiYFDbxilys7KLjk3KaB7AfeJE00ZhPNM0M8unwbsI69fSmT8SNfVEimE6ZZ2U14hA%3D%3D";

	public String getKey() {
		return key;
	}
	
	// 문자열을 JSON으로 변환 (json lib 필요)
	public JSONArray covidAPItoJSON( StringBuffer url ) throws JSONException, IOException {
		JSONObject json = new JSONObject(requestAPI( url ));
		
		JSONArray array = json.getJSONArray("data");
			
			
		return array;
	}
	
	// 공공데이터 요청
	public String requestAPI( StringBuffer url ) throws IOException {
		String response = "";
		try {
			HttpURLConnection conn = (HttpURLConnection)new URL( url.toString() ).openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			BufferedReader reader;
			if( conn.getResponseCode() == 200) {
				reader = new BufferedReader( new InputStreamReader( conn.getInputStream(), "utf-8" ) );
			} else {
				reader = new BufferedReader( new InputStreamReader( conn.getErrorStream(), "utf-8" ) );
			}
			
			String line;
			url = new StringBuffer();
			while( (line = reader.readLine()) != null ) {
				url.append(line);
			}
			response = url.toString();
			conn.disconnect();
			reader.close();
			
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		return response;
	}
	
}//class
