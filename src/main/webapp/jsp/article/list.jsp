<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
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
		<li><a class ="<%= username == null ? "":"active"%>" href="http://localhost:8080/AM_JSP_25_04/member/join2">회원가입</a></li>
		<li><a class ="<%= username == null ? "":"active"%>" href="http://localhost:8080/AM_JSP_25_04/member/login">로그인</a></li>
		<li><a class ="<%= username == null ? "active":""%>" href="http://localhost:8080/AM_JSP_25_04/member/logout">로그아웃</a></li>
		</ul>
	</div>
<div class="button">
	<a href="../home/main">메인으로 이동</a>
	<a class ="<%= username == null ? "active":""%>" href="http://localhost:8080/AM_JSP_25_04/home/write2">글쓰기</a>
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
			for (Map<String, Object> articleRow : articleRows) {
			%>
			<tr style="text-align: center;">
				<td><%=articleRow.get("id")%>번</td>
				<td><%=articleRow.get("regDate")%></td>
				<td><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title")%></a></td>
				<td><%=articleRow.get("body")%></td>
				<td><%=articleRow.get("writer")%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<div class="page">
		<%
			if(totalPage>10){
				if(cPage<5){
					for(int i = 1; i <= 10; i++){
						%>
						&nbsp
						<a class="<%=cPage == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=i%></a>
						&nbsp
						<%
					}	
				}
			else if(cPage>totalPage-4){
					for(int i = totalPage-9; i <= totalPage; i++){
						%>
						&nbsp
						<a class="<%=cPage == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=i%></a>
						&nbsp
						<%
					}	
				}
				else{
				for(int i = cPage-4; i <= cPage+5; i++){
					%>
					&nbsp
					<a class="<%=cPage == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=i%></a>
					&nbsp
					<%
				}
				}
			}
			else{
				for (int i = 1; i <= totalPage; i++) {
				%>
				&nbsp
				<a class="<%=cPage == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=i%></a>
				&nbsp
				<%
			}
		}
		%>

	</div>

</body>
</html>