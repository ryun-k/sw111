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

/* �� Ŭ������ ��Ʈ�� �������  
 * ������ ������  Ŭ���̾�Ʈ���� ������ �������� ����� Ŭ����
 * 
 * ���������� �ٿ�ε带 ���� Ŭ������ �����
 * ����
 * 1. �ݵ�� AbstractViewŬ������ ��ӹ޾ƾ� �Ѵ� => �� ��Ű�� �Ҽ�
 * 2. �ݵ�� renderMergedOutputModel()�޼ҵ带  �������̵��ؾ� �Ѵ�
 */

public class DownloadUtil extends AbstractView{

	//�������Լ��� �̿��ؼ�
	//�� Ŭ������ new�� �Ǹ�  �������� ��Ʈ��������� �����ϵ��� �� ����
	public DownloadUtil() {
		this.setContentType("application/download;charset=UTF-8");
	}
	
	//�� �Լ��� ���������� ��Ʈ���������   
	//������ Ŭ���̾�Ʈ���� ������ �۾��� ����Լ�
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//�Ķ���� model => ��Ʈ�ѷ��� �����ϴ�  �ٿ�ε� ���Ͽ� ���� ������ ����� ����
		//�츮������ ���
		// ��Ʈ�ѷ�������   downloadFile  �̶�� Ű������ ������ ������ �˷��ֱ�� ����
		//            ����������               FileŬ������ �Ѱܹ�����
		//File file = (File)model.get("downloadFile");--1�����
		
		//      ����������               FileBoardVOŬ������ �Ѱܹ�����
		FileBoardVO vo = (FileBoardVO)model.get("downloadFile");//--2�����
		
		//�ٿ�ε� ���� ȯ�漳�� ----------------------------
		//�������� ��Ʈ���������  ��������
		response.setContentType(this.getContentType());
		
		//���������� ũ�� �˷��ֱ�
		//������ ũ��� longŸ������ ���ϵ�����  �Ʒ��Լ��� �Ķ���������� int�̹Ƿ� ����ȯ
		//response.setContentLength( (int)file.length());--1�����	
		
		response.setContentLength( (int)vo.getLen());//--2�����
		
		//�ٿ�ε������ ������ �̸������ϱ�
		//Ŭ���̾�Ʈ�� �� ������ �̸��� ���� �ٿ�ε忩�θ� �����ϰ� �ȴ�
		//������ �����̸��� �ѱ��̸� ���������� �ִ�
		//��� ���ϸ� �ѱ��� �����ϸ� encodingó���� �ؾ� �Ѵ�
		//String encoding=new String(file.getName().getBytes("UTF-8"), "8859_1");--1�����

		String encoding=new String(vo.getOriName().getBytes("UTF-8"), "8859_1");//--2�����
		
		response.setHeader("Content-Disposition", "attachment;filename="+encoding);
		
		//��Ʈ�� ������� �ٿ�ε带 ����------------------------
		// ������ ������ �о..
		FileInputStream fin = null;
		OutputStream    fout = null;
		
		//vo�� �̿��ؼ� �۾��ÿ��� file��ü�� ���� inputStream()���� �ڷ�����
		File file = new File(vo.getPath(),vo.getOriName());//--2�����
		fin = new FileInputStream(file);//--1�����//--2����� �����ڵ�
		fout = response.getOutputStream(); 
		
		//������������   ���ʿ��� ���� ������  �ٸ��ʿ� ���� �κ��� �Լ��� �����ϰ� �ִ�
		//����Լ�        FileCopyUtils.copy(InputStream��ü,OutputStream��ü);
		try {
			FileCopyUtils.copy(fin,fout);
		} catch (Exception e) {
			System.out.println("�ٿ�ε� ó������ ����="+e);
		}
		finally {
			fout.flush();
			fin.close();
			fout.close();
		}
		
		
	}

}







