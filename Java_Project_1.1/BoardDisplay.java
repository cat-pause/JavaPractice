package test;

import java.util.*;

public class BoardDisplay {
	
	//게시판 리스트 출력
	void BoardList() {
		BoardDAO bdao = new BoardDAO();
		ArrayList<BoardDTO> list = bdao.selectBoard();
		System.out.println("==========================================");
		System.out.println("\t\t게시판");
		System.out.println("==========================================");
		System.out.println("글번호\t제목\t작성자\t작성일");
		System.out.println("------------------------------------------");
		for( BoardDTO dto : list ) {
			System.out.print(dto.getNo() + "\t");
			System.out.print(dto.getTitle() + "\t");
			System.out.print(dto.getWriter() + "\t");
			System.out.print(dto.getTimestamp() + "\n");
		}
		System.out.println("==========================================");
	}
	
	//선택한 게시글 내용 출력
	void BoardInfo(int no) {
		BoardDAO bdao = new BoardDAO();
		ArrayList<BoardDTO> list = bdao.selectBoardInfo(no);
		String rContent = bdao.returnContent(no);
		StringTokenizer token = new StringTokenizer(rContent, " ");
		int count = 0;
		for( BoardDTO dto : list ) {
			System.out.println("========================================");
			System.out.printf("[%d] %s\n", no, dto.getTitle());
			System.out.println("========================================");
			System.out.printf("작성자 : %s\t작성일 : %s\n", dto.getWriter(), dto.getTimestamp());
			System.out.println("----------------------------------------");
			while(token.hasMoreTokens()) {
				count++;
				String data = token.nextToken();
				if(count%5 != 0) {
					System.out.print(data+" ");
				}else {
					System.out.println(data);
				}
			}//while
			System.out.println();
			System.out.println("========================================");
		}
	}
	
}//class
