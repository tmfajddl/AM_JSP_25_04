package controller;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;

	public HomeController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.conn = conn;
		this.request = request;
		this.response = response;
	}

	public void showhome() throws ServletException, IOException {
		String username = MemberController.username;
		
		request.setAttribute("username", username);

		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
		
	}

}
