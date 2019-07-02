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
	
	//페이징처리 실행함수
	public PageUtil getPageInfo(int nowPage) {
		//총 데이터 개수 구하자
		int totalCount = fDAO.getTotalCount();
		
		PageUtil pageInfo = new PageUtil(nowPage,totalCount,3,5);
		return pageInfo;
	}
	
	//목록을 구하는 함수
	public ArrayList getBoardList(PageUtil  pInfo){
		//파라미터가 PageUtil 인 이유는
		//목록을 구하는데 사용되는 쿼리문의 where조건문에 
		//RNO BETWEEN #{start} AND #{end}이 사용되고
		//요 내용을 채워줘야 한다
		//nowPage에 따라서 start와 end가 변하기 때문
		/*nowPage가 1이면          1  3
		 *         2이면         4   6
		 */
		                     //(현재페이지-1) * 한페이지에 보여줄 게시물수     +1
		int start = (pInfo.getNowPage()-1) * pInfo.getListCount()+1;
		int end   = start + pInfo.getListCount() -1;
		
		FileBoardVO vo = new FileBoardVO();
		vo.setStart(start);
		vo.setEnd(end);
		
		ArrayList list = fDAO.getBoardList(vo);
		return list;
	}
	
	//데이터입력 실행함수 호출
	public void inserBoard(FileBoardVO vo,
			HttpSession session,ArrayList list) {
		//파라미터로 vo를 사용한 이유 : 쿼리실행시 정보를 vo로 제시하기로 약속
		//파라미터로 session을 사용한 이유 : 쿼리입력시 글쓴이정보를 세션에 받아서
		
		//세션에서 필요한 정보를 가져와 (비어있는 id를 마져) 세팅해준다
		String id = (String)session.getAttribute("UID");
		vo.setId(id);
		//fileBoard에 데이터입력
		fDAO.insertBoard(vo,"board");
		
		//fileInfo에 데이터입력
		//첨부파일에 대한 정보는 list에 담겨있고
		//list의 개수만큼 
		for(int i=0; i<list.size(); i++) {
			//첨부파일의 각정보를 VO에 세팅
			vo.setOriNo(vo.getNo());/*	#{oriNo}*/
			HashMap map =(HashMap)list.get(i);	
			vo.setPath((String)map.get("path"));
			vo.setOriName((String)map.get("oriName"));
			vo.setSaveName((String)map.get("saveName"));
			vo.setLen((Long)map.get("len"));
			
			//첨부파일의 개수만큼 insert하자
			fDAO.insertBoard(vo,"fileInfo");
		}
		
	}
	
	//상세보기 요청 처리함수
	public FileBoardVO getBoardView(int oriNo) {
		FileBoardVO vo = fDAO.getBoardView(oriNo);
		return vo;
	}
	
	
	//조회수 증가 요청 처리함수
	public void updateHit(int oriNo, HttpSession session) {
		//세션에 클릭한 글번호를 ArrayList에 기록
		//세션이 없다?-> 조회수증가
		//세션이 있니?-> 해당글을 본적이 없다->조회수증가
		//           혹은 있다->조회수증가x
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
			fDAO.updateHit(oriNo);//조회수증가
		}
	}
	
	
	//첨부파일 검색 요청 처리함수
	public ArrayList getFileInfo(int oriNo) {
		ArrayList list = fDAO.getFileInfo(oriNo);
		return list;
	}
	
	//다운로드 파일 정보 검색 요청 처리함수
	public FileBoardVO getDownloadFile(int fileNo) {
		FileBoardVO  vo = fDAO.getDownloadFile(fileNo);
		return vo;
	}
	
}






