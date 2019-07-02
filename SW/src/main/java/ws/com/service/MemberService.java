package ws.com.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.com.dao.MemberDAO;
import ws.com.vo.MemberVO;

@Service
//	@Service 어노테이션 처리를 하면 자동적으로 bean 처리가 된다.
//	만약 @Service 어노테이션 처리를 하면 환경 설정에서 <bean> 하지 않아도 된다.
public class MemberService {
	@Autowired
	private	MemberDAO	mDAO;
	
	//	로그인 처리를 위한 함수
	public void loginProc(MemberVO vo, HttpSession session) {
		System.out.println("MemberService.java의 loginProc()");
		/*파라미터로 MemberVO를 받은 이유는
		 * 로그인을 하기위해서는 user가 입력한 정보를 필요하기 때문
		 */
		/*파라미터로 HttpSession를 받은 이유는
		 * 로그인처리가 성공적으로 완료되면 필요한 정보를 세션에 담아놓기위함
		 */
		
		//쿼리실행에 필요한 정보를 Map으로 알려주기로 했으므로
		//Map에 필요한 정보를 저장시켜주자
		HashMap map = new HashMap();
		map.put("id", vo.getId()); //데이터는 컨트롤러에서 VO형태로 넘어왔다
		map.put("pw", vo.getPw());
		//주의!!  sql명령문 안에  #{???}의 부분에서 ???은 Map의 키값으로 알려줘야한다
		//		대소문자까지 정확히 처리하자
		
		HashMap result = mDAO.loginProc(map);
		//쿼리실행과를 HashMap으로 받았다
		
		//회원정보의 존재여부에 따라서 로그인 처리를 해준다
		if(result==null || result.size()==0) {
			//회원이 아니다			
		}
		else {
			//회원이다
			//세션에 필요한 정보를 담자.. 그래야  여러 필요한 곳에서 로그인여부를 파악할 수 있다
			session.setAttribute("UID", result.get("ID"));
			session.setAttribute("UNICK", result.get("NICK"));
			//쿼리문의 실행결과 Map인경우
			//key는 컬럼명,  value는 컬럼값으로 자동 세팅된다 
			//   result.put(컬럼명, 컬럼값)
			//주의!!!   컬럼명은 항상 대문자로 변환되어서 기억된다!!!
			//예) result.put("ID", ~~~)
			
		}
	}
}





