package ws.com.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

// myBatis를 이용해서   필요한 쿼리문을 실행하고 
// 그 결과를 알려줄  DAO클래스이다.
// myBatis를 이용한 DAO 클래스는 반드시 
// SqlSessionDaoSupport 클래스를 상속받아야 한다


// 기존 JSP에서의 스테이트먼트가  myBatis에서는 Session이라고 말한다
// Session을 만드는 방법2
// 1. getSqlSession()이용
// 2. DI를 이용
public class MemberDAO extends SqlSessionDaoSupport {
	@Autowired
	SqlSessionTemplate session;
	
	//로그인 처리
	public  HashMap loginProc(HashMap map) {
		//1. getSqlSession()이용
		//SqlSession session = this.getSqlSession();q
		// 모든 함수를 실행할 때 마다 매번 받아야 한다는 단점
		
		//session.실행쿼리문에 해당하는 함수("실행쿼리문",Object 파라미터)
		//selectList실행결과가 여러 줄인 경우
		//selectOne 실행결과가 한    줄인 경우
		//selectMap 실행결과가 Map인 경우(selectOne과 동일개념)
		//select	기본 질의 명령
		HashMap result = session.selectOne("member.loginProc", map);
		return result;
	}
}








