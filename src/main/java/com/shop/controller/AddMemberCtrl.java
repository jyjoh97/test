package com.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.common.MemberVO;
import com.shop.model.MemberDAO;


@WebServlet("/AddMemberCtrl")
public class AddMemberCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddMemberCtrl() {
        super();
      
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mname = request.getParameter("mname");
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String birth = request.getParameter("birth");
		
		MemberVO vo = new MemberVO();
		vo.setMname(mname);
		vo.setMid(mid);
		vo.setMpw(mpw);
		vo.setTel(tel);
		vo.setAddress(address);
		vo.setBirth(birth);
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.addMember(vo);
		if(cnt>0) {  
			response.sendRedirect("GetMemberListCtrl");
		} else {  
			response.sendRedirect("./member/addMemberForm.jsp");
		}	
	}


	}

