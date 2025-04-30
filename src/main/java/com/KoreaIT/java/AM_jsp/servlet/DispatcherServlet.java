package com.KoreaIT.java.AM_jsp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import controller.ArticleController;
import controller.HomeController;
import controller.MemberController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {

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

			HttpSession session = request.getSession();

			boolean isLogined = false;
			int loginedMemberId = -1;
			Map<String, Object> loginedMember = null;

			if (session.getAttribute("loginedMemberId") != null) {
				isLogined = true;
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
				loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");
			}

			request.setAttribute("isLogined", isLogined);
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("loginedMember", loginedMember);

			String requestUri = request.getRequestURI();

			System.out.println(requestUri);

			String[] reqUriBits = requestUri.split("/");
			// /~~~/s/article/list

			if (reqUriBits.length < 5) {
				response.getWriter()
						.append(String.format("<script>alert('올바른 요청 x'); location.replace('../home/main');</script>"));
				return;
			}

			String controllerName = reqUriBits[3];
			String actionMethodName = reqUriBits[4];

			if (controllerName.equals("article")) {
				ArticleController articleController = new ArticleController(request, response, conn);

				if (actionMethodName.equals("list")) {
					articleController.showList();
				}
				else if (actionMethodName.equals("delete")) {
					articleController.doDelete();
				}
				else if (actionMethodName.equals("detail")) {
					articleController.showDetail();
				}
				else if (actionMethodName.equals("write")) {
					articleController.dowrite();
				}
				else if (actionMethodName.equals("write2")) {
					articleController.dowrite2();
				}
				else if (actionMethodName.equals("modify2")) {
					articleController.doModify2();
				}
				else if (actionMethodName.equals("modify")) {
					articleController.doModify();
				}
				else if (actionMethodName.equals("search")) {
					articleController.doSearch();
				}
			}
			else if(controllerName.equals("member")) {
				MemberController memberController = new MemberController(request, response, conn);
				if (actionMethodName.equals("join")) {
					memberController.dojoin();
				}
				else if (actionMethodName.equals("join2")) {
					memberController.dojoin2();
				}
				else if (actionMethodName.equals("login")) {
					memberController.dologin();
				}
				else if (actionMethodName.equals("login2")) {
					memberController.dologin2();
				}
				else if (actionMethodName.equals("logout")) {
					memberController.dologout();
				}
			}
			else if(controllerName.equals("home")){
				HomeController homeController = new HomeController(request, response, conn);
				if (actionMethodName.equals("main")) {
					homeController.showhome();
				}
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
