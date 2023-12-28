<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
	<c:set value="${boardDTO.bvo }" var="bvo"/>
	<form action="/board/modify" method="post" enctype="multipart/form-data">
	<input type="hidden" class="form-control" id="bno" name="bno" value="${bvo.bno}">
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
	<!-- 파일 목록 표시라인 -->
	<c:set value="${boardDTO.flist }" var="flist"/>
	<div class="mb-3" id="fileZone">
		<div>
			<ul style="list-style-type: none">
			<!-- 파일 개수만큼 li를 추가하여 파일을 표시, 타입이 1인 경우만 표시 -->
			<!-- 
				li -> div => img 그림표시
					  div => 파일이름, 작성일, span size
			 -->
			<!-- 파일 리스트 중 하나만 가져와서 fvo로 저장 -->
			<!-- JSP와 JAVA의 seperator의 기준이 다름!!  -->
				<c:forEach items="${flist }" var="fvo">
				<li>
					<c:choose>
						<c:when test="${fvo.file_type == 1 }">
							<!-- /upload/save_dir/uuid + _ + file_name -->
							<!-- file 경로를 찾아가야 하는 것임! -->
							<div>
								<img class="rounded mx-auto d-block" alt="404 not Found" src="/upload/${fn:replace(fvo.save_dir,'\\','/')}/${fvo.uuid}_${fvo.file_name}">
							</div>
						</c:when>
						<c:otherwise>
							<div style="text-align: center;">
								<i class="bi bi-sign-do-not-enter"></i>
								이미지가 없어요!
							</div>
						</c:otherwise>
					</c:choose>
					<div  style="text-align: center;">
						<!-- div => 파일이름, 작성일, span size -->
						<div><b>${fvo.file_name }</b></div>
						${fvo.reg_date }
					    <span class="badge text-bg-warning">${fvo.file_size }Byte</span>
						<button type="button" data-uuid="${fvo.uuid}" class="file-X">X</button>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<!-- file 입력 라인 추가 -->
	<div class="mb-3">
		<label for="file" class="form-label">Files....</label> <input
			type="file" class="form-control" name="files" id="file"
			multiple style="display: none"> <br>
		<button type="button" class="btn btn-primary" id="trigger">FileUpload</button>
	</div>
	
	<!-- 파일 목록 표시라인 -->
	<div class="mb-3" id="fileZone">
		
	</div>
	
	<a href="/board/delete?bno=${bvo.bno}"><button type="button" class="btn btn-success">Delete</button></a>
	<a href="/board/modify?bno=${bvo.bno}"><button type="submit" class="btn btn-success regBtn">Modify</button></a>
	</form>
</div>

<hr>
<script type="text/javascript">
	const bnoVal = `<c:out value="${bvo.bno}"/>`;
</script>
<script src="/resources/js/boardModify.js"></script>
<script src="/resources/js/boardRegister.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>