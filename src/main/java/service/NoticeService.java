package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Notice;


public class NoticeService {
	private String url="jdbc:oracle:thin:@localhost:1521/xe";
	private String user="SON";
	private String pwd="7895";
	private String driver="oracle.jdbc.driver.OracleDriver";
	
	public int deleteNoticeAll(int[] ids) {
		int result=0;
		String params="";
		// 0 1 2  개수:3
		//10 45 88
		for(int i=0;i<ids.length;i++) {
			params+=ids[i];
			if(i!=ids.length-1) {
				params+=",";
			}
		}
		String sql="DELETE NOTICE WHERE ID in ("+params+")";
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url, user, pwd);
			Statement st=con.createStatement();
			result=st.executeUpdate(sql);
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int pubNoticeAll(int[] ids) {
		return 0;
	}
		
	public int insertNotice(Notice notice) {
		int result=0;
		String sql="INSERT INTO NOTICE(TITLE,CONTENT,WRITER_ID,PUB,FILES) VALUES(?,?,?,?,?)";
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url, user, pwd);
			PreparedStatement st=con.prepareStatement(sql);
			st.setString(1,notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getWriter());
			st.setBoolean(4, notice.getPub());
			st.setString(5,notice.getFile());
			
			result=st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;	}
	
	public int deleteNotice(int id) {
		return 0;
	}
	
	public int updateNotice(Notice notice) {
		return 0;
	}
	
	public List<Notice> getNewestNoticeList() {
		return null;
	}
	
	public List<Notice> getNoticeList(){
		
		return getNoticeList("title","",1);	
	}
	
	public List<Notice> getNoticeList(int page){
		
		return getNoticeList("title","",page);
	}
	
	public List<Notice> getNoticeList(String field,String query,int page){
		List<Notice> list=new ArrayList<>();
		int start=1+(page-1)*5;
		int end=page*5;
		
		//1,6,11 1+(n-1)5
		//5,10,15, 5*n
		String sql="SELECT  * FROM (SELECT ROWNUM RNUM,N.* FROM (SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY REGDATE DESC ) N) "
				+ "WHERE RNUM BETWEEN  ? AND ?";
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url, user, pwd);
			PreparedStatement st=con.prepareStatement(sql);
			
			st.setString(1, "%"+query+"%");
			st.setInt(2, start);
			st.setInt(3, end);
			
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				int id=rs.getInt("ID");
				String title=rs.getString("TITLE");
				String writer=rs.getString("WRITER_ID");
				String content=rs.getString("CONTENT");
				Date date=rs.getDate("REGDATE");
				String hit=rs.getString("HIT");
				String file=rs.getString("FILES");
				boolean pub=rs.getBoolean("pub");
				
				Notice notice=new Notice(id,title,writer,content,date,hit,file,pub); 
				list.add(notice);
				
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public int getNoticeCount() {
		
		return getNoticeCount("title","");
	}
	
	public int getNoticeCount(String field,String query) {
		int count=0;
		
		String sql="SELECT  COUNT(ID) COUNT FROM (SELECT ROWNUM RNUM,N.* FROM (SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY REGDATE DESC ) N) ";
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url, user, pwd);
			PreparedStatement st=con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			ResultSet rs=st.executeQuery();
			rs.next();
			count=rs.getInt("COUNT");
			
			rs.close();	
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public Notice getNotice(int id_num) {
		Notice notice=null;
		String sql="SELECT * FROM NOTICE WHERE ID=?";
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url, user, pwd);
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1,id_num );
			ResultSet rs=st.executeQuery();
			
			if(rs.next()){
				int id=rs.getInt("ID");
				String title=rs.getString("TITLE");
				String writer=rs.getString("WRITER_ID");
				String content=rs.getString("CONTENT");
				Date date=rs.getDate("REGDATE");
				String hit=rs.getString("HIT");
				String file=rs.getString("FILES");
				
				notice=new Notice(id,title,writer,content,date,hit,file); 
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}
	
	public Notice getNextNotice(int id_num) {
		Notice notice=null;
		String sql="SELECT  * FROM (SELECT ROWNUM RNUM,N.* FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC ) N) WHERE RNUM=1+"
				+ "(SELECT  RNUM FROM (SELECT ROWNUM RNUM,N.* FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC ) N) WHERE ID=?)";
		
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url, user, pwd);
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1,id_num );
			ResultSet rs=st.executeQuery();
			
			if(rs.next()){
				int id=rs.getInt("ID");
				String title=rs.getString("TITLE");
				String writer=rs.getString("WRITER_ID");
				String content=rs.getString("CONTENT");
				Date date=rs.getDate("REGDATE");
				String hit=rs.getString("HIT");
				String file=rs.getString("FILES");
				
				notice=new Notice(id,title,writer,content,date,hit,file); 
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
		
	}
	
	public Notice getPrevNotice(int id_num) {
		Notice notice=null;
		String sql="SELECT  * FROM (SELECT ROWNUM RNUM,N.* FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC ) N) WHERE RNUM=-1+"
				+ "(SELECT  RNUM FROM (SELECT ROWNUM RNUM,N.* FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC ) N) WHERE ID=?)";
		
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url, user, pwd);
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1,id_num );
			ResultSet rs=st.executeQuery();
			
			if(rs.next()){
				int id=rs.getInt("ID");
				String title=rs.getString("TITLE");
				String writer=rs.getString("WRITER_ID");
				String content=rs.getString("CONTENT");
				Date date=rs.getDate("REGDATE");
				String hit=rs.getString("HIT");
				String file=rs.getString("FILES");
				
				notice=new Notice(id,title,writer,content,date,hit,file); 
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}
	
}
	