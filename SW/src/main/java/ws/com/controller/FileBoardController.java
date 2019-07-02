package ws.com.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ws.com.service.FileBoardService;
import ws.com.util.FileUtil;
import ws.com.util.PageUtil;
import ws.com.vo.FileBoardVO;

@Controller
@RequestMapping("/fileBoard") //�ش���Ʈ�ѷ��� ��� ��û�� /fileBoard�� �����
public class FileBoardController {
	@Autowired
	private FileBoardService fService;
	
	//��ü ��Ϻ���
	@RequestMapping("/boardList")
	public void fileBoardList(
		@RequestParam(value="nowPage", required=false, 	defaultValue="1") int nowPage,
			HttpServletRequest request
		) {
		//1. �Ķ���� ��û
		// user�� ������� �������� �˾Ƴ��� => @RequestParam�̿�
		/*���� �Ķ���Ͱ� �Ѿ���� ���� ���   �ٸ� ������ ��ü�� �� �ִ�	
		����>@RequestParam(value="Ű��", required=false, defaultValue=?) ������Ÿ�� ������*/			
		
		//2.����Ͻ��������� ->��������
		
			//2-1.����¡ó���� �ʿ��� �ڷ�
			PageUtil pInfo = fService.getPageInfo(nowPage);
		
			//2-2.��Ͽ� �Ѹ� �ڷ�
			ArrayList list = fService.getBoardList(pInfo);
		
		//3.��    ������ �信�� �����ؾ��Ѵ�
		request.setAttribute("LIST", list); //�Խù�����
		request.setAttribute("PINFO", pInfo);//����¡ó��
		
		//4.�� => ��Ϻ��� ������ ������
	}
	
	//�۾���-1) ������
	@RequestMapping("/writeForm")
	public String fileWriteForm() {
		// �α��� ó��=> ���ͼ��ͼ���
		return "fileBoard/writeForm";
	}
	
	
	//�۾���-2) �۾���
	@RequestMapping("/writeProc")
	public ModelAndView fileWriteProc(FileBoardVO vo,
			HttpSession session/*String id*/, 
			ModelAndView mv){
		System.out.println("fileWriteProc()��Ʈ�ѷ��Լ� ȣ�⼺��");
		//1.�Ķ���� �ޱ�
		
		//2.����Ͻ������� ��������
		/*������ �����Ͱ� ���۵Ǹ� ���ε� ������ �ӽ� ������ �ڵ� ����ȴ�.
		  �ش� ��Ʈ�ѷ� ������ ������ ��  ������ �ڵ� �����ȴ�.*/
		//������ ������ �˾Ƴ���
		
		//������ ���鶧���� ���(����)�� ���� �̸����� ��������� �����ؾ� �Ѵ�
		//new File(���, �����̸�);
		//��������   �ٿ�ε��ϰ�쿡�� ���ϴ� ������ ����
		//����      ��������  ���࿡ web���� ������ �����̶�� getRealPath()�� �̿��ؼ� ������ �Ѵ�
		
		
		/*�˾Ƴ� ���������� �����Ͽ� �ӽ� ����� ������ �ӽ��������� �����ؼ�*/
		String path = "D:\\upload";
		//String path = "D:\\test\\upload";
		
		//���ε�� ������ �������ϰ�쿡��
		//������ ������ �迭�� ���ǹǷ�  �迭�� �̿��ؼ� �� ������ ������ �޾ƾ� �Ѵ�
		
		//������ ������ �ϳ��� ���� ���� ����
		ArrayList list = new ArrayList();
		
		for( int i=0; i<vo.getFiles().length ;i++) {
			//�����̸��� �˾Ƴ���
			String oriName = vo.getFiles()[i].getOriginalFilename();
			
			//������ ���ε� ���� ���� ���   ���� ������ ���ε��ض�..
			if( oriName==null || oriName.length()==0 ) {
				continue;
			}
			
			String saveName = FileUtil.renameTo(path, oriName);
			
			try {
				File file = new File(path, saveName);
		
			   	//������ Ư�������� ���� ���ε�(����)���� ��� �Ѵ�
				//.transferTo()�� �̿��ؼ� ����
				vo.getFiles()[i].transferTo(file);
			
				//���� �ڵ������ �ϳ��� ������ ���ε�� �����̴�
				//���ε�� ������ ������ Map()���� ����
				HashMap map = new HashMap();
				map.put("path", path);	//����� ���
				map.put("oriName", oriName);//�����̸�
				map.put("saveName", saveName);//������̸�
				map.put("len", file.length());//����ũ��
			
				list.add(map);
				
			} catch (Exception e) {
				System.out.println("���Ϻ��翡�� = " + e);
			}
			
		}//for
		System.out.println("��Ʈ�ѷ� list����="+list.size());
		
		
		System.out.println("insert������ �� no="+vo.getNo());
		fService.inserBoard(vo, session,  list);
		System.out.println("insert������ �� no="+vo.getNo());
		
		//3.��
		//4.��: �����̷�Ʈ : ��Ϻ���
		RedirectView rv = new RedirectView("../fileBoard/boardList.do");
	    //�ʿ�� �����̷�Ʈ�信�� �Ķ���͸� �߰��Ͽ� ���� �� �ִ�
		//rv.addStaticAttribute(name, value);
		                       //("downloadFile", new File(~,~))~~~~~~~~~~~~   
		
		
		
		mv.setView(rv);
		return mv;
	}
	  
	
	
