package ws.com.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import ws.com.dao.FileBoardDAO;
import ws.com.util.PageUtil;
import ws.com.vo.FileBoardVO;

public class FileBoardService {
	@Autowired
	private FileBoardDAO fDAO;
	
	//����¡ó�� �����Լ�
	public PageUtil getPageInfo(int nowPage) {
		//�� ������ ���� ������
		int totalCount = fDAO.getTotalCount();
		
		PageUtil pageInfo = new PageUtil(nowPage,totalCount,3,5);
		return pageInfo;
	}
	
	//����� ���ϴ� �Լ�
	public ArrayList getBoardList(PageUtil  pInfo){
		//�Ķ���Ͱ� PageUtil �� ������
		//����� ���ϴµ� ���Ǵ� �������� where���ǹ��� 
		//RNO BETWEEN #{start} AND #{end}�� ���ǰ�
		//�� ������ ä����� �Ѵ�
		//nowPage�� ���� start�� end�� ���ϱ� ����
		/*nowPage�� 1�̸�          1  3
		 *         2�̸�         4   6
		 */
		                     //(����������-1) * ���������� ������ �Խù���     +1
		int start = (pInfo.getNowPage()-1) * pInfo.getListCount()+1;
		int end   = start + pInfo.getListCount() -1;
		
		FileBoardVO vo = new FileBoardVO();
		vo.setStart(start);
		vo.setEnd(end);
		
		ArrayList list = fDAO.getBoardList(vo);
		return list;
	}
	
	//�������Է� �����Լ� ȣ��
	public void inserBoard(FileBoardVO vo,
			HttpSession session,ArrayList list) {
		//�Ķ���ͷ� vo�� ����� ���� : ��������� ������ vo�� �����ϱ�� ���
		//�Ķ���ͷ� session�� ����� ���� : �����Է½� �۾��������� ���ǿ� �޾Ƽ�
		
		//���ǿ��� �ʿ��� ������ ������ (����ִ� id�� ����) �������ش�
		String id = (String)session.getAttribute("UID");
		vo.setId(id);
		//fileBoard�� �������Է�
		fDAO.insertBoard(vo,"board");
		
		//fileInfo�� �������Է�
		//÷�����Ͽ� ���� ������ list�� ����ְ�
		//list�� ������ŭ 
		for(int i=0; i<list.size(); i++) {
			//÷�������� �������� VO�� ����
			vo.setOriNo(vo.getNo());/*	#{oriNo}*/
			HashMap map =(HashMap)list.get(i);	
			vo.setPath((String)map.get("path"));
			vo.setOriName((String)map.get("oriName"));
			vo.setSaveName((String)map.get("saveName"));
			vo.setLen((Long)map.get("len"));
			
			//÷�������� ������ŭ insert����
			fDAO.insertBoard(vo,"fileInfo");
		}
		
	}
	
	//�󼼺��� ��û ó���Լ�
	public FileBoardVO getBoardView(int oriNo) {
		FileBoardVO vo = fDAO.getBoardView(oriNo);
		return vo;
	}
	
	
	//��ȸ�� ���� ��û ó���Լ�
	public void updateHit(int oriNo, HttpSession session) {
		//���ǿ� Ŭ���� �۹�ȣ�� ArrayList�� ���
		//������ ����?-> ��ȸ������
		//������ �ִ�?-> �ش���� ������ ����->��ȸ������
		//           Ȥ�� �ִ�->��ȸ������x
		boolean isHit = false; //��ȸ���������������� ����� ����
		ArrayList hitList = (ArrayList)session.getAttribute("HIT");
		
		if(hitList==null || hitList.size()==0) { //�Խ��Ǳ��� ó���ϴ� ����
			isHit = true; //��ȸ�������� �������� true�� �ο�
			hitList = new ArrayList(); //��������
			hitList.add(oriNo); //�۹�ȣ ���
			session.setAttribute("HIT",hitList);
		}
		else if( !hitList.contains(oriNo) ){ //�ش���� ������ ����?
			isHit = true;
			hitList.add(oriNo);//�۹�ȣ ���
			session.setAttribute("HIT",hitList);
		}
		else { //�ش� �Խù��� ������ �ִ� ������
			isHit = false; //��ȸ�� �������Ҳ��ϱ�
		}
		
		if(isHit == true) {
			fDAO.updateHit(oriNo);//��ȸ������
		}
	}
	
	
	//÷������ �˻� ��û ó���Լ�
	public ArrayList getFileInfo(int oriNo) {
		ArrayList list = fDAO.getFileInfo(oriNo);
		return list;
	}
	
	//�ٿ�ε� ���� ���� �˻� ��û ó���Լ�
	public FileBoardVO getDownloadFile(int fileNo) {
		FileBoardVO  vo = fDAO.getDownloadFile(fileNo);
		return vo;
	}
	
}






