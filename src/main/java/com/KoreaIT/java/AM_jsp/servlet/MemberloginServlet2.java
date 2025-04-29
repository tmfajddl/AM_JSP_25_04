package com.KoreaIT.java.AM_jsp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

@WebServlet("/member/login2")
public class MemberloginServlet2 extends HttpServlet {
	
	public static String username = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		// DB 연결
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 x");
			e.printStackTrace();

		}

		String url = "jdbc:mysql://127.0.0.1:3306/AM_JSP_25_04?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			response.getWriter().append("연결 성공!");

			String loginid = request.getParameter("loginid");
			String loginpw = request.getParameter("loginpw");
			
			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM `member`");
			sql.append("WHERE loginid = ? AND", loginid);
			sql.append("loginpw = ?;", loginpw);
				
			Map<String, Object> memberMap = DBUtil.selectRow(conn, sql);
			
            if (memberMap.isEmpty()) {
            	sql = SecSql.from("SELECT *");
    			sql.append("FROM `member`");
    			sql.append("WHERE loginid = ?;", loginid);

    			memberMap = DBUtil.selectRow(conn, sql);
    			if(memberMap.isEmpty()) {
    				response.getWriter()
					.append(String.format("<script>alert('아이디가 존재하지 않습니다.'); location.replace('http://localhost:8080/AM_JSP_25_04/member/login');</script>"));
    			}
    			else if(!memberMap.isEmpty()){
					response.getWriter()
					.append(String.format("<script>alert('비밀번호가 틀립니다.'); location.replace('http://localhost:8080/AM_JSP_25_04/member/login');</script>"));
    			}
              }
            else {
            	response.getWriter()
				.append(String.format("<script>alert('%s 회원님 로그인 되었습니다.'); location.replace('http://localhost:8080/AM_JSP_25_04/home/main');</script>",loginid));
				
				username = loginid;
            }
         

		} catch (SQLException e) {
			System.out.println("에러 1 : " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
