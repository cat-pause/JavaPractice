package covid;

import java.io.IOException;

import org.json.JSONException;

import common.PublicAPI;

public class CovidMain {
	public static void main(String[] args) throws JSONException, IOException {
		
		PublicAPI api = new PublicAPI();
		HospitalList list = new HospitalList(api); // 병원목록 화면 생성
		//목록화면 보기
		list.display();
		
	} //main()
} //class
