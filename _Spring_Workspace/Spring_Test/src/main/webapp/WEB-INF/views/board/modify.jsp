<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">

	<form action="/board/modify" method="post">
	<div class="mb-3">
	  <label for="title" class="form-label">Title</label>
	  <input type="text" class="form-control" id="title" name="title" value="${bvo.title}">
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
	  <textarea class="form-control" id="content" name="content" rows="4">${bvo.content}</textarea>
	</div>
	<button type="button" class="btn btn-success">Success</button>
	<a href="/board/modify"><button type="button" class="btn btn-success">Modify</button></a>
	</form>
</div>

<hr>
<jsp:include page="../layout/footer.jsp"></jsp:include>