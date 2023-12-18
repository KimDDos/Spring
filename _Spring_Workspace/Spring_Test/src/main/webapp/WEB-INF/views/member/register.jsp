<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
	<form action="/member/register" method="post">
		<div class="mb-3">
		  <label for="id" class="form-label">ID</label>
		  <input type="text" class="form-control" name="id" id="id" placeholder=" id ">
		</div>
		<div class="mb-3">
		  <label for="pw" class="form-label">Password</label>
		  <input type="password" class="form-control" name="pw" id="pw" placeholder=" pw ">
		</div>
		<div class="mb-3">
		  <label for="name" class="form-label">Name</label>
		  <input type="text" class="form-control" name="name" id="name" placeholder=" name ">
		</div>
		<div class="mb-3">
		  <label for="email" class="form-label">E-mail</label>
		  <input type="text" class="form-control" name="email" id="email" placeholder=" E-mail ">
		</div>
		<div class="mb-3">
		  <label for="home" class="form-label">Home</label>
		  <input type="text" class="form-control" name="home" id="home" placeholder=" home ">
		</div>
		<div class="mb-3">
		  <label for="age" class="form-label">Age</label>
		  <input type="text" class="form-control" name="age" id="age" placeholder=" home ">
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>
