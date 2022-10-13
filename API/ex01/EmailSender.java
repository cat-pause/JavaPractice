package ex01;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class EmailSender {
	// 보내는이의 메일주소, 이름, 비밀번호
	Scanner sc = new Scanner(System.in);
	private String emailAddress, senderName, emailPw;
	
	EmailSender(String emailAddress, String senderName, String emailPw){
		this.emailAddress = emailAddress;
		this.senderName = senderName;
		this.emailPw = emailPw;
	}
	
	// HTML 형식 메일 보내기
	void sendHTML(String email, String name) {
		HtmlEmail mail = new HtmlEmail();
		mail.setHostName("smtp." + emailAddress.substring(emailAddress.indexOf("@")+1));
		mail.setAuthentication(emailAddress, emailPw);
		mail.setSSLOnConnect(true);
		
		try {
			mail.setFrom(emailAddress, senderName);
			mail.setCharset("utf-8");
			mail.addTo(email, name);
			mail.setSubject("html 형식 메일");
			
			StringBuffer msg = new StringBuffer();
			msg.append("<html>");
			msg.append("<body>");
			msg.append("<h2>").append(name).append("님 생일 축하합니다</h2>");
			msg.append("<div>생일 축하 쿠폰이 발급되었습니다.</div>");
			msg.append("<a target='_blank' href='https://www.naver.com/'>네이버</a>");
			msg.append("<img src='https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569__340.jpg'>");
			msg.append("</body>");
			msg.append("</html>");
			mail.setHtmlMsg(msg.toString());
			
			EmailAttachment file = new EmailAttachment();
			file.setURL(new URL("https://cdn.pixabay.com/photo/2016/11/06/05/36/lake-1802337__340.jpg"));
			mail.attach(file);
			
			mail.send();
		}catch(Exception e) {
			
		}
	}
	
	// 파일첨부 메일 보내기
	void sendAttach(String email, String name) {
		MultiPartEmail mail = new MultiPartEmail();
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		// 메일서버 지정
		mail.setHostName("smtp." + emailAddress.substring(emailAddress.indexOf("@")+1));
		// 로그인
		mail.setAuthentication(emailAddress, emailPw);
		// 로그인 버튼클릭
		mail.setSSLOnConnect(true);
		
		try {
			mail.setFrom(emailAddress, senderName); // 보내는이 지정
			mail.addTo(email, name); // 받는이 지정
			
			//제목 작성
			mail.setSubject("html ( 첨부파일 )");
			//내용 작성
			mail.setMsg(name+"님! 생일 축하합니다 \n생일쿠폰이 발급되었습니다\n"
					+ "당사 사이트에서 쿠폰함을 확인하세요! ");
			//파일 첨부
			EmailAttachment file = new EmailAttachment();
			file.setPath("D:\\html용 이미지.png");
			mail.attach(file); //첨부하기 버튼 클릭
			//파일 추가 첨부
			file = new EmailAttachment();
			file.setURL( new URL("https://cdn.pixabay.com/photo/2022/08/15/09/14/koyasan-temple-7387445__340.jpg"));
			mail.attach(file);
			// 메일 보내기 클릭
			mail.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	// 메일 보내기
	void sendSimple(String email, String name) {
		// 받는 사람의 메일주소, 이름
		SimpleEmail mail = new SimpleEmail();
		mail.setCharset("utf-8");
		mail.setDebug(true); // 메일 전송 과정 확인하도록 로그출력
		
		String host = emailAddress.substring(emailAddress.indexOf("@")+1);
		mail.setHostName("smtp."+ host);
		
		// 아이디/비밀번호를 입력 후, 로그인 버튼 클릭
		mail.setAuthentication(emailAddress, emailPw);
		mail.setSSLOnConnect(true); //로그인 버튼 클릭
		
		try {
			// 메일 보내는이 지정
			mail.setFrom(emailAddress, senderName);
			// 메일 받는이 지정
			mail.addTo(email, name);
			
			// 메일 제목
//			System.out.print("메일 제목 작성 : ");
//			String title = sc.next();
			mail.setSubject("title");
			
			// 메일 내용
			mail.setMsg("메일 내용");
			
			// 메일 보내기 버튼 클릭
			mail.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
	}//sendSimple()
	
}//class
