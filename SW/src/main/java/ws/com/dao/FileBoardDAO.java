package ws.com.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import ws.com.vo.FileBoardVO;

//�� Ŭ������ ���Ͼ��ε� ���� ������ ������ DAOŬ�����̴�
//myBatis�� �̿��ؼ�   �ʿ��� �������� �����ϰ� 
//�� ����� �˷���  DAOŬ�����̴�.
//myBatis�� �̿��� DAO Ŭ������ �ݵ�� 
//SqlSessionDaoSupport Ŭ������ ��ӹ޾ƾ� �Ѵ�


//���� JSP������ ������Ʈ��Ʈ��  myBatis������ Session�̶�� ���Ѵ�
//Session�� ����� ���2
//1. getSqlSession()�̿�
//2. DI�� �̿�
public class FileBoardDAO extends SqlSessionDaoSupport{
	
	//�� ������ ���� ��ȸ �������� �Լ�
	public int getTotalCount() {
		/*SqlSession session = this.getSqlSession();
		int result = session.selectOne("fileBoard.totalCount");
		return result;*/
		
		//���� �ڵ带 ���� �� �ִ�
		return (Integer)getSqlSession().selectOne("fileBoard.totalCount");
		
		/*����
		 * ���������ɿ���
		 * selectOne�� 	����� ��   �ٷ� ������ ��� ���
		 * selectList��	����� ���� �ٷ� ������ ��� ���
		 */
	}
	
	
	//��� ��ȸ �������� �Լ�
	public ArrayList getBoardList(FileBoardVO vo) {
		return (ArrayList) getSqlSession().selectList("fileBoard.boardList",vo);
	}
	
	
	
	//������ �Է� �������� �Լ�
	public void insertBoard(FileBoardVO vo,String kind) {
		//�Ķ���ͷ� vo�� ����� ���� : ��������� ������ vo�� �����ϱ�� ���
		SqlSession session = this.getSqlSession();
		
		if(kind.equals("board")) {//fileBoard�� �������Է�
			session.insert("fileBoard.insertBoard", vo);
		}
		else if(kind.equals("fileInfo")){//fileInfo�� �������Է�
			session.insert("fileBoard.insertFileInfo", vo);
		}
	}
	
	
	//�󼼺���˻� �������� �Լ�
	public FileBoardVO getBoardView(int no) {
		return (FileBoardVO)getSqlSession().selectOne("fileBoard.boardView",no);
	}

	
	//��ȸ������ �������� �Լ�~~~~~~~~~~~~~~~~~~~~
	public void updateHit(int oriNo) {
		getSqlSession().update("fileBoard.updateHit",oriNo);
	}
	
	//÷������ �������� �Լ�
	public ArrayList getFileInfo(int oriNo) {
		return (ArrayList)getSqlSession().selectList("fileBoard.fileInfo", oriNo);
	}
	
	
	//�ٿ�ε� ���� ���� �˻��������� �Լ� 
	public FileBoardVO  getDownloadFile(int fileNo) {
		return (FileBoardVO)getSqlSession().selectOne("fileBoard.download",fileNo);	
	}
	
}








