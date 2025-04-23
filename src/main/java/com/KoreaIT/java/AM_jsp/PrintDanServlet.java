package com.KoreaIT.java.AM_jsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/printDan")
public class PrintDanServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		
		String inputdan = request.getParameter("dan");
		String inputlimit = request.getParameter("limit");
		String inputcolor = request.getParameter("color");
		
		
		if(inputdan==null) {
			inputdan = "1";
		}
		
		if(inputlimit==null) {
			inputlimit ="1";
		}
		
		int dan = Integer.parseInt(inputdan);
		int limit = Integer.parseInt(inputlimit);

		response.getWriter().append(String.format("<div style='color:%s; background-color:pink;'>==%d단==</div>",inputcolor,dan));

		for (int i = 1; i <= limit; i++) {
			response.getWriter().append(String.format("<div style='color:%s; background-color:pink;'>%d * %d = %d</div>",inputcolor, dan, i, dan * i));
		}
		
	}

}

