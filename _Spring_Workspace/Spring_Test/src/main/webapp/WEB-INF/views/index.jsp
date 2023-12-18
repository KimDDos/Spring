<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="./layout/header.jsp"></jsp:include>



<h1>
	Hello my First Spring Study!  
</h1>
<c:if test="${ses.id ne null }">
	<p> ${ses.id }님 안녕하세요~! <span class="badge text-bg-primary">최근 로그인 : ${ses.last_login }</span></p>
	
</c:if>

<script type="text/javascript">
	const msg_login = `<c:out value="${msg_login}"/>`;
	const msg_logout = `<c:out value="${msg_logout}"/>`;
	const msg_memberMod = `<c:out value="${msg_memberMod}"/>`;
	if(msg_login === "1"){
		alert("로그인 실패~!!!");
	}
	
	if(msg_logout === "1"){
		alert("로그아웃 되었습니다.");
	}
	
	if(msg_memberMod ==="1"){
		alert("회원정보가 수정되어 로그아웃 되었습니다.");
	} else if(msg_memberMod ==="2"){
		alert("회원정보가 수정 실패!");
	}
	
</script>

<jsp:include page="./layout/footer.jsp"></jsp:include>
