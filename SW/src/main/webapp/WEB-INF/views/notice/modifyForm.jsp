<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!doctype html>	
<html lang="en">	
<head>	
	<title>writeForm</title>
	<meta charset="utf-8">	
  	<meta name="viewport" content="width=device-width, initial-scale=1">	
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">	
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>	
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
 	
 	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 	
 	<script type="text/javascript">
 	
 	  $( function() {
	    $( "#startDate" ).datepicker();
        $( "#startDate" ).datepicker( "option", "dateFormat", "yy-mm-dd" ).datepicker('setDate', '${DATA.startDate}');
        $( "#endDate" ).datepicker();
        $( "#endDate" ).datepicker( "option", "dateFormat", "yy-mm-dd" ).datepicker('setDate', '${DATA.endDate}');
        
        $( "#wBtn" ).click(function() { 
        	var sd =$( "#startDate" ).val();
        	var ed =$( "#endDate" ).val();
        	if(sd<=ed){
	            if ($('input[name=ischeck]').is(":checked")) {
	                $('input[name=isshow]').val('Y');
	            } else {
	                $('input[name=isshow]').val('N');
	            }
	        	$("#wFrom").submit();
        	}
        	else if(sd>ed){
        		alert("시작일이 종료일보다 큽니다");
        		$( "#endDate" ).val("");
        	}

        });
        
        
       
 	 } );


 	</script>
</head>
<body>
<div class="container">
  <h1>writeForm</h1>
  <form class="form-horizontal" action="../notice/modifyProc.do"  id="wFrom" method="get">
  	<input type="hidden" id="oriNo" name="oriNo" value="${DATA.no }"/>
  	<input type="hidden" id="nowPage" name="nowPage" value="${nowPage }"/>
  	 
    <div class="form-group">
      <label class="control-label col-sm-2" >제목</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="title" name="title" value="${DATA.title}" />
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="content">내용</label>
      <div class="col-sm-10">          
        <textarea type="text" class="form-control" id="content" name="content">${DATA.content}</textarea>
      </div>
    </div>
    
	<div class="form-group">
      <label class="control-label col-sm-2" >시작일</label>
      <div class="col-sm-10">          
		<input type="text" class="form-control"  id="startDate" name="startDate" size="30" autocomplete="off" />
      </div>
      <label class="control-label col-sm-2" >종료일</label>
      <div class="col-sm-10">          
		<input type="text" class="form-control"  id="endDate"  name="endDate" size="30" autocomplete="off" />
      </div>
    </div> 
       
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <div class="checkbox">
         	<label><input type="checkbox" name="ischeck" />공개</label>
          	<input type="hidden" name="isshow" />
        </div>
      </div>
    </div>
        <button id="wBtn" type="button" class="btn btn-dark">수정하기</button>
  </form>
</div>

</body>
