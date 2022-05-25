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
<title>회원 목록</title>
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
		<h2>회원 목록</h2>
		<form method="post" action="../GetMemberSearchCtrl">
			<table class="table" id="search_tb">
				<tr>
					<td>
						<select name="searchCondition">
							<option value="mname">이름</option>
							<option value="mid">아이디</option>
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
					<th class="item1">번호</th>
					<th class="item2">이름</th>
					<th class="item3">아이디</th>
					<th class="item4">비밀번호</th>
					<th class="item5">연락처</th>
					<th class="item6">주소</th>
					<th class="item7">생일</th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${mvo }" var="vo" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>
						<a href="../GetMemberListCtrl?num=${vo.mno }">${vo.mname }</a>
					</td>
					<td>${vo.mid }</td>
					<td>${vo.mpw }</td>
					<td>${vo.tel }</td>
					<td>${vo.address }</td>
					<td>${vo.birth }</td>
				</tr>
			</c:forEach>	
				<tr>
					<td colspan="4"><a href="addMemberForm.jsp"></a></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>