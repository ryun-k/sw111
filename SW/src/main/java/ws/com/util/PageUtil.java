package ws.com.util;

/* �� Ŭ������ ������ ���� ����� ó���ϱ� ���ؼ�
 * ȭ�鿡 ����� ������ �̵� ��� ����� ���� Ŭ����
 * �ʿ��� ������ ����ϰ� �����ϴ� ���
 *  
 * [1][2][3][4][5]
 * [2][3][4][5][6] �� ������ε� ���� �� �ִ�
 * 				   nowPage�� ��� �ΰ�
 * 					�������� ������ ����ŭ ������������ ������������ ��ġ�� �Ǵ� ���
 * private int nowPage;		//���� ���� ���� ������
 * private int totalCount;	//�� ������ ����   			104
 * 
 * private int listCount;	//�� �������� ��Ÿ�� �Խù� ��		3
 * private int pageCount;	//�� �������� ��Ÿ�� ������ �̵� ���� 5
 * 
 * 
 * private int totalPage;	//�� ������ ��
 * private int startPage;//ȭ�鿡 ǥ���� ����  ��������
 * private int endPage; //ȭ�鿡 ǥ���� ������ �������� 
 * 		�� ������ ���� /�� �������� ��Ÿ�� �Խù� ��
 * 		totalPage=totalCount/listCount	�������� 0
 * 		20<=100/5   
 * 		21	101/5   1 2 3 4 5 => �������� 0�� �ƴѰ�쿡�� 1�������� �߰�
 * 		21	104/5
 * 	
 *  (����)?���ΰ��:�������
 *  totalPage=(�������� 0)?totalCount/listCount: (totalCount/listCount)+1;
 * 		
 */
public class PageUtil {

	 //�ʼ�����(�����ڰ� �˷���� �ϴ� ����)
	 private int nowPage;		//���� ���� ���� ������
	 private int totalCount;	//�� ������ ����   			104
	  
	 private int listCount;	//�� �������� ��Ÿ�� �Խù� ��		5
	 private int pageCount;	//�� �������� ��Ÿ�� ������ �̵� ����
	  
	 //��꺯��
	 private int totalPage;	//�� ������ ��
	 private int startPage;//ȭ�鿡 ǥ���� ����  ��������
	 private int endPage; //ȭ�鿡 ǥ���� ������ �������� 
	 // 		�� ������ ���� /�� �������� ��Ÿ�� �Խù� ��	
	 
	 //�����ڰ� �˷���� �ϴ� 2���� �����ʹ� �������Լ��� �̿��ؼ� �޵����ϰڴ�
	 //��) PageUtil info = new PageUtil(1,10);
	 public PageUtil(int np,int tc){
		 this(np, tc,3, 5);
		
		/* this.nowPage = np;
		 this.totalCount = tc;
		 
		 //�ʼ��������� ������ �����ʹ� ������ �������ִ� ���
		 this.listCount = 3; //�� �������� ��Ÿ�� �Խù� ��		3
		 this.pageCount = 5; //�� �������� ��Ÿ�� ������ �̵� ����
		 */
	 }
	 
	 //������ �Լ��� �̿��ؼ� ���������� ����
	//��) PageUtil info = new PageUtil(1,10,10,5);
	 public PageUtil(int np, int tc, int lc, int pc){
		 this.nowPage = np;
		 this.totalCount = tc;
		 this.listCount = lc;
		 this.pageCount = pc;
		 
		 //new�� ��Ű�� ���� �ڵ����� ���ǰ� ����
		 calcTotalPage(); //�� ������ ��
		 calcStartPage(); //ȭ�鿡 ǥ���� ����  ��������
		 calcEndPage();	  //ȭ�鿡 ǥ���� ������ ��������  
	 }
	 
	 //�ʿ��� ����� ����
	 //�� ������ ��
	 public void calcTotalPage() {		
		 totalPage=(totalCount%listCount==0)?(totalCount/listCount):
			 		(totalCount/listCount)+1;
	 }
	 
	 //ȭ�鿡 ǥ���� ����  ��������
	 public void calcStartPage() {
		/*	[1][2][3][4][5]	  startPage=1
		 *  3  4  [5], 6,7    startPage=3
		 *  5, 6, [7], 8,9	  startPage=5
		 *  11,12,[13],14,15  startPage=11*/	
		 //System.out.println(pageCount/2+" pageCount/2");
		 startPage = nowPage -(pageCount/2);
		 //nowPage�� 2�� ��쿡��  ���� startPage�� 0
		 //nowPage�� 1�� ��쿡�� ���� startPage�� -1�����µ�
		 //startPage�� 0�Ǵ� -1�� ������ �ȵǹǷ�
		 if(startPage<=0) {  
			 startPage = 1;  //startPage�� 1�� �ɼ��ֵ��� ����
		 }		 
	 }

	 //ȭ�鿡 ǥ���� ������ �������� 
	 public void calcEndPage() {
		 // ���������� +�� �������� ��Ÿ�� ������ �̵� ����-1
		 // 1 2 3 4 5 => ���������� �Ѹ� �Խù����� 5��� �ѰԽù� ���� 25
		 // 6 7     10     endPage�� 10�� �Ǿ�� �ϴµ�
		 // ������ ȭ�鿡��(��������������)    �� ���������� �Ѿ ������ ������ �ʾƵ� �ȴ�             
		 endPage = startPage+pageCount-1;
		 if(endPage>=totalPage) { //������������>=����������
			 endPage = totalPage;
		 }
	 }

	 
	//���� �κ��� ����Ǹ鼭  ���� �����ʹ�  �信�� ����ϱ����� ���� �����͵��̴�.
	//�信�� ����� �����ʹ� getXxx()�� �����ؾ����� �̿��� �� �ִ�
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









