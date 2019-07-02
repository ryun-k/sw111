package ws.com.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ws.com.service.FileBoardService;
import ws.com.util.FileUtil;
import ws.com.util.PageUtil;
import ws.com.vo.FileBoardVO;

@Controller
@RequestMapping("/fileBoard") //해당컨트롤러의 모든 요청이 /fileBoard인 경우라면
public class FileBoardController {
	@Autowired
	private FileBoardService fService;
	
	//전체 목록보기
	@RequestMapping("/boardList")
	public void fileBoardList(
		@RequestParam(value="nowPage", required=false, 	defaultValue="1") int nowPage,
			HttpServletRequest request
		) {
		//1. 파라미터 요청
		// user가 보고싶은 페이지를 알아내기 => @RequestParam이용
		/*만약 파라미터가 넘어오지 않을 경우   다른 값으로 대체할 수 있다	
		형식>@RequestParam(value="키값", required=false, defaultValue=?) 데이터타입 변수명*/			
		
		//2.비즈니스로직수행 ->서비스위임
		
			//2-1.페이징처리에 필요한 자료
			PageUtil pInfo = fService.getPageInfo(nowPage);
		
			//2-2.목록에 뿌릴 자료
			ArrayList list = fService.getBoardList(pInfo);
		
		//3.모델    정보를 뷰에게 전달해야한다
		request.setAttribute("LIST", list); //게시물내용
		request.setAttribute("PINFO", pInfo);//페이징처리
		
		//4.뷰 => 목록보기 페이지 보여줌
	}
	
	//글쓰기-1) 폼보기
	@RequestMapping("/writeForm")
	public String fileWriteForm() {
		// 로그인 처리=> 인터셉터설정
		return "fileBoard/writeForm";
	}
	
	
	//글쓰기-2) 글쓰기
	@RequestMapping("/writeProc")
	public ModelAndView fileWriteProc(FileBoardVO vo,
			HttpSession session/*String id*/, 
			ModelAndView mv){
		System.out.println("fileWriteProc()컨트롤러함수 호출성공");
		//1.파라미터 받기
		
		//2.비즈니스로직을 서비스위임
		/*서버로 데이터가 전송되면 업로드 파일은 임시 폴더에 자동 저장된다.
		  해당 컨트롤러 실행이 끝나면 이  파일은 자동 삭제된다.*/
		//파일을 정보를 알아낸다
		
		//파일을 만들때에는 어디(폴더)에 무슨 이름으로 만들것인지 지정해야 한다
		//new File(어디에, 무슨이름);
		//폴더명은   다운로드일경우에는 원하는 폴더를 지정
		//참고      폴더명은  만약에 web에서 보여줄 목적이라면 getRealPath()를 이용해서 만들어야 한다
		
		
		/*알아낸 파일정보를 토대로하여 임시 저장된 파일을 임시폴더에서 복사해서*/
		String path = "D:\\upload";
		//String path = "D:\\test\\upload";
		
		//업로드된 파일이 여러개일경우에는
		//파일의 정보가 배열에 기억되므로  배열을 이용해서 각 파일의 정보를 받아야 한다
		
		//파일의 정보를 하나라 묶기 위한 변수
		ArrayList list = new ArrayList();
		
		for( int i=0; i<vo.getFiles().length ;i++) {
			//파일이름을 알아내기
			String oriName = vo.getFiles()[i].getOriginalFilename();
			
			//파일이 업로드 되지 않을 경우   다음 파일을 업로드해라..
			if( oriName==null || oriName.length()==0 ) {
				continue;
			}
			
			String saveName = FileUtil.renameTo(path, oriName);
			
			try {
				File file = new File(path, saveName);
		
			   	//강제로 특정폴더에 실제 업로드(복사)시켜 줘야 한다
				//.transferTo()를 이용해서 복사
				vo.getFiles()[i].transferTo(file);
			
				//위의 코드까지는 하나의 파일이 업로드된 상태이다
				//업로드된 파일은 정보를 Map()으로 묶자
				HashMap map = new HashMap();
				map.put("path", path);	//저장된 경로
				map.put("oriName", oriName);//원래이름
				map.put("saveName", saveName);//저장된이름
				map.put("len", file.length());//파일크기
			
				list.add(map);
				
			} catch (Exception e) {
				System.out.println("파일복사에러 = " + e);
			}
			
		}//for
		System.out.println("컨트롤러 list개수="+list.size());
		
		
		System.out.println("insert문실행 전 no="+vo.getNo());
		fService.inserBoard(vo, session,  list);
		System.out.println("insert문실행 후 no="+vo.getNo());
		
		//3.모델
		//4.뷰: 리다이렉트 : 목록보기
		RedirectView rv = new RedirectView("../fileBoard/boardList.do");
	    //필요시 리다이렉트뷰에도 파라미터를 추가하여 보낼 수 있다
		//rv.addStaticAttribute(name, value);
		                       //("downloadFile", new File(~,~))~~~~~~~~~~~~   
		
		
		
		mv.setView(rv);
		return mv;
	}
	  
	
	
