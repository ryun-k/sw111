package ws.com.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

// myBatis�� �̿��ؼ�   �ʿ��� �������� �����ϰ� 
// �� ����� �˷���  DAOŬ�����̴�.
// myBatis�� �̿��� DAO Ŭ������ �ݵ�� 
// SqlSessionDaoSupport Ŭ������ ��ӹ޾ƾ� �Ѵ�


// ���� JSP������ ������Ʈ��Ʈ��  myBatis������ Session�̶�� ���Ѵ�
// Session�� ����� ���2
// 1. getSqlSession()�̿�
// 2. DI�� �̿�
public class MemberDAO extends SqlSessionDaoSupport {
	@Autowired
	SqlSessionTemplate session;
	
	//�α��� ó��
	public  HashMap loginProc(HashMap map) {
		//1. getSqlSession()�̿�
		//SqlSession session = this.getSqlSession();q
		// ��� �Լ��� ������ �� ���� �Ź� �޾ƾ� �Ѵٴ� ����
		
		//session.������������ �ش��ϴ� �Լ�("����������",Object �Ķ����)
		//selectList�������� ���� ���� ���
		//selectOne �������� ��    ���� ���
		//selectMap �������� Map�� ���(selectOne�� ���ϰ���)
		//select	�⺻ ���� ���
		HashMap result = session.selectOne("member.loginProc", map);
		return result;
	}
}








