package controller.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import entity.Notice;
import service.NoticeService;

public class ListController implements Controller{
	
	private NoticeService noticeservice;
	
	public void setNoticeService(NoticeService noticeservice) {
		this.noticeservice = noticeservice;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Notice> list=noticeservice.getNoticeList("TITLE", "", 1);
		
		ModelAndView mv = new ModelAndView("notice.list");
		mv.addObject("list",list);
		return mv;
	}
}
