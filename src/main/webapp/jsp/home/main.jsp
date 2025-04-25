<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
body {
	  margin: 0;
  padding: 0;
  display:flex;
justify-content: flex-start;
align-items: center;
flex-direction: column;
}
a{
  color: inherit;
  text-decoration: inherit;
}
.title{
wigth:100vw;
font-size: 3rem;
}

ul,
li {
  list-style: none;
  margin: 0;
  padding: 0;
}
ul>li:hover>a{
color: #fa6e8c;
}

</style>
</head>
<body>
	<h1 class="title">메인 페이지</h1>

	<ul>
		<li><a href="../article/list">리스트로 이동</a></li>
	</ul>

</body>
</html>