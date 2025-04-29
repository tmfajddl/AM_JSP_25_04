<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
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

h2{
width: 80vw;
margin: 0 auto;
margin-top: 20px;
display: flex;
flex-direction: column;
align-items: center;
}

form{
width: 20vw;
margin: 0 auto;
margin-top: 20px;
display: flex;
flex-direction: column;
}

input,textarea{
  display: block;
  width: 200px;
  height: 30px;
  border: none;
background-color: rgba(168, 172, 173,0.3);
  border-radius: 10px;s
}
div{
padding: 10px 0;
}
button:hover{
border: 2px solid #fa6e8c;
color: #fa6e8c;
}

</style>
</head>
<body>
<h2 class = "title">게시글 작성</h2>

<form action="../article/doWrite" method="post">

<div>제목:
<input type="text" name="title" required/></div>
<div>내용: 
<textarea name="body" required></textarea></div>
<br>
<div><button type="submit">확인</button></div>

</form>
</body>
</html>