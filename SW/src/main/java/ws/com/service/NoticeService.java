package ws.com.service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import ws.com.dao.NoticeDAO;
import ws.com.util.PageUtil;
import ws.com.vo.NoticeVo;

public class NoticeService {
	@Autowired
	private NoticeDAO nDAO;
	
	//페이징처리 실행함수
	public PageUtil getPageInfo(int nowPage, HttpSession session) {
		//총 데이터 개수 구하자
		int totalCount = nDAO.getTotalCount(session);
		System.out.println("totalCount "+totalCount);
		PageUtil pageInfo = new PageUtil(nowPage,totalCount,5,5);
		return pageInfo;
	}
	
	//목록을 구하는 함수
	public ArrayList getBoardList(PageUtil  pInfo, HttpSession session){

		int start = (pInfo.getNowPage()-1) * pInfo.getListCount()+1;
		int end   = start + pInfo.getListCount() -1;
		
		NoticeVo vo = new NoticeVo();
		vo.setStart(start);
		vo.setEnd(end);
		
		ArrayList list = nDAO.getNoticeList(vo,session);
		return list;
	}
	
	//insert
	public void insertNotice(NoticeVo vo) {
		System.out.println("insertNotice()");
		nDAO.insertNotice(vo);
	}
	
	//view
	public NoticeVo viewNotice(int oriNo) {
		System.out.println("oriNo "+oriNo);
		return nDAO.getView(oriNo); 
	}
	
	//modifyFrom
	public NoticeVo modifyNotice(int oriNo) {
		System.out.println("modifyNotice");
		return (NoticeVo)nDAO.modifyNotice(oriNo);
	}
	//modifyProc
	public void updateNotice(NoticeVo vo,int oriNo) {
		System.out.println("updateNotice");
		nDAO.updateNotice(vo);
	}
	
	
	//hit
	public void hitNotice(int oriNo,HttpSession session) {
		System.out.println("hitNotice");
		boolean isHit = false; //조회수증가할지말지를 기억할 변수
		ArrayList hitList = (ArrayList)session.getAttribute("HIT");
		
		if(hitList==null || hitList.size()==0) { //게시판구경 처음하는 유저
			isHit = true; //조회수증가할 예정으로 true값 부여
			hitList = new ArrayList(); //쿠폰바행
			hitList.add(oriNo); //글번호 기록
			session.setAttribute("HIT",hitList);
		}
		else if( !hitList.contains(oriNo) ){ //해당글을 본적이 없니?
			isHit = true;
			hitList.add(oriNo);//글번호 기록
			session.setAttribute("HIT",hitList);
		}
		else { //해당 게시물을 본적이 있는 유저다
			isHit = false; //조회수 증가안할꺼니까
		}
		
		if(isHit == true) {
			nDAO.hitNotice(oriNo);
		}
		
	}
	
	//delete
	public void deleteNotice(int oriNo) {
		System.out.println("deleteNotice");
		nDAO.deleteNotice(oriNo);
	}
}
