package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import dao.ArticleDao;
import dto.Article;
import dto.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ArticleService;

public class ArticleController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;
	private ArticleService articleservice;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.conn = conn;
		this.request = request;
		this.response = response;
		this.articleservice = new ArticleService(conn);
	}

	public void showList() throws ServletException, IOException {
		int page = 1;

		if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int itemsInAPage = 10;
		int limitFrom = (page - 1) * itemsInAPage;

		int totalCnt = articleservice.listCount(limitFrom, itemsInAPage);
		int totalPage = (int) Math.ceil(totalCnt / (double)itemsInAPage);

		List<Article> articles = articleservice.showlist(limitFrom, itemsInAPage);

		String username = MemberController.username;
		
		request.setAttribute("username", username);
		request.setAttribute("page", page);
		request.setAttribute("articles", articles);
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("totalPage", totalPage);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
		
	}

	public void doDelete() throws IOException {
		if(MemberController.username==null) {
			response.getWriter()
			.append(String.format("<script>alert('권한이 없습니다.'); location.replace('../article/detail');</script>"));
		}
		else {
			int id = Integer.parseInt(request.getParameter("id"));

			articleservice.doDelete(id);

			response.getWriter()
					.append(String.format("<script>alert('%d번 글이 삭제됨'); location.replace('list');</script>", id));
		}
		
	}

	public void showDetail() throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Map<String, Object> articleRow = articleservice.showDetail(id);
		
		Article article = new Article(articleRow);

		request.setAttribute("id", article.getId());
		request.setAttribute("regDate", article.getRegDate());
		request.setAttribute("updateDate", article.getUpdateDate());
		request.setAttribute("title", article.getTitle());
		request.setAttribute("body", article.getBody());
		request.setAttribute("writer", article.getWriter());
		
        String username = MemberController.username;
		
		request.setAttribute("username", username);

		request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);
		
	}

	public void dowrite() throws IOException, ServletException {
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		String writer = MemberController.username;

				int id = articleservice.doWrite(title, body, writer);

				response.getWriter()
						.append(String.format("<script>alert('%d번 글이 등록됨'); location.replace('list');</script>", id));
	}

	public void dowrite2() throws IOException, ServletException {
		if(MemberController.username==null) {
			response.getWriter()
			.append(String.format("<script>alert('로그인 후 이용바랍니다.'); location.replace('../member/login');</script>"));
		}
		else {
			request.getRequestDispatcher("/jsp/article/write.jsp").forward(request, response);
		}
		
	}

	public void doModify() throws IOException, ServletException {
		String inputId = request.getParameter("id");
		int id = Integer.parseInt(inputId);

		Map<String, Object> articleRow = articleservice.showDetail(id);
		Article article = new Article(articleRow);

		request.setAttribute("id", article.getId());
		request.setAttribute("title", article.getTitle());
		request.setAttribute("body", article.getBody());
		
		if(MemberController.username==null) {
			response.getWriter()
			.append(String.format("<script>alert('권한이 없습니다.'); location.replace('../article/detail');</script>"));
		}
		else {
			request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);
		}
		
	}

	public void doModify2() throws IOException {
		String inputId = request.getParameter("id");

		int id = Integer.parseInt(inputId);

		Map<String, Object> articleRow = articleservice.showDetail(id);
		
	
	    String title = request.getParameter("title");
		String body = request.getParameter("body");
		articleservice.doUpdate(title, body, id);

	response.getWriter()
			.append(String.format("<script>alert('%d번 글이 수정됨'); location.replace('list');</script>", id));
		
	}

}
