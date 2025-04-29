package com.KoreaIT.java.AM_jsp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home/write2")
public class ArticleWriteServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(MemberloginServlet2.username==null) {
			response.getWriter()
			.append(String.format("<script>alert('로그인 후 이용바랍니다.'); location.replace('http://localhost:8080/AM_JSP_25_04/member/login');</script>"));
		}
		else {
			request.getRequestDispatcher("/jsp/article/write.jsp").forward(request, response);
		}
	}

}
