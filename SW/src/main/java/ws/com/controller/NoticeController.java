package ws.com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ws.com.service.NoticeService;
import ws.com.util.PageUtil;
import ws.com.vo.NoticeVo;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService nService;
	
	
	//공지사항 리스트
	@RequestMapping("/List")
	public void noticeList(@RequestParam(value="nowPage", required=false, 	defaultValue="1") int nowPage,
			HttpServletRequest request, HttpSession session
		) {
		PageUtil pInfo = nService.getPageInfo(nowPage,session);
		
		ArrayList list = nService.getBoardList(pInfo,session);
		
		request.setAttribute("LIST", list); //게시물내용
		request.setAttribute("PINFO", pInfo);//페이징처리
		
	}
	
	//공지사항 상세보기
	@RequestMapping("/View")
	public ModelAndView noticeView(@RequestParam(value="oriNo") int oriNo, @RequestParam(value="nowPage") int nowPage) {
		System.out.println("noticeView진입");
		System.out.println("oriNo "+oriNo);
		NoticeVo vo = nService.viewNotice(oriNo);
		System.out.println("viewNotice 서비스종료");
		ModelAndView mv = new ModelAndView();
		mv.addObject("VIEW",vo);
		mv.addObject("nowPage",nowPage);
		mv.setViewName("notice/View");
		
		return mv;
	}
	
	//공지사항 글쓰기폼
	@RequestMapping("/writeForm")
	public void noticeWriteForm() {
		
	}
	
	//공지사항 글쓰기 처리
	@RequestMapping("/writeProc")
	public ModelAndView noticeWriteProc(NoticeVo vo,ModelAndView mv) {
		System.out.println("writeProc 진입");
		nService.insertNotice(vo);
		System.out.println("nService종료");
		
		RedirectView rv = new RedirectView("../notice/List.do");
		mv.setView(rv);
		return mv;
	}
	
	//조회수 처리
	@RequestMapping("/hitProc")
	public ModelAndView	noticeHitProc(ModelAndView mv, HttpServletRequest req, HttpSession session) {
		String  strOriNo= req.getParameter("oriNo");
		int     oriNo = Integer.parseInt(strOriNo);
		String  nowPage = req.getParameter("nowPage"); 
		nService.hitNotice(oriNo,session);
		RedirectView rv = new RedirectView("../notice/View.do");
		
		rv.addStaticAttribute("oriNo", oriNo);
		rv.addStaticAttribute("nowPage", nowPage);
		mv.setView(rv);
		return mv;
	}
	
	//공지사항 수정 폼
	@RequestMapping("/modifyForm")
	public ModelAndView noticeModifyForm(HttpServletRequest req, ModelAndView mv) {
		String strOriNo = req.getParameter("oriNo");
		int    oriNo    = Integer.parseInt(strOriNo);
		String nowPage  = req.getParameter("nowPage");
		System.out.println("modifyForm oriNo "+oriNo);
		NoticeVo vo = nService.modifyNotice(oriNo);
		
		mv.addObject("DATA",vo);
		mv.addObject("nowPage",nowPage);
		mv.setViewName("notice/modifyForm");
		return mv;
		
	}
	
	//공지사항 글쓰기 처리
	@RequestMapping("/modifyProc")
	public ModelAndView noticeModifyProc(HttpServletRequest req, ModelAndView mv, NoticeVo vo) {
		String strOriNo = req.getParameter("oriNo");
		int    oriNo    = Integer.parseInt(strOriNo);
		String nowPage  = req.getParameter("nowPage");
		nService.updateNotice(vo, oriNo);
		
		RedirectView rv = new RedirectView("../notice/List.do");
		rv.addStaticAttribute("nowPage", nowPage);
		mv.setView(rv);
		return mv;
		
	}
	
	//글삭제 처리
	@RequestMapping("/delete")
	public ModelAndView noticeDeleteProc(HttpServletRequest req, ModelAndView mv){
		String strOriNo = req.getParameter("oriNo");
		int    oriNo    = Integer.parseInt(strOriNo);
		String nowPage  = req.getParameter("nowPage");
		nService.deleteNotice(oriNo);
		
		RedirectView rv = new RedirectView("../notice/List.do");
		rv.addStaticAttribute("nowPage", nowPage);
		mv.setView(rv);
		return mv;
	}
	
}
