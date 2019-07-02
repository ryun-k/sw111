package ws.com.vo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

//파일에 대한 여러가지 정보를 담당할 클래스
public class FileBoardVO {
	//필요하면 다른 정보도 추가
	private String id;
	private String writer;
	private String title;
	private String body;
	private String pw;
	
	//private MultipartFile files;
	//동일한 이름으로 업로드되는 파일이 한 개일 경우 일반변수
	
	private MultipartFile[] files;
	//동일한 이름으로 업로드되는 파일이 여러 개일 경우 배열변수
	
	private	int no;	//번호
	
	private	int oriNo; //게시물번호
	private	String path; //저장된 경로	
	private	String oriName; //원래이름
	private	String saveName; //파일의 실제저장이름
	private	long   len;	//파일의 크기
	
	private Date wDate; //작성일
	private int	 hit;	//조회수
	private String nick;
	private int  fileCount; //첨부파일수
	private int	 rno;
	private int  start;
	private int  end;
	
	private int fileNo;  //파일번호
	private int nowPage;
	
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrBody() {
		String result = null;
		if( body!=null && body.length()!=0 ) { //데이터가 존재하면
			result = body.replaceAll("\r\n", "<br/>");
		}
		return result;
	}
	public String getBody() {
		return body;
	}	
	public void setBody(String body) {
		this.body = body;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getOriNo() {
		return oriNo;
	}
	public void setOriNo(int oriNo) {
		this.oriNo = oriNo;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	
	//천단위마다 컴마찍기
	public String getComma() {
		DecimalFormat form = new DecimalFormat("###,###,###");
		return form.format(len);
	}
	
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	
	public String getwDate() {
		//내가 원하는 작업을 할 수 있다
		SimpleDateFormat form = new SimpleDateFormat("yyyy.MM.dd hh시 mm분 a");
		
		return form.format(wDate);
		
	}
	
	//함수를 필요에 의해서 추가
	public String getWDate2() {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat form2 = new SimpleDateFormat("hh:mm:ss");
		
		String now   = form1.format(new Date()); //현재일시정보의 포맷변경
		String wDay  = form1.format(wDate); //글작성일정보의 포맷변경
		
		if( !now.equals(wDay) ) { //오늘날짜를 작성일과 비교하여 동일하지않을 경우
			return wDay;
		}
		else { //작성일이 오늘날짜와 동일하면
			return form2.format(wDate);
		}
	}
	
	public void setwDate(Date wDate) {
		this.wDate = wDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	

}
