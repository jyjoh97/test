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

import com.shop.common.BoardVO;
import com.shop.common.JDBCConnection;


@WebServlet("/GetBoardListCtrl")
public class GetBoardListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetBoardListCtrl() {
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
			sql = "select * from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList<BoardVO> bvo = new ArrayList<BoardVO>();
			
			while(rs.next()) { 
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setBtitle(rs.getString("btitle"));
				vo.setBcontent(rs.getString("bcontent"));
				vo.setBwriter(rs.getString("bwriter"));
				vo.setBdate(rs.getDate("bdate"));
				bvo.add(vo);
			}			
			request.setAttribute("bvo", bvo);	//요청 저장소에 담기
			RequestDispatcher view = request.getRequestDispatcher("/board/getBoardList.jsp");	//보내질 곳 지정
			view.forward(request, response);	//지정된 곳에 저장된 요청데이터를 전송하기
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
