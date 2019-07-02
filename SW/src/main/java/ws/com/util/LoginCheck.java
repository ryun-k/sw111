package ws.com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//��Ʈ�ѷ��� Ư���۾�(���Է�,����,����)�� �����ϱ� ���� 
//�ڵ����� ����Ǵ� Ŭ�����̴�

//���ͼ��� Ŭ������ �Ǳ����ؼ���
//1. �ݵ��  HandlerInterceptorAdapterŬ������ ����� �޾ƾ��Ѵ�
//2. �ݵ�� preHandle�Լ��� Override �ؾ��Ѵ�
//		=> ��Ʈ�ѷ��� ����Ǳ� ���� ����Ǵ� �Լ�
public class LoginCheck extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		// �� �Լ��� true�� ��ȯ�ϸ�   ��Ʈ�ѷ��� ����ȴ�
		// �� �Լ��� false�� ��ȯ�ϸ�  ��Ʈ�ѷ��� ����x�ȴ�
		
		//�츮�� �α��ο��θ� �Ǵ��ϴ� ����� �ְ� �ʹ�
		//�α��������� true�־ �ش���Ʈ�ѷ�(��)���Է�,����,����) ����ǵ��� ����
		
		//�α��ο����Ǵ��� ���ǿ� �����Ͱ� ������ �α��εǾ��ٴ� �ǹ�
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("UID");
		
		if( id==null || id.length()==0 ) {
			//�α����� �ƴϵ� ���                     
			try { //(�α��� ���� �������)  
				response.sendRedirect("../member/loginForm.do");
			}
			catch(Exception e) {}
			return false;
		}
		else {
			//�α��� �� ���
			return true;
		}
		
	}

}




