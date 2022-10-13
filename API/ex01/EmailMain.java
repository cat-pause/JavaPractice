package ex01;

public class EmailMain {
	public static void main(String[] args) {
		
		EmailSender email = new EmailSender("aquamori@naver.com","cat-pause","dkzndkahfl1");
		
		email.sendAttach("aquamori@naver.com", "김원희");
		
	} //main()
}
