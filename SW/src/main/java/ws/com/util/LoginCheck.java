package ws.com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//컨트롤러가 특정작업(글입력,수정,삭제)을 진행하기 전에 
//자동으로 실행되는 클래스이다

//인터셉터 클래스가 되기위해서는
//1. 반드시  HandlerInterceptorAdapter클래스를 상속을 받아야한다
//2. 반드시 preHandle함수를 Override 해야한다
//		=> 컨트롤러가 실행되기 전에 실행되는 함수
public class LoginCheck extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		// 이 함수가 true를 반환하면   컨트롤러가 실행된다
		// 이 함수가 false를 반환하면  컨트롤러가 실행x된다
		
		//우리는 로그인여부를 판단하는 기능을 넣고 싶다
		//로그인했으면 true주어서 해당컨트롤러(예)글입력,수정,삭제) 실행되도록 하자
		
		//로그인여부판단은 세션에 데이터가 있으면 로그인되었다는 의미
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("UID");
		
		if( id==null || id.length()==0 ) {
			//로그인이 아니된 경우                     
			try { //(로그인 폼을 보여줘요)  
				response.sendRedirect("../member/loginForm.do");
			}
			catch(Exception e) {}
			return false;
		}
		else {
			//로그인 된 경우
			return true;
		}
		
	}

}




