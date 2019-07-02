package ws.com.vo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

//���Ͽ� ���� �������� ������ ����� Ŭ����
public class FileBoardVO {
	//�ʿ��ϸ� �ٸ� ������ �߰�
	private String id;
	private String writer;
	private String title;
	private String body;
	private String pw;
	
	//private MultipartFile files;
	//������ �̸����� ���ε�Ǵ� ������ �� ���� ��� �Ϲݺ���
	
	private MultipartFile[] files;
	//������ �̸����� ���ε�Ǵ� ������ ���� ���� ��� �迭����
	
	private	int no;	//��ȣ
	
	private	int oriNo; //�Խù���ȣ
	private	String path; //����� ���	
	private	String oriName; //�����̸�
	private	String saveName; //������ ���������̸�
	private	long   len;	//������ ũ��
	
	private Date wDate; //�ۼ���
	private int	 hit;	//��ȸ��
	private String nick;
	private int  fileCount; //÷�����ϼ�
	private int	 rno;
	private int  start;
	private int  end;
	
	private int fileNo;  //���Ϲ�ȣ
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
		if( body!=null && body.length()!=0 ) { //�����Ͱ� �����ϸ�
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
	
	//õ�������� �ĸ����
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
		//���� ���ϴ� �۾��� �� �� �ִ�
		SimpleDateFormat form = new SimpleDateFormat("yyyy.MM.dd hh�� mm�� a");
		
		return form.format(wDate);
		
	}
	
	//�Լ��� �ʿ信 ���ؼ� �߰�
	public String getWDate2() {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat form2 = new SimpleDateFormat("hh:mm:ss");
		
		String now   = form1.format(new Date()); //�����Ͻ������� ���˺���
		String wDay  = form1.format(wDate); //���ۼ��������� ���˺���
		
		if( !now.equals(wDay) ) { //���ó�¥�� �ۼ��ϰ� ���Ͽ� ������������ ���
			return wDay;
		}
		else { //�ۼ����� ���ó�¥�� �����ϸ�
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