	//3.�󼼺���-1)��ȸ�� ����     ��û�Լ�
	@RequestMapping("/hitProc")
	public ModelAndView	boardHitProc(ModelAndView mv,
			HttpServletRequest req,HttpSession session) {
		//1.�Ķ���� �ޱ�
		String  strOriNo= req.getParameter("oriNo");
		int     oriNo = Integer.parseInt(strOriNo);
		String  nowPage = req.getParameter("nowPage"); //�����̿�
		//2.����Ͻ����� ->���񽺿��� ����
		fService.updateHit(oriNo, session);
		//3.��
		//4.��
		RedirectView rv = new RedirectView("../fileBoard/boardView.do");
		//�ʿ�� �����̷�Ʈ�信�� �Ķ���͸� �߰��Ͽ� ���� �� �ִ�
		rv.addStaticAttribute("oriNo", oriNo);
		rv.addStaticAttribute("nowPage", nowPage);
		mv.setView(rv);
		return mv;
	}
	
	
	//3.�󼼺���-2)�󼼺���   ��û�Լ�
	@RequestMapping("/boardView")
	public ModelAndView boardView(@RequestParam(value="oriNo") int oriNo,
			@RequestParam(value="nowPage") int nowPage) {
		//1.�Ķ���� �ޱ�
		//2.����Ͻ����� ->���񽺿��� ����
		//���ϰԽù�����
		FileBoardVO  vo = fService.getBoardView(oriNo);
		
		//÷���������������� ����
		ArrayList list = fService.getFileInfo(oriNo);
		//System.out.println("list.size()="+list.size());
		
		//3.�� & 4.��
		ModelAndView mv = new ModelAndView();
		mv.addObject("VIEW",vo);//���� � ����� �������� ����..
		mv.addObject("LIST",list);//÷�����ϸ��
		mv.addObject("nowPage",nowPage); //�����̿�
		
		
		/*HashMap map = new HashMap();
		map.put("VIEW", vo);
		map.put("LIST", list);
		mv.addAllObjects(map);*/
		mv.setViewName("/fileBoard/boardView");
		return mv;
	}
	
	//���� �ٿ�ε� ��û�Լ�    
	@RequestMapping("/fileDownload")
	public ModelAndView fileDownload(@RequestParam(value="fileNo") int fileNo) {
		//1.�Ķ���� ��
	
		//2.����Ͻ����� -> ��������(�ش����Ϲ�ȣ�� �̿��ؼ� fileInfo����  ��ȸ)
		//�ٿ�ε� ���� ���� �˻� ��û 
		FileBoardVO vo = fService.getDownloadFile(fileNo);
		
		/*��Ʈ�ѷ������� <bean> ó���� �ٿ�ε� Ŭ������ 
		   ModelAndView Ŭ������ ����� �����ν�       �ٿ�ε尡 �̷������ �ȴ�.*/
		// ����� �ٿ�ε� �並 ȣ���ϱ�
		// ���� ModelAndView mv = new ModelAndView("����ڴٿ�ε���","�����ҵ�����Ű��",�����ҵ�����);
		//xml�������� ����ں並 �Ʒ��� ���� ����صξ���
		//<beans:bean id="download" class="ws.com.util.DownloadUtil"/>
		//�츮�� �ٿ�ε��� ������
		//downloadFile  �̶�� Ű������ ������ ������ �˷��ְ� ,  FileŬ������ �Ѱ��ֱ�� �Ѵٸ� --1�����
		//FileBoardVO�� �̿��ؼ� ���ϰ�ü�� ������
		//File file = new File(vo.getPath(),vo.getSaveName());//--2�����
		
		//�����͸� File�� �ѱ涧--1�����
		//ModelAndView mv = new ModelAndView("download","downloadFile",file);--1�����
		
		//�����͸� vo�� �ѱ涧
		ModelAndView mv = new ModelAndView("download","downloadFile",vo);//--2�����
		//����������  �����ҵ�����Ű���� �̿��Ͽ�  �����ҵ����Ͱ� Map���� ���������  �信�� ���޵ȴ�
		/* Map map = new Map();
		 * map.put("downloadFile",vo);
		 */
		
		return mv;
	}
	
	
	
	//4.�����ϱ�-1)������
	@RequestMapping("/modifyForm")
	public ModelAndView  modifyForm(HttpServletRequest request, ModelAndView mv) {
		//1.�Ķ���͹ް�
		String strOriNo = request.getParameter("oriNo");
		int    oriNo    = Integer.parseInt(strOriNo);
		String nowPage  = request.getParameter("nowPage");//�����̿�
		
		//2.���� 
		//���ϰԽù�����
		FileBoardVO  vo = fService.getBoardView(oriNo); //���񽺸� ����ϴ� ����
		
		//3.��
		mv.addObject("VIEW",vo);//���� � ����� �������� ����..
		mv.addObject("nowPage",nowPage);//�����̿�
		//4.��
		/*����	mv.setView("�����̷�Ʈ���");
				mv.setViewName("�Ϲ� ��");*/
		mv.setViewName("fileBoard/modifyForm");	
		return mv;
	}
	
	//4.�����ϱ�-2)�����ϱ�
	@RequestMapping("/modifyProc")
	public ModelAndView modifyProc(FileBoardVO vo,ModelAndView mv, RedirectView rv) {
		//1.�Ķ���͹ް�
		System.out.println("vo.getOriNo()="+vo.getOriNo());
		System.out.println("vo.getNowPage()="+vo.getNowPage());
		System.out.println("vo.getPw()="+vo.getPw());
	/*	�Ķ����	oriNo=
				nowPage=(�����̿�)
				title
				body
				pw*/
		//2.����Ͻ�����
		//	2-1) ����,����,����� update
		//	2-2) ÷�������� ������ ���ε�(÷�����Ͼ����� x)
		//÷������
		//3.��
		//4.��
		//��				Redirect View : �󼼺���
		rv.setUrl("../fileBoard/boardView.do");
		mv.setView(rv);
		return mv;
	}
	
}











