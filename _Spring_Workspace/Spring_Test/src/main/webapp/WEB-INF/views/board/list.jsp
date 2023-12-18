<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">Title</th>
	      <th scope="col">writer</th>
	      <th scope="col">Regdate</th>
	      <th scope="col">ReadCount</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${list }" var="bvo">
	    <tr>
	      <th scope="row">${bvo.bno }</th>
	      <td><a href="/board/detail?bno=${bvo.bno}">${bvo.title }</a></td>
	      <td>${bvo.writer }</td>
	      <td>${bvo.regdate }</td>
	      <td>${bvo.read_count }</td>
	    </tr>
	  </c:forEach>
	  </tbody>
	</table>
</div>
<<script type="text/javascript">
	const isDel = `<c:out value="${isDel}" />`;
	if(isDel == '1'){
		alert("게시글이 삭제되었습니다.");
	} else if(isDel == '0') {
		alert("게시글 삭제 실패~!");
	}
</script>
<jsp:include page="../layout/footer.jsp"></jsp:include>