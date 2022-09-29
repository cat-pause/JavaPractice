package drink2;

import java.util.Scanner;

public class Common {
	static int minNumber(int in, String item, Scanner sc) {
		while(in < 0) {
			System.out.printf(
				"%s 0이상 입력하세요 재입력: ", item );
			in = sc.nextInt();
		}
		return in;
	}
}
