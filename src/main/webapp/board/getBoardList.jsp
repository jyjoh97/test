<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.shop.model.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css" />
	<style>
		#lst_tb { width:700px; margin:0 auto; }
	</style>
	
</head>


<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<%-- <c:if test="${empty name }"><c:redirect url="./member/login.jsp" /></c:if> --%>
	
	<div id="content" class="panel-body">
		<div>
			<%-- <h3>${name }님 &nbsp; &nbsp;<a href="../LogoutCtrl">로그아웃</a></h3> --%>
		</div>
		<h2>게시글 목록</h2>
		<form method="post" action="../GetBoardSearchCtrl">
			<table class="table" id="search_tb">
				<tr>
					<td>
						<select name="searchCondition">
							<option value="btitle">제목</option>
							<option value="writer">작성자</option>
						</select>
						<input type="text" name="searchKeyword" />
						<input type="submit" value="검색" />
					</td>
				</tr>
			</table>
		</form>
		<table class="table" id=lst_tb>
			<thead>
				<tr>
					<th class="item1">글 번호</th>
					<th class="item2">글 제목</th>
					<th class="item3">글 내용</th>
					<th class="item4">작성자</th>
					<th class="item5">작성일</th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${bvo }" var="vo" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>
						<a href="../GetBoardListCtrl?num=${vo.bno }">${vo.btitle }</a>
					</td>
					<td>${vo.bcontent }</td>
					<td>${vo.bwriter }</td>
					<td>${vo.bdate }</td>
				</tr>
			</c:forEach>	
				<tr>
					<td colspan="4"><a href="addBoardForm.jsp"></a></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>