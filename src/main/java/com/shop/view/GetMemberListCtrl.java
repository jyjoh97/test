package com.shop.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.common.JDBCConnection;
import com.shop.common.MemberVO;


@WebServlet("/GetMemberListCtrl")
public class GetMemberListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetMemberListCtrl() {
        super();
        
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			conn = JDBCConnection.getConnection();
			sql = "select * from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList<MemberVO> mvo = new ArrayList<MemberVO>();
			
			while(rs.next()) { 
				System.out.println("wdw");
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
			request.setAttribute("mvo", mvo);	
			RequestDispatcher view = request.getRequestDispatcher("/member/getMemberList.jsp");	
			view.forward(request, response);	
		} catch(Exception e) {
			e.getStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
	}

}
