package com.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shop.common.JDBCConnection;
import com.shop.common.MemberVO;

public class MemberDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	String sql = "";
	int cnt = 0;

	public ArrayList<MemberVO> getMemberList() {
		ArrayList<MemberVO> mvo = null;
		try {
			conn = JDBCConnection.getConnection();
			sql = "select * from Member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			mvo = new ArrayList<MemberVO>();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setMno(rs.getInt("mno"));
				vo.setMname(rs.getString("mname"));
				vo.setMid(rs.getString("mid"));
				vo.setMpw(rs.getString("mpw"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setBirth(rs.getString("birth"));
				mvo.add(vo);
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
		return mvo;
	}

	public MemberVO getMember(int mno) {
		MemberVO vo = new MemberVO();
		try {
			conn = JDBCConnection.getConnection();
			sql = "select * from member where mno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setMno(rs.getInt("mno"));
				vo.setMname(rs.getString("mname"));
				vo.setMid(rs.getString("mid"));
				vo.setMpw(rs.getString("mpw"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setBirth(rs.getString("birth"));
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

	public int addMember(MemberVO mvo) { 
		try { 
			conn = JDBCConnection.getConnection(); 
			sql = "insert into member values((select nvl(max(mno), 0)+1 from member), ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, mvo.getMname()); 
			pstmt.setString(2, mvo.getMid());
			pstmt.setString(3, mvo.getMpw());
			pstmt.setString(4, mvo.getTel());
			pstmt.setString(5, mvo.getAddress());
			pstmt.setString(6, mvo.getBirth());
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
	 

	public int editMember(MemberVO mvo) {
		try {
			conn = JDBCConnection.getConnection();
			sql = "update member set mname=?, mid=?, mpw=?, tel=?, address=?, birth=? where mno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mvo.getMname());
			pstmt.setString(2, mvo.getMid());
			pstmt.setString(3, mvo.getMpw());
			pstmt.setString(4, mvo.getTel());
			pstmt.setString(5, mvo.getAddress());
			pstmt.setString(6, mvo.getBirth());
			pstmt.setInt(7, mvo.getMno());
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

	public int delMember(MemberVO mvo) {
		try {
			conn = JDBCConnection.getConnection();
			sql = "delete from member where mno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mvo.getMno());
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