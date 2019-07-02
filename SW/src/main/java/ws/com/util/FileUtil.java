package ws.com.util;

import java.io.File;

//이 클래스는 파일업로드에 관련된 기능을 가진 클래스
public class FileUtil {

	//만약 폴더가 없다면 자동으로 폴더를 생성해줄께	
	private static void makeFoler(String path) {
		//제시된 path를 이용하여 File클래스를 만들고
		File file = new File(path);

		//그 경로를 이용하여 폴더를 생성
		file.mkdirs();
	}
	
	
	//만약 동일한 이름을 가진 파일이 존재하면
	//현재 파일의 이름을 바꾸어줌으로써
	//덮어쓰기가 되지 않도록 방지하기 위한 함수
	public static String renameTo(String path, String oriName) {
		
		makeFoler(path);
		
		//파일을 만들때에는 어디(폴더)에 무슨 이름으로 만들것인지 지정해야 한다
		//new File(어디에, 무슨이름);
		//a.txt -> a_1.txt
		String tempName = oriName; //현재 이름
		int		count = 0;
		
		//현재 저장할 파일의 이름을 File클래스로 바꾸고
		File file = new File(path, tempName);
		
		//이런 파일존재하니?
		while(file.exists()) {
			//그렇다면 이름을 바꾸자			
			//.을 기준으로 앞의 부분 추출
			int index = oriName.lastIndexOf(".");
			String fileN = oriName.substring(0, index); //0부터 index-1까지 추출
			String extN  = oriName.substring(index+1); //.뒤부터 문자열의 끝까지 추출
			
			//붙일 번호 증가
			count = count + 1;
			
			//파일이름에 _번호를 붙이기
			fileN = fileN+"_"+count;
			
			//붙여진 이름뒤에 확장자를 붙여줌으로인해 온전한 파일이름을 다시 만든다
			tempName = fileN+"."+extN;
			
			file = new File(path, tempName);
		}
		
		return tempName;
	}

	
}






