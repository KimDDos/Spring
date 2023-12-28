<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
	<h2> Board List Page </h2>
	<br>
	
	<!-- 검색라인 -->
	<div>
		<form action="/board/list">
			<div class="input-group mb-3">
				<select class="form-select" name="type">
					<c:set value="${ph.pgvo.type }" var="typed"></c:set>
					<option ${typed == null ? 'selected' : '' }> Choose </option>
					<option value="t" ${typed == 't' ? 'selected' : '' }> Title </option>
					<option value="w" ${typed == 'w' ? 'selected' : '' }> Writer </option>
					<option value="c" ${typed == 'c' ? 'selected' : '' }> Content </option>
					<option value="tc" ${typed == 'tc' ? 'selected' : '' }> Title & Content </option>
					<option value="tw" ${typed == 'tw' ? 'selected' : '' }> Title & Writer </option>
					<option value="wc" ${typed == 'wc' ? 'selected' : '' }> Writer & Content </option>
					<option value="twc" ${typed == 'twc' ? 'selected' : '' }> All </option>
				</select>
				<input class="form-control me-2" type="text" name="keyword" value="${ph.pgvo.keyword}" placeholder="Search...">
				<input type="hidden" name="pageNo" value="${ph.pgvo.pageNo }" placeholder="1">
				<input type="hidden" name="qty" value="${ph.pgvo.qty }" placeholder="10">
				<button type="submit" class="btn btn-outline-success">
					Search<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">${totalCount }</span>
				</button>
			</div>
		</form>
	</div>
	
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">Title</th>
	      <th scope="col">writer</th>
	      <th scope="col">Regdate</th>
	      <th scope="col">Comment Count</th>
	      <th scope="col">File Count</th>
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
	      <td>${bvo.commentCount }</td>
	      <td>${bvo.filedCount }</td>
	      <td>${bvo.read_count }</td>
	    </tr>
	  </c:forEach>
	  </tbody>
	</table>
	<div class="paging-bar-container" style="display: flex; justify-content: center;">
		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		  	<!-- prev -->
		  	<c:if test="${ph.prev}">
			    <li class="page-item">
			      <a class="page-link" href="/board/list?pageNo=${ph.startPage - 1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
		    </c:if>
		    <!-- paging No -->
		    <c:forEach begin="${ph.startPage }" end="${ph.endPage}" var="i">
			    <li class="page-item"><a class="page-link" href="/board/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a></li>
		    </c:forEach>
		    <!-- Next -->
		    <c:if test="${ph.next}">
			    <li class="page-item">
			      <a class="page-link" href="/board/list?pageNo=${ph.endPage + 1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
		    </c:if>
		    <li class="page-item">
		      <a class="page-link" href="/board/list">
		        <span aria-hidden="true">전체보기</span>
		      </a>
		    </li>
		  </ul>
		</nav>
		
    </div>
	
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