	//3.상세보기-1)조회수 증가     요청함수
	@RequestMapping("/hitProc")
	public ModelAndView	boardHitProc(ModelAndView mv,
			HttpServletRequest req,HttpSession session) {
		//1.파라미터 받기
		String  strOriNo= req.getParameter("oriNo");
		int     oriNo = Integer.parseInt(strOriNo);
		String  nowPage = req.getParameter("nowPage"); //릴레이용
		//2.비즈니스로직 ->서비스에게 위임
		fService.updateHit(oriNo, session);
		//3.모델
		//4.뷰
		RedirectView rv = new RedirectView("../fileBoard/boardView.do");
		//필요시 리다이렉트뷰에도 파라미터를 추가하여 보낼 수 있다
		rv.addStaticAttribute("oriNo", oriNo);
		rv.addStaticAttribute("nowPage", nowPage);
		mv.setView(rv);
		return mv;
	}
	
	
	//3.상세보기-2)상세보기   요청함수
	@RequestMapping("/boardView")
	public ModelAndView boardView(@RequestParam(value="oriNo") int oriNo,
			@RequestParam(value="nowPage") int nowPage) {
		//1.파라미터 받기
		//2.비즈니스로직 ->서비스에게 위임
		//파일게시물내용
		FileBoardVO  vo = fService.getBoardView(oriNo);
		
		//첨부파일정보가져올 예정
		ArrayList list = fService.getFileInfo(oriNo);
		//System.out.println("list.size()="+list.size());
		
		//3.모델 & 4.뷰
		ModelAndView mv = new ModelAndView();
		mv.addObject("VIEW",vo);//누가 어떤 제목과 내용으로 언제..
		mv.addObject("LIST",list);//첨부파일목록
		mv.addObject("nowPage",nowPage); //릴레이용
		
		
		/*HashMap map = new HashMap();
		map.put("VIEW", vo);
		map.put("LIST", list);
		mv.addAllObjects(map);*/
		mv.setViewName("/fileBoard/boardView");
		return mv;
	}
	
	//파일 다운로드 요청함수    
	@RequestMapping("/fileDownload")
	public ModelAndView fileDownload(@RequestParam(value="fileNo") int fileNo) {
		//1.파라미터 값
	
		//2.비즈니스로직 -> 서비스위임(해당파일번호를 이용해서 fileInfo에서  조회)
		//다운로드 파일 정보 검색 요청 
		FileBoardVO vo = fService.getDownloadFile(fileNo);
		
		/*컨트롤러에서는 <bean> 처리된 다운로드 클래스를 
		   ModelAndView 클래스에 등록해 줌으로써       다운로드가 이루어지게 된다.*/
		// 사용자 다운로드 뷰를 호출하기
		// 형식 ModelAndView mv = new ModelAndView("사용자다운로드뷰명","전달할데이터키값",전달할데이터);
		//xml문서에서 사용자뷰를 아래와 같이 등록해두었다
		//<beans:bean id="download" class="ws.com.util.DownloadUtil"/>
		//우리는 다운로드할 파일을
		//downloadFile  이라는 키값으로 파일의 정보를 알려주고 ,  File클래스로 넘겨주기로 한다면 --1번방식
		//FileBoardVO를 이용해서 파일객체를 만들자
		//File file = new File(vo.getPath(),vo.getSaveName());//--2번방식
		
		//데이터를 File로 넘길때--1번방식
		//ModelAndView mv = new ModelAndView("download","downloadFile",file);--1번방식
		
		//데이터를 vo로 넘길때
		ModelAndView mv = new ModelAndView("download","downloadFile",vo);//--2번방식
		//내부적으로  전달할데이터키값을 이용하여  전달할데이터가 Map으로 만들어져서  뷰에게 전달된다
		/* Map map = new Map();
		 * map.put("downloadFile",vo);
		 */
		
		return mv;
	}
	
	
	
	//4.수정하기-1)폼보기
	@RequestMapping("/modifyForm")
	public ModelAndView  modifyForm(HttpServletRequest request, ModelAndView mv) {
		//1.파라미터받고
		String strOriNo = request.getParameter("oriNo");
		int    oriNo    = Integer.parseInt(strOriNo);
		String nowPage  = request.getParameter("nowPage");//릴레이용
		
		//2.로직 
		//파일게시물내용
		FileBoardVO  vo = fService.getBoardView(oriNo); //서비스를 사용하는 이유
		
		//3.모델
		mv.addObject("VIEW",vo);//누가 어떤 제목과 내용으로 언제..
		mv.addObject("nowPage",nowPage);//릴레이용
		//4.뷰
		/*참고	mv.setView("리다이렉트용뷰");
				mv.setViewName("일반 뷰");*/
		mv.setViewName("fileBoard/modifyForm");	
		return mv;
	}
	
	//4.수정하기-2)수정하기
	@RequestMapping("/modifyProc")
	public ModelAndView modifyProc(FileBoardVO vo,ModelAndView mv, RedirectView rv) {
		//1.파라미터받고
		System.out.println("vo.getOriNo()="+vo.getOriNo());
		System.out.println("vo.getNowPage()="+vo.getNowPage());
		System.out.println("vo.getPw()="+vo.getPw());
	/*	파라메터	oriNo=
				nowPage=(릴레이용)
				title
				body
				pw*/
		//2.비즈니스로직
		//	2-1) 제목,내용,비번은 update
		//	2-2) 첨부파일이 있으면 업로드(첨부파일없으면 x)
		//첨부파일
		//3.모델
		//4.뷰
		//뷰				Redirect View : 상세보기
		rv.setUrl("../fileBoard/boardView.do");
		mv.setView(rv);
		return mv;
	}
	
}











