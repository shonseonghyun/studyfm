package entity;

import java.util.Date;

public class Notice {
	private int id;
	private String title;
	private String content;
	private String writer;
	private Date date;
	private String hit;
	private String file;
	private boolean pub;
	
	
	public Notice() {
		
	}
	
	public Notice(int id, String title, String writer, String content, Date date, String hit,String file) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.date = date;
		this.hit = hit;
		this.file = file;
	}
	
	public Notice(int id, String title, String writer, String content, Date date, String hit,String file,boolean pub) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.date = date;
		this.hit = hit;
		this.file = file;
		this.pub=pub;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public boolean getPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	

}
