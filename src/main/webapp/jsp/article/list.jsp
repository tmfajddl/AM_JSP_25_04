<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");

int cPage = (int) request.getAttribute("page");
int totalCnt = (int) request.getAttribute("totalCnt");
int totalPage = (int) request.getAttribute("totalPage");
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
tbody>tr>td>a{
border: 2px solid black;
}

tbody>tr>td>a:hover{
border: 2px solid #fa6e8c;
color: #fa6e8c;
}

.page {
	font-size: 1.4rem;
	width: 100vw;
display: flex;
justify-content: center;
}

.page>a {
	color: black;
	text-decoration: none;
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
</style>
</head>
<body>
	<h2>게시글 목록</h2>
<div class="button">
	<a href="../home/main">메인으로 이동</a>
	<a href="/AM_JSP_25_04/jsp/article/write.jsp">글쓰기</a>
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
				<th>삭제</th>
				<th>수정</th>
				<th>상세보기</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Map<String, Object> articleRow : articleRows) {
			%>
			<tr style="text-align: center;">
				<td><%=articleRow.get("id")%>번</td>
				<td><%=articleRow.get("regDate")%></td>
				<td><%=articleRow.get("title")%></td>
				<td><%=articleRow.get("body")%></td>
				<td><a
					onclick="if ( confirm('정말 삭제하시겠습니까?') == false ) { return false; }"
					href="doDelete?id=<%=articleRow.get("id")%>">삭제</a></td>
					<td><a href="doModify?id=<%=articleRow.get("id")%>">수정</a></td>
					<td><a href="detail?id=<%=articleRow.get("id")%>">보기</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<div class="page">
		<%
		for (int i = 1; i <= totalPage; i++) {
			if(totalPage>10){
				for(int j = 1; j <= 10; j++){
					%>
					<a class="<%=cPage == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=i%></a>
					<%
				}
			}
			else{
				%>
				<a class="<%=cPage == i ? "cPage" : "" %>" href="list?page=<%=i%>"><%=i%></a>
				<%
			}
		}
		%>

	</div>

</body>
</html>