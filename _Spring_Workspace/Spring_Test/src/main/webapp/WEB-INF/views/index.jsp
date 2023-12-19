<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="./layout/header.jsp"></jsp:include>



<h1>
	Hello my First Spring Study!  
</h1>
<c:if test="${ses.id ne null }">
	<p> ${ses.id}(${ses.name})님 안녕하세요~! <span class="badge text-bg-primary">최근 로그인 : ${ses.last_login }</span></p>
	
</c:if>

<script type="text/javascript">
	const msg_login = `<c:out value="${msg_login}"/>`;
	const msg_logout = `<c:out value="${msg_logout}"/>`;
	const msg_modify = `<c:out value="${msg_modify}"/>`;
	const msg_remove = `<c:out value="${msg_remove}"/>`;
	if(msg_login === "1"){
		alert("로그인 실패~!!!");
	}
	
	if(msg_logout === "1"){
		alert("로그아웃 되었습니다.");
	}
	
	if(msg_modify ==="1"){
		alert("회원정보가 수정되었습니다. 다시 로그인 해주세요.");
	}
	
	if(msg_remove ==="1"){
		alert("회원탈퇴 성공! 안녕히 가세요~!");
	}
	
</script>

<jsp:include page="./layout/footer.jsp"></jsp:include>
