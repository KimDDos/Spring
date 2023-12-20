<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
	
	<div class="mb-3">
	  <input type="hidden" class="form-control" id="bno" name="bno" value="${bvo.bno}">
	  <label for="title" class="form-label">Title</label>
	  <input type="text" class="form-control" id="title" name="title" value="${bvo.title}" readonly="readonly">
	</div>
	<div class="mb-3">
	  <label for="writer" class="form-label">Writer</label>
	  <input type="text" class="form-control" id="writer" name="writer" value="${bvo.writer}" readonly="readonly">
	</div>
	<div class="mb-3">
	  <label for="regdate" class="form-label">Regdate</label>
		<span class="badge bg-secondary">Read Count ${bvo.read_count}</span>
	  <input type="text" class="form-control" id="regdate" name="regdate" value="${bvo.regdate}" readonly="readonly">
	</div>
	<div class="mb-3">
	  <label for="content" class="form-label">Content</label>
	  <textarea class="form-control" id="content" name="content" rows="4" readonly="readonly">${bvo.content}</textarea>
	</div>
	<a href="/board/modify?bno=${bvo.bno }"><button type="button" class="btn btn-success">Modify</button></a>
	
	<br>
	<hr>
	<br>
	<!-- 댓글 등록 라인 -->
	<h2>댓글 등록</h2> <br>
	<div class="input-group mb-3">
		<span class="input-group-text" id="cmtWriter"> ${ses.id } </span>
		<input type="text" id="cmtText" class="form-control" aria-label="Amount (to the nearest dollar)" placeholder="Add Comment...">
		<button class="btn btn-outline-secondary input-group-text" id="cmtAddBtn" type="button">Comment <br>Registration</button>
	</div>
		
		
	<br>
	<hr>
	<br>
	
	<!-- 댓글 표시 라인 -->
	<div class="accordion" id="accordionExample">
	  <div class="accordion-item">
	    <h2 class="accordion-header">
	      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
	        cno, writer, reg_date
	      </button>
	    </h2>
	    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
	      <div class="accordion-body">
	        <strong> Add Comment.... </strong>
	      </div>
	      <div class="btn-group" role="group" aria-label="Default button group">
	  		<a href="#?cno=${1 }"><button type="button" class="btn btn-outline-primary">Modify</button></a>
	  		<a href="#?cno=${1 }"><button type="button" class="btn btn-outline-danger">Remove</button></a>
	      </div>
	    </div>
	  </div>
	  <div>
	  </div>
	</div>
	
</div>

<script type="text/javascript">
	const bnoVal = `<c:out value="${bvo.bno}"/>`;
</script>

<jsp:include page="../layout/footer.jsp"></jsp:include>