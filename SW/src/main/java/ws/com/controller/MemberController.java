package ws.com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ws.com.service.MemberService;
import ws.com.vo.MemberVO;

@Controller
public class MemberController {
	@Autowired
	MemberService mService;
	
	//로그인 폼 보여주기 요청
	@RequestMapping("/member/loginForm.do")
	public String loginForm() {
		//1.파라미터 받고
		//2.비즈니스 로직수행를 서비스에게 위임
		//3.모델
		//4.뷰
		return "member/loginForm";
		//주의 뷰지정시   맨 앞에는 /없이...   맨 마지막에는 확장자없이   작성
	}
	
	//로그인처리
	@RequestMapping("/member/loginProc.do")
	public ModelAndView loginProc(MemberVO vo, HttpSession session) {
		//1.(VO클래스를 이용해서) 파라미터 받고
		
		//2.비즈니스 로직수행를 서비스에게 위임
			//로그인 검사
			//user가 입력한 내용과 db의 내용을 비교하여 회원여부를 판단
		//MemberService mService = new MemberService();
		mService.loginProc(vo, session);
		//로그인이 끝나면   (로그인성공이면 세션에 필요한 정보가 담긴후..)
		//다음 작업을 위한 페이지로 이동하기위한 요청이 있어야하지만
		//여기에서는 우선 로그인폼으로 이동시킬예정
		//4.뷰
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView("../member/loginForm.do");
		//필요하면 파라미터를 포함시킬 수 있다
		//rv.addSaticAttribue(~,~);
		mv.setView(rv);
		return mv;
	}
}






