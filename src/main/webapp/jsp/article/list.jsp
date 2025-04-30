<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="dto.Article" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Article> articles = (List<Article>) request.getAttribute("articles");
int cPage = (int) request.getAttribute("page");
int totalCnt = (int) request.getAttribute("totalCnt");
int totalPage = (int) request.getAttribute("totalPage");
String username = (String) request.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<style>
table>thead>tr>th, table>tbody>tr>td {
	padding: 5px;
}
ul,
li {
  list-style: none;
  margin: 0;
  padding: 0;
}
body {
	  margin: 0;
  padding: 0;
  min-height: 100vh;
  min-width: 100vw;

}
a{
  color: inherit;
  text-decoration: inherit;
}
table,h2{
width: 80vw;
margin: 0 auto;
margin-top: 14px;
}

h2{
display:block;
padding:20px;
}
.body{
width: 40%;
}
.title{
width: 30%;

}
.button{
margin: 0 auto;
width: 80vw;
display: flex;
justify-content: flex-start;
}

.button>a{
border: 2px solid black;
padding: 10px;
margin-right: 20px;
}

.button>a:hover{
border: 2px solid #fa6e8c;
color: #fa6e8c;c
}

body>a:hover{
border: 2px solid #fa6e8c;
color: #fa6e8c;
}

tbody>tr>td>a:hover{
color: #fa6e8c;
}

.page {
margin: 0 auto;
	font-size: 1.4rem;
	width: 30vw;
display: flex;
justify-content: center;
}

.page>a {
	color: black;
	text-decoration: none;
	flex-grow:1;
	width: 10%;
}

.page>a.cPage {
color: #fa6e8c;
}

.articleNumber{
margin: 0 auto;
width: 80vw;
display: flex;
justify-content: flex-start;
margin-top: 20px;
}

.active{
display: none;
}

.btn2 > ul{
width: 15vw;
display: flex;
justify-content: space-between;
}

.btn2{
position: absolute;
top: 100px;
right: 10%;
}

.btn2>ul>li>a{
border: 2px solid black;
}

.btn2>ul>li>a:hover{
border: 2px solid #fa6e8c;
color: #fa6e8c;
}
</style>

</head>

   
<body>
	<h2>게시글 목록</h2>
	<div class = "btn2">
		<ul>
			<%if(username != null){%>
	<li><%=username %>님 환영합니다.</li>
<%} %>
		<li><a class ="<%= username == null ? "":"active"%>" href="../member/join2">회원가입</a></li>
		<li><a class ="<%= username == null ? "":"active"%>" href="../member/login">로그인</a></li>
		<li><a class ="<%= username == null ? "active":""%>" href="../member/logout">로그아웃</a></li>
		</ul>
	</div>
<div class="button">
	<a href="../home/main">메인으로 이동</a>
	<a class ="<%= username == null ? "active":""%>" href="../article/write2">글쓰기</a>
</div>
	<div class = "articleNumber">
		총 게시글 갯수 :
		<%=totalCnt%>개
	</div>
	
	<table style="border-collapse: collapse; border: 5px solid black;"
		border="1px">
		<thead>
			<tr>
				<th class = "id">번호</th>
				<th class = "date">날짜</th>
				<th class = "title">제목</th>
				<th class = "body">내용</th>
				<th class = "writer">작성자</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Article article : articles) {
			%>
			<tr style="text-align: center;">
				<td><%=article.getId()%>번</td>
				<td><%=article.getRegDate()%></td>
				<td><a href="detail?id=<%=article.getId()%>"><%=article.getTitle()%></a></td>
				<td><%=article.getBody()%></td>
				<td><%=article.getWriter()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<div class="page">
	<%
	if(totalPage<=10){
		for(int i = 1; i <= totalPage; i++){ %>
			<a class="<%=cPage == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=i%></a>
	<%
			}
		}
	else{  
	if(cPage>10){%>
		<a href="list?page=<%=(cPage/10-1)*10+1%>">이전</a>
		<%
	}
		if(cPage<=10){
		for(int i = 1; i <= 10; i++){%>
		<a class="<%=cPage == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=i%></a>
		<%
		}
		}
		else if(cPage > 10){
			if(cPage%10 == 1){
				if(totalPage > cPage+10 ){
			for(int i = cPage; i < cPage+10; i++){%>
				<a class="<%=cPage == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=i%></a>
			<%
			}
			}
				else{
					for(int i = cPage; i <= totalPage; i++){
				%>
					<a class="<%=cPage == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=i%></a>
				<%
					}
					}
			}
			else{
				if(totalPage > (cPage/10)*10+11){
				for(int i = (cPage/10)*10+1; i < (cPage/10)*10+11; i++){%>
					<a class="<%=cPage == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=i%></a>
				<%}
				}
				else{
					for(int i = (cPage/10)*10+1; i <= totalPage; i++){%>
					<a class="<%=cPage == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=i%></a>
				<%}
				}
			}
		}
		if(cPage/10+1<totalPage/10+1){%>
		<a href="list?page=<%=(cPage/10+1)*10+1%>">이후</a>
		<%}
		
		else if(cPage%10==0){%>
			<a href="list?page=<%=(cPage/10)*10+1%>">이후</a>
		<%}
	}
		%>
	</div>

</body>
</html>