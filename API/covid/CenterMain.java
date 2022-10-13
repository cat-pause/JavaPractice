package covid;

import java.io.IOException;

import org.json.JSONException;

import common.PublicAPI;

public class CenterMain {
	public static void main(String[] args) throws JSONException, IOException {
		
		PublicAPI api = new PublicAPI();
		CenterList list = new CenterList(api);
		
		list.display();
		
	} //main
}//class
