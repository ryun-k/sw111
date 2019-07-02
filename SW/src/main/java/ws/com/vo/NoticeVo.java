package ws.com.vo;

import java.sql.Date;

public class NoticeVo {
	
	
	private int no;
	private String title;
	private String content;
	private Date startDate;
	private Date endDate;
	private Date wDate;
	private int hit;
	private String isshow;
	
	private int start;
	private int end;
	
	private int oriNo;
	
	private String brBody;
	
	
	
	public String getBrBody() {
		String result = null;
		if( content!=null && content.length()!=0 ) { //데이터가 존재하면
			result = content.replaceAll("\r\n", "<br/>");
		}
		return result;
	}
	public void setBrBody(String brBody) {
		this.brBody = brBody;
	}
	public int getOriNo() {
		return oriNo;
	}
	public void setOriNo(int oriNo) {
		this.oriNo = oriNo;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
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
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getwDate() {
		return wDate;
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
	
	
	
}
