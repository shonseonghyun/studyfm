package service;

import java.util.List;

import entity.Notice;

public interface Service {
	public int deleteNoticeAll(int[] ids);
	public int pubNoticeAll(int[] ids);
	public int insertNotice(Notice notice);
	public int deleteNotice(int id);
	public int updateNotice(Notice notice);
	
	public List<Notice> getNewestNoticeList();
	public List<Notice> getNoticeList();
	public List<Notice> getNoticeList(int page);
	public List<Notice> getNoticeList(String field,String query,int page);
	
	public int getNoticeCount();
	public int getNoticeCount(String field,String query);
	public Notice getNotice(int id_num);
	public Notice getNextNotice(int id_num);
	public Notice getPrevNotice(int id_num);
}
