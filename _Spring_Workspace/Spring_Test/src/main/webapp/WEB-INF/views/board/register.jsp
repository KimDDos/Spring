<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
	<form action="/board/register" method="post" enctype="multipart/form-data">
		<div class="mb-3">
			<label for="title" class="form-label">Title</label> <input
				type="text" class="form-control" name="title" id="title"
				placeholder=" 제 목 ">
		</div>
		<div class="mb-3">
			<label for="writer" class="form-label">Writer</label> <input
				type="text" class="form-control" name="writer" id="writer"
				placeholder=" 작 성 자 " value="${ses.id }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">Content</label>
			<textarea class="form-control" id="content" name="content" rows="3"></textarea>
		</div>

		<!-- file 입력 라인 추가 -->
		<div class="mb-3">
			<label for="file" class="form-label">Files....</label> <input
				type="file" class="form-control" name="files" id="file"
				multiple="multiple" style="display: none"> <br>
			<button type="button" class="btn btn-primary" id="trigger">FileUpload</button>
		</div>
		<!-- 파일 목록 표시라인 -->
		<div class="mb-3" id="fileZone">
			
		</div>

		<c:if test="${ses.id eq null }">
		<button type="submit" class="btn btn-primary" id="regBtn" disabled>전 송</button>
		<div style="text-align: center;"><p>게시글을 작성하시려면 로그인 해주세요.</p></div>
		</c:if>
		<c:if test="${ses.id ne null }">
		<button type="submit" class="btn btn-primary" id="regBtn">전 송</button>
		</c:if>
	</form>
</div>

<script>
	
</script>

<script src="/resources/js/boardRegister.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>

