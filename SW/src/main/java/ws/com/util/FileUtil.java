package ws.com.util;

import java.io.File;

//�� Ŭ������ ���Ͼ��ε忡 ���õ� ����� ���� Ŭ����
public class FileUtil {

	//���� ������ ���ٸ� �ڵ����� ������ �������ٲ�	
	private static void makeFoler(String path) {
		//���õ� path�� �̿��Ͽ� FileŬ������ �����
		File file = new File(path);

		//�� ��θ� �̿��Ͽ� ������ ����
		file.mkdirs();
	}
	
	
	//���� ������ �̸��� ���� ������ �����ϸ�
	//���� ������ �̸��� �ٲپ������ν�
	//����Ⱑ ���� �ʵ��� �����ϱ� ���� �Լ�
	public static String renameTo(String path, String oriName) {
		
		makeFoler(path);
		
		//������ ���鶧���� ���(����)�� ���� �̸����� ��������� �����ؾ� �Ѵ�
		//new File(���, �����̸�);
		//a.txt -> a_1.txt
		String tempName = oriName; //���� �̸�
		int		count = 0;
		
		//���� ������ ������ �̸��� FileŬ������ �ٲٰ�
		File file = new File(path, tempName);
		
		//�̷� ���������ϴ�?
		while(file.exists()) {
			//�׷��ٸ� �̸��� �ٲ���			
			//.�� �������� ���� �κ� ����
			int index = oriName.lastIndexOf(".");
			String fileN = oriName.substring(0, index); //0���� index-1���� ����
			String extN  = oriName.substring(index+1); //.�ں��� ���ڿ��� ������ ����
			
			//���� ��ȣ ����
			count = count + 1;
			
			//�����̸��� _��ȣ�� ���̱�
			fileN = fileN+"_"+count;
			
			//�ٿ��� �̸��ڿ� Ȯ���ڸ� �ٿ����������� ������ �����̸��� �ٽ� �����
			tempName = fileN+"."+extN;
			
			file = new File(path, tempName);
		}
		
		return tempName;
	}

	
}






