package test;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	
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
		if( conn!=null)
			try {conn.close();} catch (Exception e) {e.printStackTrace();}
	}//disconnect()
	
	//제품 리스트 조회
	ArrayList<ProductDTO> pList() {
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		connect();
		try {
			String sql = "SELECT num, name as product_name, "
					+ "company, price, qty "
					+ "FROM tbl_java_product";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				//모든 물건 정보 담기
				ProductDTO dto = new ProductDTO();
				dto.setNum(rs.getInt("num"));
				dto.setP_name(rs.getString("product_name"));
				dto.setCompany(rs.getString("company"));
				dto.setPrice(rs.getInt("price"));
				dto.setQty(rs.getInt("qty"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}//pList
	
	//제품 이름으로 검색하기
	ArrayList<ProductDTO> searchProduct(String inputName) {
		connect();
		ProductDTO dto = null;
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String sql = "SELECT num, REPLACE(name, ' ', '') name, REPLACE(company, ' ','') company, price, qty "
				+ "FROM tbl_java_product WHERE name LIKE ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+inputName.trim()+"%");
			rs = ps.executeQuery();
			while( rs.next() ) {
				dto = new ProductDTO();
				dto.setNum(rs.getInt("num"));
				dto.setP_name(rs.getString("name"));
				dto.setCompany(rs.getString("company"));
				dto.setPrice(rs.getInt("price"));
				dto.setQty(rs.getInt("qty"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}//searchForName
	
	//제품번호로 제품 정보 조회
	ArrayList<ProductDTO> searchNum(int num) {
		connect();
		ProductDTO dto = null;
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String sql = "SELECT num, name, company, price, qty "
				+ "FROM tbl_java_product WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			while( rs.next() ) {
				dto = new ProductDTO();
				dto.setNum(rs.getInt("num"));
				dto.setP_name(rs.getString("name"));
				dto.setCompany(rs.getString("company"));
				dto.setPrice(rs.getInt("price"));
				dto.setQty(rs.getInt("qty"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}//searchNum
	
	//제조사 검색
	ArrayList<ProductDTO> searchCom(String inputName) {
		connect();
		ProductDTO dto = null;
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String sql = "SELECT num, name, company, price, qty "
				+ "FROM tbl_java_product WHERE company LIKE ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+inputName+"%");
			rs = ps.executeQuery();
			while( rs.next() ) {
				dto = new ProductDTO();
				dto.setNum(rs.getInt("num"));
				dto.setP_name(rs.getString("name"));
				dto.setCompany(rs.getString("company"));
				dto.setPrice(rs.getInt("price"));
				dto.setQty(rs.getInt("qty"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}//searchCom
	
	//상품 주문
	void orderProduct(int num, int orderCount) {
		connect();
		String sql = "UPDATE tbl_java_product "
					+ "SET qty = qty - ? "
					+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderCount);
			ps.setInt(2, num);
			rs = ps.executeQuery();
			System.out.println("...주문 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}//orderProduct
	
	//주문상품의 가격 조회
	int returnPrice(int num) {
		connect();
		int price = 0;
		String sql = "SELECT price FROM tbl_java_product WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if( rs.next() ) {
				price = rs.getInt("price");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return price;
	}//returnPrice	
	
	//제품 등록
	void insertProduct(int num, String name, String company, int price, int qty) {
		connect();
		String sql = "INSERT INTO tbl_java_product "
					+ "VALUES (?, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setString(2, name);
			ps.setString(3, company);
			ps.setInt(4, price);
			ps.setInt(5, qty);
			rs = ps.executeQuery();
			System.out.println("[등록 완료]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}//insertProduct
	
	//상품 삭제
	void deleteProduct(int num) {
		connect();
		String sql = "DELETE FROM tbl_java_product "
					+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			System.out.println("[삭제 완료]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}//deleteProduct
	
	//수량 증가
	void increaseQty(int num, int incQty) {
		connect();
		String sql = "UPDATE tbl_java_product SET qty = qty + ? "
					+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, incQty);
			ps.setInt(2, num);
			rs = ps.executeQuery();
			System.out.println("[수량 변경 완료]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}//increaseQty
	//수량 감소
	void descQty(int num, int desQty) {
		connect();
		String sql = "UPDATE tbl_java_product SET qty = qty - ? "
					+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, desQty);
			ps.setInt(2, num);
			rs = ps.executeQuery();
			System.out.println("[수량 변경 완료]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}//descQty
	//수량 수정
	void updateQty(int num, int upQty) {
		connect();
		String sql = "UPDATE tbl_java_product SET qty = ? "
				+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, upQty);
			ps.setInt(2, num);
			rs = ps.executeQuery();
			System.out.println("[수량 입력 완료]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}//updateQty
	
	//상품 수량만 조회
	int selectQty(int num) {
		connect();
		int qty = 0;
		String sql = "SELECT qty FROM tbl_java_product "
					+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if( rs.next() ) {
				qty = rs.getInt("qty");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return qty;
	}//selectQty
	
	//상품 이름만 조회
	String selectPname(int num) {
		connect();
		String name = null;
		String sql = "SELECT name FROM tbl_java_product "
					+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if( rs.next() ) {
				name = rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return name;
	}
	
	//제품명 변경
	void changeName(int num, String newName) {
		connect();
		String sql = "UPDATE tbl_java_product SET name = ? WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newName);
			ps.setInt(2, num);
			rs = ps.executeQuery();
			System.out.println("\n[ 제품명 변경 완료 ]\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	//제품 가격 수정
	void changePrice(int num, int newPrice) {
		connect();
		String sql = "UPDATE tbl_java_product SET price = ? WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, newPrice);
			ps.setInt(2, num);
			rs = ps.executeQuery();
			System.out.println("\n[ 가격 수정 완료 ]\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}//class
