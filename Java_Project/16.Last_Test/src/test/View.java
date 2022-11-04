package test;

import java.io.File;

public class View {
	static Login login;
	static AdminMode adminmode;
//	static UserMode usermode;
	static ProductList product_List;
	
	static void makeFolder(String path) {
		File dir = new File(path);
		if( ! dir.exists() ) dir.mkdirs();
	}
}//class
