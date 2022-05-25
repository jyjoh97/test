package com.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shop.common.JDBCConnection;
import com.shop.common.BoardVO;

public class BoardDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	String sql = "";
	int cnt = 0;

	public ArrayList<BoardVO> getBoardList() {
		ArrayList<BoardVO> bvo = null;
		try {
			conn = JDBCConnection.getConnection();
			sql = "select * from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			bvo = new ArrayList<BoardVO>();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setBtitle(rs.getString("btitle"));
				vo.setBcontent(rs.getString("bcontent"));
				vo.setBwriter(rs.getString("bwriter"));
				vo.setBdate(rs.getDate("bdate"));
				bvo.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL구문 처리 실패");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("요청 실패");
			e.printStackTrace();
		} finally {
			JDBCConnection.close(rs, pstmt, conn);
		}
		return bvo;
	}

	public BoardVO getBoard(int bno) {
		BoardVO vo = new BoardVO();
		try {
			conn = JDBCConnection.getConnection();
			sql = "select * from board where bno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setBno(rs.getInt("bno"));
				vo.setBtitle(rs.getString("btitle"));
				vo.setBcontent(rs.getString("bcontent"));
				vo.setBwriter(rs.getString("bwriter"));
				vo.setBdate(rs.getDate("bdate"));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버로딩 실패.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL처리 실패");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("업무 처리 실패.");
			e.printStackTrace();
		} finally {
			JDBCConnection.close(rs, pstmt, conn);
		}
		return vo;
	}

	public int addBoard(BoardVO bvo) { 
		try { 
			conn = JDBCConnection.getConnection(); sql =
			"insert into member values((select nvl(max(mno), 0)+1 from member), ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, bvo.getBtitle()); 
			pstmt.setString(2, bvo.getBcontent());
			pstmt.setString(3, bvo.getBwriter());
			pstmt.setDate(4, bvo.getBdate());

			cnt = pstmt.executeUpdate(); 
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩이 실패되었습니다."); 
			e.printStackTrace(); 
		} catch(SQLException e) { 
	 		System.out.println("SQL구문이 처리되지 못했습니다.");
	 		e.printStackTrace(); 
	 	} catch(Exception e) {
	 		System.out.println("잘못된 요청으로 업무를 처리하지 못했습니다."); 
	 		e.printStackTrace(); 
	 	} finally { 
	 		JDBCConnection.close(rs, pstmt, conn);
	 	} 
		return cnt;
	}
	 

	public int editBoard(BoardVO bvo) {
		try {
			conn = JDBCConnection.getConnection();
			sql = "update member set btitle=?, bcontent=?, bwriter=?, bdate=sysdate where bno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getBtitle());
			pstmt.setString(2, bvo.getBcontent());
			pstmt.setString(3, bvo.getBwriter());
			pstmt.setDate(4, bvo.getBdate());
			pstmt.setInt(5, bvo.getBno());

			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩이 실패되었습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL구문이 처리되지 못했습니다.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("잘못된 요청으로 업무를 처리하지 못했습니다.");
			e.printStackTrace();
		} finally {
			JDBCConnection.close(rs, pstmt, conn);
		}
		return cnt;
	}

	public int delBoard(BoardVO bvo) {
		try {
			conn = JDBCConnection.getConnection();
			sql = "delete from board where bno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bvo.getBno());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩이 실패되었습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL구문이 처리되지 못했습니다.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("잘못된 요청으로 업무를 처리하지 못했습니다.");
			e.printStackTrace();
		} finally {
			JDBCConnection.close(rs, pstmt, conn);
		}
		return cnt;
	}

}