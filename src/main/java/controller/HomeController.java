package controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.Notice;
import service.NoticeService;

@Controller
public class HomeController {
	
	@Autowired
	private NoticeService noticeservice;
	
	@RequestMapping("/index")
	public String index() {
		System.out.println("index");
//		PrintWriter out;
//		try {
//			out = response.getWriter();
//			out.println("hello");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return "index";
	}
		
	@RequestMapping("/notice/list")
	public String noticelist(Model model) {
		System.out.println("noticelist");
		List<Notice> list=noticeservice.getNoticeList();
		model.addAttribute("list", list);
		return "notice.list";
	}
	
	@RequestMapping("/notice/detail")
	public String noticedetail() {
		System.out.println("detail");
		return "notice.detail";
	}
	
	//이것도 요청
//	@Override
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView mv=new ModelAndView();
//		//이것도 요청
////		mv.addObject("data", "Hello mvc~");
//		mv.setViewName("index");
//		return mv;
//	}

}
