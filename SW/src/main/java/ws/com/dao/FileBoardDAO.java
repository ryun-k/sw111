package ws.com.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import ws.com.vo.FileBoardVO;

//이 클래스는 파일업로드 관련 쿼리를 실행할 DAO클래스이다
//myBatis를 이용해서   필요한 쿼리문을 실행하고 
//그 결과를 알려줄  DAO클래스이다.
//myBatis를 이용한 DAO 클래스는 반드시 
//SqlSessionDaoSupport 클래스를 상속받아야 한다


//기존 JSP에서의 스테이트먼트가  myBatis에서는 Session이라고 말한다
//Session을 만드는 방법2
//1. getSqlSession()이용
//2. DI를 이용
public class FileBoardDAO extends SqlSessionDaoSupport{
	
	//총 데이터 개수 조회 쿼리실행 함수
	public int getTotalCount() {
		/*SqlSession session = this.getSqlSession();
		int result = session.selectOne("fileBoard.totalCount");
		return result;*/
		
		//위의 코드를 줄일 수 있다
		return (Integer)getSqlSession().selectOne("fileBoard.totalCount");
		
		/*참고
		 * 쿼리실행명령에서
		 * selectOne은 	결과가 한   줄로 나오는 경우 사용
		 * selectList는	결과가 여러 줄로 나오는 경우 사용
		 */
	}
	
	
	//목록 조회 쿼리실행 함수
	public ArrayList getBoardList(FileBoardVO vo) {
		return (ArrayList) getSqlSession().selectList("fileBoard.boardList",vo);
	}
	
	
	
	//데이터 입력 쿼리실행 함수
	public void insertBoard(FileBoardVO vo,String kind) {
		//파라미터로 vo를 사용한 이유 : 쿼리실행시 정보를 vo로 제시하기로 약속
		SqlSession session = this.getSqlSession();
		
		if(kind.equals("board")) {//fileBoard에 데이터입력
			session.insert("fileBoard.insertBoard", vo);
		}
		else if(kind.equals("fileInfo")){//fileInfo에 데이터입력
			session.insert("fileBoard.insertFileInfo", vo);
		}
	}
	
	
	//상세보기검색 쿼리실행 함수
	public FileBoardVO getBoardView(int no) {
		return (FileBoardVO)getSqlSession().selectOne("fileBoard.boardView",no);
	}

	
	//조회수증가 쿼리실행 함수~~~~~~~~~~~~~~~~~~~~
	public void updateHit(int oriNo) {
		getSqlSession().update("fileBoard.updateHit",oriNo);
	}
	
	//첨부파일 쿼리실행 함수
	public ArrayList getFileInfo(int oriNo) {
		return (ArrayList)getSqlSession().selectList("fileBoard.fileInfo", oriNo);
	}
	
	
	//다운로드 파일 정보 검색쿼리실행 함수 
	public FileBoardVO  getDownloadFile(int fileNo) {
		return (FileBoardVO)getSqlSession().selectOne("fileBoard.download",fileNo);	
	}
	
}








