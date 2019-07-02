package ws.com.util;

/* 이 클래스는 페이지 나눔 기능을 처리하기 위해서
 * 화면에 출력할 페이지 이동 기능 만들기 위한 클래스
 * 필요한 정보를 계산하고 제공하는 기능
 *  
 * [1][2][3][4][5]
 * [2][3][4][5][6] 의 모습으로도 보일 수 있다
 * 				   nowPage를 가운데 두고
 * 					양쪽으로 정당한 량만큼 이전페이지와 다음페이지가 배치가 되는 방식
 * private int nowPage;		//현재 보고 싶은 페이지
 * private int totalCount;	//총 데이터 개수   			104
 * 
 * private int listCount;	//한 페이지에 나타날 게시물 수		3
 * private int pageCount;	//한 페이지에 나타날 페이지 이동 개수 5
 * 
 * 
 * private int totalPage;	//총 페이지 수
 * private int startPage;//화면에 표시할 시작  페이지수
 * private int endPage; //화면에 표시할 마지막 페이지수 
 * 		총 데이터 개수 /한 페이지에 나타날 게시물 수
 * 		totalPage=totalCount/listCount	나머지가 0
 * 		20<=100/5   
 * 		21	101/5   1 2 3 4 5 => 나머지가 0이 아닌경우에는 1페이지를 추가
 * 		21	104/5
 * 	
 *  (조건)?참인경우:거짓경우
 *  totalPage=(나머지가 0)?totalCount/listCount: (totalCount/listCount)+1;
 * 		
 */
public class PageUtil {

	 //필수변수(개발자가 알려줘야 하는 변수)
	 private int nowPage;		//현재 보고 싶은 페이지
	 private int totalCount;	//총 데이터 개수   			104
	  
	 private int listCount;	//한 페이지에 나타날 게시물 수		5
	 private int pageCount;	//한 페이지에 나타날 페이지 이동 개수
	  
	 //계산변수
	 private int totalPage;	//총 페이지 수
	 private int startPage;//화면에 표시할 시작  페이지수
	 private int endPage; //화면에 표시할 마지막 페이지수 
	 // 		총 데이터 개수 /한 페이지에 나타날 게시물 수	
	 
	 //개발자가 알려줘야 하는 2개의 데이터는 생성자함수를 이용해서 받도록하겠다
	 //예) PageUtil info = new PageUtil(1,10);
	 public PageUtil(int np,int tc){
		 this(np, tc,3, 5);
		
		/* this.nowPage = np;
		 this.totalCount = tc;
		 
		 //필수적이지만 부족한 데이터는 강제로 지정해주는 경우
		 this.listCount = 3; //한 페이지에 나타날 게시물 수		3
		 this.pageCount = 5; //한 페이지에 나타날 페이지 이동 개수
		 */
	 }
	 
	 //생성자 함수를 이용해서 유동적으로 지정
	//예) PageUtil info = new PageUtil(1,10,10,5);
	 public PageUtil(int np, int tc, int lc, int pc){
		 this.nowPage = np;
		 this.totalCount = tc;
		 this.listCount = lc;
		 this.pageCount = pc;
		 
		 //new를 시키는 순간 자동으로 계산되게 하자
		 calcTotalPage(); //총 페이지 수
		 calcStartPage(); //화면에 표시할 시작  페이지수
		 calcEndPage();	  //화면에 표시할 마지막 페이지수  
	 }
	 
	 //필요한 계산을 하자
	 //총 페이지 수
	 public void calcTotalPage() {		
		 totalPage=(totalCount%listCount==0)?(totalCount/listCount):
			 		(totalCount/listCount)+1;
	 }
	 
	 //화면에 표시할 시작  페이지수
	 public void calcStartPage() {
		/*	[1][2][3][4][5]	  startPage=1
		 *  3  4  [5], 6,7    startPage=3
		 *  5, 6, [7], 8,9	  startPage=5
		 *  11,12,[13],14,15  startPage=11*/	
		 //System.out.println(pageCount/2+" pageCount/2");
		 startPage = nowPage -(pageCount/2);
		 //nowPage가 2인 경우에는  계산된 startPage가 0
		 //nowPage가 1인 경우에는 계산된 startPage가 -1나오는데
		 //startPage가 0또는 -1이 나오면 안되므로
		 if(startPage<=0) {  
			 startPage = 1;  //startPage가 1로 될수있도록 조정
		 }		 
	 }

	 //화면에 표시할 마지막 페이지수 
	 public void calcEndPage() {
		 // 시작페이지 +한 페이지에 나타날 페이지 이동 개수-1
		 // 1 2 3 4 5 => 한페이지에 뿌릴 게시물수가 5라면 총게시물 수는 25
		 // 6 7     10     endPage는 10이 되어야 하는데
		 // 마지막 화면에는(마지막페이지가)    총 페이지수를 넘어간 내용은 만들지 않아도 된다             
		 endPage = startPage+pageCount-1;
		 if(endPage>=totalPage) { //마지막페이지>=총페이지수
			 endPage = totalPage;
		 }
	 }

	 
	//위의 부분이 진행되면서  계산된 데이터는  뷰에서 사용하기위해 만든 데이터들이다.
	//뷰에서 사용할 데이터는 getXxx()가 존재해야지만 이용할 수 있다
	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	 
	
}









