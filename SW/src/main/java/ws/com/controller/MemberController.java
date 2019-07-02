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
	
	//�α��� �� �����ֱ� ��û
	@RequestMapping("/member/loginForm.do")
	public String loginForm() {
		//1.�Ķ���� �ް�
		//2.����Ͻ� �������ฦ ���񽺿��� ����
		//3.��
		//4.��
		return "member/loginForm";
		//���� ��������   �� �տ��� /����...   �� ���������� Ȯ���ھ���   �ۼ�
	}
	
	//�α���ó��
	@RequestMapping("/member/loginProc.do")
	public ModelAndView loginProc(MemberVO vo, HttpSession session) {
		//1.(VOŬ������ �̿��ؼ�) �Ķ���� �ް�
		
		//2.����Ͻ� �������ฦ ���񽺿��� ����
			//�α��� �˻�
			//user�� �Է��� ����� db�� ������ ���Ͽ� ȸ�����θ� �Ǵ�
		//MemberService mService = new MemberService();
		mService.loginProc(vo, session);
		//�α����� ������   (�α��μ����̸� ���ǿ� �ʿ��� ������ �����..)
		//���� �۾��� ���� �������� �̵��ϱ����� ��û�� �־��������
		//���⿡���� �켱 �α��������� �̵���ų����
		//4.��
		ModelAndView mv = new ModelAndView();
		RedirectView rv = new RedirectView("../member/loginForm.do");
		//�ʿ��ϸ� �Ķ���͸� ���Խ�ų �� �ִ�
		//rv.addSaticAttribue(~,~);
		mv.setView(rv);
		return mv;
	}
}






