package test;

import java.sql.*;
import java.util.*;

public class BoardDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	private Statement st;
	
	//DB연결
	void connect() {
		//드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@211.223.59.99:1521:xe","smart02","0000");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//connect()
	void disconnect() {
		if( rs!=null ) 
			try { rs.close(); } catch (Exception e) {e.printStackTrace();}
		if( ps!=null )
			try {ps.close();} catch (Exception e) {e.printStackTrace();}
		if( conn!=null )
			try {conn.close();} catch (Exception e) {e.printStackTrace();}
		if( st!=null )
			try {st.close();} catch (Exception e) {e.printStackTrace();}
	}//disconnect()
	
	//게시판 리스트 조회
	ArrayList<BoardDTO> selectBoard() {
		connect();
		String sql = "SELECT no, title, writer, "
				+ "TO_CHAR(timestamp, 'YYYY/MM/DD HH24:MI') time "
				+ "FROM tbl_java_board ";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			while( rs.next() ) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setTimestamp(rs.getString("time"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//게시판 번호 지정
	int boardNo() {
		connect();
		int returnNo = 0;
		String sql = "SELECT no FROM tbl_java_board ORDER BY 1 DESC";
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			if( rs.next() ) {
				returnNo = rs.getInt("no")+1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return returnNo;
	}
	
	//게시글 작성
	void insertBoard(String title, String content, String id) {
		String sql = "INSERT INTO tbl_java_board "
					+ "VALUES (?, ?, ?, ?, systimestamp)";
		int bNo = boardNo();
		connect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bNo);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.setString(4, id);
			rs = ps.executeQuery();
			System.out.println("\n[ 게시글 작성 완료 ]\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	//특정 게시글 정보 조회
	ArrayList<BoardDTO> selectBoardInfo(int no) {
		connect();
		String sql = "SELECT no, title, content, writer, "
				+ "TO_CHAR(timestamp, 'YYYY/MM/DD HH24:MI') time "
				+ "FROM tbl_java_board "
				+ "WHERE no = ? ";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		BoardDTO bdto = new BoardDTO();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			while( rs.next() ) {
				bdto.setNo(rs.getInt("no"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setTimestamp(rs.getString("time"));
				list.add(bdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//특정 게시글 내용만 리턴
	String returnContent(int no) {
		connect();
		String rContent = null;
		String sql = "SELECT content FROM tbl_java_board "
				+ "WHERE no = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if( rs.next() ) {
				rContent = rs.getString("content");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return rContent;
	}
	
	//게시글 전부 삭제
	void deleteBoardAll() {
		connect();
		String sql = "DELETE FROM tbl_java_board ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			System.out.println("[ 게시글 삭제 완료 ]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	//특정 게시글 삭제
	void deleteBoard(int no) {
		connect();
		String sql = "DELETE FROM tbl_java_board WHERE no = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			System.out.println("해당 게시글 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	//게시글 번호 확인
	int checkNo(int no) {
		connect();
		int result = 0;  //0이면 불일치, 1이면 일치
		String sql = "SELECT no FROM tbl_java_board "
				+ "WHERE no = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if( rs.next() ) {
				result = 1;
			}else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
	
}//class
