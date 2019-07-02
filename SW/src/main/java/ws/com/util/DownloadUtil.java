package ws.com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import ws.com.vo.FileBoardVO;

/* 이 클래스는 스트림 방식으로  
 * 서버의 파일을  클라이언트에게 전달할 목적으로 사용할 클래스
 * 
 * 스프링에서 다운로드를 위한 클래스를 만들기
 * 조건
 * 1. 반드시 AbstractView클래스를 상속받아야 한다 => 뷰 패키지 소속
 * 2. 반드시 renderMergedOutputModel()메소드를  오버라이딩해야 한다
 */

public class DownloadUtil extends AbstractView{

	//생성자함수를 이용해서
	//이 클래스가 new가 되면  응답방식을 스트림방식으로 변경하도록 할 예정
	public DownloadUtil() {
		this.setContentType("application/download;charset=UTF-8");
	}
	
	//이 함수가 실제적으로 스트림방식으로   
	//파일을 클라이언트에게 전달할 작업을 담당함수
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//파라미터 model => 컨트롤러가 제공하는  다운로드 파일에 대한 정보를 기억할 변수
		//우리끼리의 약속
		// 컨트롤러에서는   downloadFile  이라는 키값으로 파일의 정보를 알려주기로 하자
		//            파일정보는               File클래스로 넘겨받을시
		//File file = (File)model.get("downloadFile");--1번방식
		
		//      파일정보는               FileBoardVO클래스로 넘겨받을시
		FileBoardVO vo = (FileBoardVO)model.get("downloadFile");//--2번방식
		
		//다운로드 해줄 환경설정 ----------------------------
		//응답방식을 스트림방식으로  세팅하자
		response.setContentType(this.getContentType());
		
		//응답파일의 크기 알려주기
		//파일의 크기는 long타입으로 리턴되지만  아래함수의 파라미터유형은 int이므로 형변환
		//response.setContentLength( (int)file.length());--1번방식	
		
		response.setContentLength( (int)vo.getLen());//--2번방식
		
		//다운로드시켜줄 파일의 이름지정하기
		//클라이언트는 이 파일의 이름을 보고 다운로드여부를 결정하게 된다
		//문제는 파일이름이 한글이면 깨질위험이 있다
		//고로 파일명에 한글이 존재하면 encoding처리를 해야 한다
		//String encoding=new String(file.getName().getBytes("UTF-8"), "8859_1");--1번방식

		String encoding=new String(vo.getOriName().getBytes("UTF-8"), "8859_1");//--2번방식
		
		response.setHeader("Content-Disposition", "attachment;filename="+encoding);
		
		//스트림 방식으로 다운로드를 실행------------------------
		// 파일의 정보를 읽어서..
		FileInputStream fin = null;
		OutputStream    fout = null;
		
		//vo를 이용해서 작업시에는 file객체를 만들어서 inputStream()으로 자료제공
		File file = new File(vo.getPath(),vo.getOriName());//--2번방식
		fin = new FileInputStream(file);//--1번방식//--2번방식 공통코드
		fout = response.getOutputStream(); 
		
		//스프링에서는   한쪽에서 읽은 내용을  다른쪽에 쓰는 부분을 함수로 제공하고 있다
		//사용함수        FileCopyUtils.copy(InputStream객체,OutputStream객체);
		try {
			FileCopyUtils.copy(fin,fout);
		} catch (Exception e) {
			System.out.println("다운로드 처리관련 에러="+e);
		}
		finally {
			fout.flush();
			fin.close();
			fout.close();
		}
		
		
	}

}







