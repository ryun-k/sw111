<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!doctype html>	
<html lang="ko">	
<head>	
	<title>list</title>		
	<meta charset="utf-8">	
  	<meta name="viewport" content="width=device-width, initial-scale=1">	
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">	
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>	
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>	
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
 	<script type="text/javascript">
 	$(function(){
 		//글쓰기버튼 클릭 시  페이지 이동
 		$('#wBtn').click(function(){
			$(location).attr("href","../notice/writeForm.do");
 		});
 		
 		//목록 클릭 시 뷰페이지 이동
 		$('#list > tr').click(function(){
 			var no = $(this).attr('id');
 			$(location).attr("href","../notice/hitProc.do?oriNo="+no+"&nowPage=${PINFO.nowPage}");
 			 
 		}); 
 	});
 	// ../notice/hitProc.do?oriNo=${data.no}&nowPage=${PINFO.nowPage}
 	</script>
</head>
<body>
<div class="container"  >
  <h2 align="center">공지사항</h2>
  <table class="table table-dark table-striped">
    <thead>
      <tr>
        <th>NO</th>
        <th>TITLE</th>
        <th>HIT</th>
        <th>DATE</th>
      </tr>
    </thead>
    <tbody id="list">
	    <c:forEach items="${LIST }" var="data">
	      <tr id="${data.no }">
	        <td>${data.no }</td>
	        <td>${data.title }</td>
	        <td>${data.hit }</td>
	        <td>${data.wDate }</td>
	      </tr>
	    </c:forEach>
    </tbody>
    <tfoot>
    	<tr>
    		<td colspan="5">
			  <ul class="pagination justify-content-center" >
				
				<c:if test="${PINFO.startPage eq 1}">
			    <li class="page-item">
					<a  class="page-link" href="" ><<</a>
			    </li>
				</c:if>
				<c:if test="${PINFO.startPage ne 1}">
			    <li class="page-item">
					<a class="page-link" href="../notice/List.do?nowPage=${PINFO.startPage-1}"><<</a>
			    </li>
				</c:if>
			    <c:forEach  var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}" step="1">
			    <li class="page-item">
			    	<a class="page-link" href="../notice/List.do?nowPage=${page}">${page}</a>
			    </li>
			    </c:forEach>
			    <c:if test="${PINFO.endPage eq PINFO.totalPage}">
				<li class="page-item">
			    	<a class="page-link" href="">>></a>
			    </li>
				</c:if>
				<c:if test="${PINFO.endPage ne PINFO.totalPage}">
			    <li class="page-item">
			    	<a class="page-link" href="../notice/List.do?nowPage=${PINFO.endPage+1}">>></a>
			    </li>
				</c:if>
			  </ul>
	  		</td>
	  	</tr>
    </tfoot>
  </table>
</div>
<div class="container">
	
	<ul>
		<c:if test="${sessionScope.UID eq 'admin'}">
		    <button type="button" class="btn btn-dark" id="wBtn">글쓰기</button>
		</c:if>
	</ul>
</div>
</body>
