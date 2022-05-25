<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.shop.common.*" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-latest.js"></script>
<title>게시글 목록</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css" />
	<style>
		#lst_tb { width:700px; margin:0 auto; }
	</style>
	
</head>


<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div id="content">
		<h2>글 상세보기</h2>
		<form action="EditBoardCtrl" method="post"></form>
		<table class="table">
			<tbody>
				<tr>
					<th>제목</th>
					<td><input type="text" name="btitle" value="${vo.title }"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="bwriter" value="${vo.bwwriter }"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea cols="100" rows="7" name="bcontent">${vo.bcontent }</textarea>></td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${vo.bdate }</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정" class="button is-info"/>
						<input type="reset" value="취소" class="button is-info"/>
						<a href="DelBoardCtrl" class="button is-info">삭제</a>
					</td>
				</tr>		
			</tbody>
		</table>
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>