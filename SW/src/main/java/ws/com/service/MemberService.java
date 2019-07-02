package ws.com.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.com.dao.MemberDAO;
import ws.com.vo.MemberVO;

@Service
//	@Service ������̼� ó���� �ϸ� �ڵ������� bean ó���� �ȴ�.
//	���� @Service ������̼� ó���� �ϸ� ȯ�� �������� <bean> ���� �ʾƵ� �ȴ�.
public class MemberService {
	@Autowired
	private	MemberDAO	mDAO;
	
	//	�α��� ó���� ���� �Լ�
	public void loginProc(MemberVO vo, HttpSession session) {
		System.out.println("MemberService.java�� loginProc()");
		/*�Ķ���ͷ� MemberVO�� ���� ������
		 * �α����� �ϱ����ؼ��� user�� �Է��� ������ �ʿ��ϱ� ����
		 */
		/*�Ķ���ͷ� HttpSession�� ���� ������
		 * �α���ó���� ���������� �Ϸ�Ǹ� �ʿ��� ������ ���ǿ� ��Ƴ�������
		 */
		
		//�������࿡ �ʿ��� ������ Map���� �˷��ֱ�� �����Ƿ�
		//Map�� �ʿ��� ������ �����������
		HashMap map = new HashMap();
		map.put("id", vo.getId()); //�����ʹ� ��Ʈ�ѷ����� VO���·� �Ѿ�Դ�
		map.put("pw", vo.getPw());
		//����!!  sql��ɹ� �ȿ�  #{???}�� �κп��� ???�� Map�� Ű������ �˷�����Ѵ�
		//		��ҹ��ڱ��� ��Ȯ�� ó������
		
		HashMap result = mDAO.loginProc(map);
		//����������� HashMap���� �޾Ҵ�
		
		//ȸ�������� ���翩�ο� ���� �α��� ó���� ���ش�
		if(result==null || result.size()==0) {
			//ȸ���� �ƴϴ�			
		}
		else {
			//ȸ���̴�
			//���ǿ� �ʿ��� ������ ����.. �׷���  ���� �ʿ��� ������ �α��ο��θ� �ľ��� �� �ִ�
			session.setAttribute("UID", result.get("ID"));
			session.setAttribute("UNICK", result.get("NICK"));
			//�������� ������ Map�ΰ��
			//key�� �÷���,  value�� �÷������� �ڵ� ���õȴ� 
			//   result.put(�÷���, �÷���)
			//����!!!   �÷����� �׻� �빮�ڷ� ��ȯ�Ǿ ���ȴ�!!!
			//��) result.put("ID", ~~~)
			
		}
	}
}





