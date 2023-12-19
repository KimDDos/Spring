<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
	<form action="/member/modify" method="post">
		<div class="mb-3">
		  <label for="id" class="form-label">ID</label>
		  <input type="text" class="form-control" name="id" id="id" value="${ses.id}" readonly="readonly">
		</div>
		<div class="mb-3">
		  <label for="pw" class="form-label">Password</label>
		  <input type="password" class="form-control" name="pw" id="pw" placeholder="새로운 비밀번호를 입력해주세요!">
		</div>
		<div class="mb-3">
		  <label for="name" class="form-label">Name</label>
		  <input type="text" class="form-control" name="name" id="name" value="${ses.name}">
		</div>
		<div class="mb-3">
		  <label for="email" class="form-label">E-mail</label>
		  <input type="text" class="form-control" name="email" id="email" value="${ses.email}">
		</div>
		<div class="mb-3">
		  <label for="home" class="form-label">Home</label>
		  <input type="text" class="form-control" name="home" id="home" value="${ses.home}">
		</div>
		<div class="mb-3">
		  <label for="age" class="form-label">Age</label>
		  <input type="text" class="form-control" name="age" id="age" value="${ses.age}">
		</div>
		<button type="submit" class="btn btn-primary">Modify</button>
		<a href="/member/remove?id=${ses.id}"><button type="button" class="btn btn-danger">Delete</button></a>
	</form>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>
