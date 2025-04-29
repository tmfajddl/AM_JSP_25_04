<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%
String username = (String) request.getAttribute("username");
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=articleRow.get("id") %>번 게시글 상세페이지</title>

<style>
body {
	  margin: 0;
  padding: 0;
  font-size: 1rem;
  font-weight: bold;
}
a{
  color: inherit;
  text-decoration: inherit;
}
.title{
font-size: 3rem;
}

div>a{
position: block;
border: 2px solid black;
padding: 10px;
margin-left: 20px;
}

div>a:hover{
border: 2px solid #fa6e8c;
color: #fa6e8c;
}

h2{
width: 80vw;
margin: 0 auto;
margin-top: 20px;
display: flex;
flex-direction: column;
align-items: center;
}

section{
width: 30vw;
margin: 0 auto;
margin-top: 20px;
display: flex;
flex-direction: column;
}

div{
padding: 10px 0;
}

.button{
display: flex;
justify-content: center;
}

.active{
display: none;
}

</style>
</head>
<body>

	<h2 class="title"><%=articleRow.get("id") %>번 게시글 상세페이지</h2>
	<section>
		<div>
		번호 :
		<%=articleRow.get("id")%></div>
	<div>
		날짜 :
		<%=articleRow.get("regDate")%></div>
	<div>
		업데이트 날짜 :
		<%=articleRow.get("updateDate")%></div>
	<div>
		제목 :
		<%=articleRow.get("title")%></div>
	<div>
		내용 :
		<%=articleRow.get("body")%></div>
	<div>
		작성자 :
		<%=articleRow.get("writer")%></div>
	</section>


	<div class="button">
	<a class ="<%= username.equals(articleRow.get("writer")) ? "":"active"%>" href="doModify?id=<%=articleRow.get("id")%>">수정</a>
	<a class ="<%= username.equals(articleRow.get("writer")) ? "":"active"%>"
					onclick="if ( confirm('정말 삭제하시겠습니까?') == false ) { return false; }"
					href="doDelete?id=<%=articleRow.get("id")%>">삭제</a>
		<a href="list">리스트로 돌아가기</a>
		<a href="../home/main">메인으로 이동</a>
	</div>
</body>
</html>