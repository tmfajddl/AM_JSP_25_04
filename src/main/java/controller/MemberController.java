package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ArticleService;
import service.MemberService;

public class MemberController {
	public static String username = null;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;
	private MemberService memberservice;

	public MemberController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.conn = conn;
		this.request = request;
		this.response = response;
		this.memberservice = new MemberService(conn);
	}

	public void dojoin() throws IOException {
		String loginid = request.getParameter("loginid");
		String loginpw = request.getParameter("loginpw");
		String loginpw2 = request.getParameter("loginpw2");
		String name = request.getParameter("name");
		
		if(loginpw.equals(loginpw2)) {
			
			Map<String, Object> articleMap = memberservice.findId(loginid);
            if (articleMap.isEmpty()) {

				int id = memberservice.doJoin(loginid, loginpw2, name);

				response.getWriter()
						.append(String.format("<script>alert('%d번 회원이 등록됨'); location.replace('../home/main');</script>", id));
            }
					response.getWriter()
					.append(String.format("<script>alert('아이디가 중복되었습니다.'); location.replace('../member/join2');</script>"));
			}
			
		else {
			response.getWriter()
			.append(String.format("<script>alert('비밀번호가 일치하지 않습니다.'); location.replace('../member/join2');</script>"));
		}
		
	}

	public void dojoin2() throws ServletException, IOException {
		if(MemberController.username == null) {
			request.getRequestDispatcher("/jsp/member/join.jsp").forward(request, response);
		}
		else {
			response.getWriter()
			.append(String.format("<script>alert('로그인 되어있습니다.'); location.replace('../home/main');</script>"));
		}
	}

	public void dologin() throws ServletException, IOException {
		if(MemberController.username != null) {
        	response.getWriter()
			.append(String.format("<script>alert('로그인 되어있습니다.'); location.replace('../home/main');</script>"));
        }
		
        else{request.getRequestDispatcher("/jsp/member/login.jsp").forward(request, response);
        }
		
	}

	public void dologin2() throws IOException {
		String loginid = request.getParameter("loginid");
		String loginpw = request.getParameter("loginpw");;
			
		Map<String, Object> memberRow = memberservice.findMember(loginid, loginpw);
		
        if (memberRow.isEmpty()) {

			memberRow = memberservice.findId(loginid);
			if(memberRow.isEmpty()) {
				response.getWriter()
				.append(String.format("<script>alert('아이디가 존재하지 않습니다.'); location.replace('http://localhost:8080/AM_JSP_25_04/member/login');</script>"));
			}
			else if(!memberRow.isEmpty()){
				response.getWriter()
				.append(String.format("<script>alert('비밀번호가 틀립니다.'); location.replace('http://localhost:8080/AM_JSP_25_04/member/login');</script>"));
			}
          }
        else {
        	
			HttpSession session = request.getSession();
			session.setAttribute("loginedMember", memberRow);
			session.setAttribute("loginedMemberId", memberRow.get("id"));
			session.setAttribute("loginedMemberLoginId", memberRow.get("loginId"));
			
        	response.getWriter()
			.append(String.format("<script>alert('%s 회원님 로그인 되었습니다.'); location.replace('http://localhost:8080/AM_JSP_25_04/home/main');</script>",loginid));
			
        	username = loginid;
        }
     
	}

	public void dologout() throws IOException {
		if(MemberController.username == null) {
        	response.getWriter()
			.append(String.format("<script>alert('로그인 되어있지 않습니다.'); location.replace('http://localhost:8080/AM_JSP_25_04/home/main');</script>"));
        }
        else{
        	MemberController.username = null;
        	
			HttpSession session = request.getSession();
			session.removeAttribute("loginedMember");
			session.removeAttribute("loginedMemberId");
			session.removeAttribute("loginedMemberLoginId");

			response.getWriter()
					.append(String.format("<script>alert('로그아웃 되었습니다.'); location.replace('../home/main');</script>"));
	}

}
}
