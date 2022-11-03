package view;

import java.io.File;

public class View {
	static EmployeeHome home;
	static EmployeeList list;
	static EmployeeInfo info;
	static DeptList dList;
	static DeptInfo dInfo;
	
	static void makeFolder(String path) {
		File dir = new File(path);
		if( ! dir.exists() ) dir.mkdirs();
	}
}
