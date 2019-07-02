package ws.com.util;

public class qq {

	public static void main(String[] args) {
		
				/*	-1 0 1  
				 * [1][2][3][4][5]	  startPage=1
				 *  3  4  [5], 6,7    startPage=3
				 *  5, 6, [7], 8,9	  startPage=5
				 *  11,12,[13],14,15  startPage=11*/				                           
				 int startPage;
				 int nowPage=1;
				 int pageCount=5; //고정 5
				 System.out.println(pageCount/2+" pageCount/2");

				 startPage = nowPage -(pageCount/2);
				 System.out.println("startPage ="+startPage);
				 if(startPage<=0) {
					 startPage = 1;
					 System.out.println("startPage<=0인경우 :"+startPage);	 
				 }
			

	}

}
