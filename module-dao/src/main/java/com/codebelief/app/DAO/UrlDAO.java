package com.codebelief.app.DAO;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.codebelief.app.VO.Url;
//import com.mysql.jdbc.Statement;
import com.mysql.jdbc.Statement;

/**
 * 
 * @ClassName: UrlDAO
 * @Description: Define Some Concrete Operations For Url Table
 * @author ����
 * @date 2017��10��14��
 *
 */
public class UrlDAO implements IUrlDAO{
	private Connection conn = null;
	private PreparedStatement ps = null;
	
	public UrlDAO(Connection conn){
		this.conn = conn;
	}
	
	@Override
	/**
	 * @Title: doInsert
	 * @Description: Insert a piece of Url Info
	 * @param url
	 * @return boolean
	 * @throws Exception
	 */
	public int doInsert(String UserName, 
			String title,
			String url,
			boolean enabled,
			boolean realTimePush) throws Exception {
		String query = "insert into Url (UserName, Title, Url, Enabled, RealTimePush) values(?,?,?,?,?)";
		ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, UserName);
		ps.setString(2, title);
		ps.setString(3, url);
		ps.setBoolean(4, enabled);
		ps.setBoolean(5, realTimePush);
		if(ps.executeUpdate() == 0){
			ps.close();
			return -1;
		}
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		int urlID = rs.getInt(1);
		rs.close(); ps.close();
		return urlID;
	}

	@Override
	/**
	 * @Title: doDelete
	 * @Description: Delete a piece of Url Info
	 * @param UrlID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doDelete(int UrlID) throws Exception {
		String query = "delete from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
	}

	@Override
	/**
	 * @Title: doUpdate
	 * @Description: Update the Url,Enabled,RealTimePush of a piece of Url Info
	 * @param UrlID
	 * @param url
	 * @param Enabled
	 * @param RealTimePush
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doUpdate(Url urlInstance) throws Exception {
		String title = urlInstance.getTitle();
		String url = urlInstance.getUrl();
		boolean Enabled = urlInstance.isEnabled();
		boolean RealTimePush = urlInstance.isRealTimePush();
		int UrlID = urlInstance.getUrlID();
		String query = "update Url set Title=?,Url=?,Enabled=?,RealTimePush=? where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, title);
		ps.setString(2, url);
		ps.setBoolean(3, Enabled);
		ps.setBoolean(4, RealTimePush);
		ps.setInt(5, UrlID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return false;
	}

	@Override
	/**
	 * 
	 * @Title: doUpdateTitle
	 * @Description: update the Title singly
	 * @param UrlID
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateTitle(int urlID, String title) throws Exception {
		String query =  "update Url set Title=? where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, title);
		ps.setInt(2, urlID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
	}

	@Override
	/**
	 * 
	 * @Title: doUpdateUrl
	 * @Description: update the Url singly
	 * @param UrlID
	 * @param url
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateUrl(int urlID, String url) throws SQLException {
		String query = "update Url set Url=? where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, url);
		ps.setInt(2, urlID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
	}

	@Override
	/**
	 * 
	 * @Title: doUpdateEnabled
	 * @Description: update the Enabled singly
	 * @param UrlID
	 * @param Enabled
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateEnabled(int urlID, boolean Enabled) throws SQLException {
		String query = "update Url set Enabled=? where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setBoolean(1, Enabled);
		ps.setInt(2, urlID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
	}

	@Override
	/**
	 * 
	 * @Title: doUpdateRealTimePush
	 * @Description: update the ReanTimePush singly
	 * @param urlID
	 * @param RealTimePuah
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateRealTimePush(int urlID, boolean RealTimePush) throws SQLException {
		String query = "update Url set RealTimePush=? where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setBoolean(1, RealTimePush);
		ps.setInt(2, urlID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
	}
	
	@Override
	/**
	 * @Title: doFindUrl
	 * @Description: Find the Url By UrlID
	 * @param UrlID
	 * @return String
	 * @throws Exception
	 */
	public String doFindUserName(int UrlID) throws Exception {
		String query = "select UserName from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		String UserName = null;
		while(rs.next()){
			UserName = rs.getString(1);
		}
		rs.close();ps.close();
		return UserName;
	}

	@Override
	/**
	 * @Title: doFindUrl
	 * @Description: Find the Url By UrlID
	 * @param UrlID
	 * @return String
	 * @throws Exception
	 */
	public String doFindUrl(int UrlID) throws Exception {
		String query = "select Url from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		String url = null;
		while(rs.next()){
			url = rs.getString(1);
		}
		rs.close();ps.close();
		return url;
	}

	@Override
	/**
	 * @Title: doFindEnabled
	 * @Description: Find the Enabled By UrlID
	 * @param UrlID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doFindEnabled(int UrlID) throws Exception {
		String query = "select Enabled from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		boolean Enabled = false;
		while(rs.next()){
			Enabled = rs.getBoolean(1);
		}
		rs.close();ps.close();
		return Enabled;
	}

	@Override
	/**
	 * @Title: doFindRealTimePush
	 * @Description: Find the RealTimePush
	 * @param UrlID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doFindRealTimePush(int UrlID) throws Exception {
		String query = "select RealTimePush from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		boolean RealTimePush = false;
		while(rs.next()){
			RealTimePush = rs.getBoolean(1);
		}
		rs.close();ps.close();
		return RealTimePush;
	}

	@Override
	/**
	 * 
	 * @Title: FindAllByUserName
	 * @Description: ͨ��UserName��Url������Ҷ�Ӧ��Url����������
	 * @param UserName
	 * @return LinkedList<Url>
	 * @throws SQLException
	 */
	public LinkedList<Url> doFindAll(String UserName) throws SQLException {
		String query = "select * from Url where UserName=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, UserName);
		ResultSet rs = ps.executeQuery();
		LinkedList<Url> lst =new LinkedList<Url>();
		while(rs.next()){
			Url newUrl = new Url();
			newUrl.setUrlID(rs.getInt(1));
			newUrl.setUserName(UserName);
			newUrl.setTitle(rs.getString(3));
			newUrl.setUrl(rs.getString(4));
			newUrl.setEnabled(rs.getBoolean(5));
			newUrl.setRealTimePush(rs.getBoolean(6));
			lst.add(newUrl);
		}
		rs.close();ps.close();
		return lst;
	}
	
	@Override
	/**
	 * 
	 *Title: doFindAllEnabled
	 * @Description: ͨFind all enabled urls belong to the given Username
	 * @param UserName
	 * @return LinkedList<Url>
	 * @throws SQLException
	 */
	public LinkedList<Url> doFindAllEnabled(String UserName) throws SQLException{
		String query = "select * from Url where UserName=? and Enabled=true";
		ps = conn.prepareStatement(query);
		ps.setString(1, UserName);
		ResultSet rs = ps.executeQuery();
		LinkedList<Url> lst =new LinkedList<Url>();
		while(rs.next()){
			Url newUrl = new Url();
			newUrl.setUrlID(rs.getInt(1));
			newUrl.setUserName(UserName);
			newUrl.setTitle(rs.getString(3));
			newUrl.setUrl(rs.getString(4));
			newUrl.setEnabled(rs.getBoolean(5));
			newUrl.setRealTimePush(rs.getBoolean(6));
			lst.add(newUrl);
		}
		rs.close();ps.close();
		return lst;
	}
	
	@Override
	/**
	 * 
	 * @Title: doFind
	 * @Description: ͨGet a whole url record by UrlID
	 * @param UrlID
	 * @return Url
	 * @throws SQLException
	 */
	public Url doFind(int UrlID) throws SQLException{
		String query = "select * from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		Url url = new Url();
		while(rs.next()){
			url.setUrlID(UrlID);
			url.setUserName(rs.getString(2));
			url.setTitle(rs.getString(3));
			url.setUrl(rs.getString(4));
			url.setEnabled(rs.getBoolean(5));
			url.setRealTimePush(rs.getBoolean(6));
		}
		rs.close();ps.close();
		return url;
	}
	
	@Override
	/**
	 * 
	 * @Title: doFindAllEnabledAndRealTimePush
	 * @Description: 发现所有激活状态同时是实时推送的Url
	 * @return Map<Integer, String>
	 * @throws SQLException
	 */
	public Map<Integer, String> doFindAllEnabledAndRealTimePush() throws SQLException {
		String query = "select UrlID,UserName from Url where Enabled=true and RealTimePush=true";
		Statement stat = (Statement) conn.createStatement();
		ResultSet rs = stat.executeQuery(query);
		Map<Integer,String> urlMap= new HashMap<Integer,String>();
		while(rs.next()){
			int UrlID = rs.getInt(1);
			String UserName = rs.getString(2);
			urlMap.put(UrlID, UserName);
		}
		rs.close();stat.close();
		return urlMap;
	}
	
	@Override
	/**
	 * 
	 * @Title: doFindAllEnabledAndNotRealTimePush
	 * @Description: 发现所有不处于激活状态同时是实时推送的Url
	 * @return Map<Integer, String>
	 * @throws SQLException
	 */
	public Map<Integer, String> doFindAllEnabledAndNotRealTimePush() throws SQLException {
		String query = "select UrlID,UserName from Url where Enabled=true and RealTimePush=false";
		Statement stat = (Statement) conn.createStatement();
		ResultSet rs = stat.executeQuery(query);
		Map<Integer,String> urlMap= new HashMap<Integer,String>();
		while(rs.next()){
			int UrlID = rs.getInt(1);
			String UserName = rs.getString(2);
			urlMap.put(UrlID, UserName);
		}
		rs.close();stat.close();
		return urlMap;
	}
	
	@Override
	/**
	 * 
	 * @Title: getAllUrl
	 * @Description: ����Url��������������UrlID�Ͷ�ӦUrl��
	 * @return Map<Integer,String>
	 * @throws SQLException
	 */
	public Map<Integer,String> getAllUrl() throws SQLException {
		String query = "select UrlID,Url from Url where Enabled=true";
		Statement stat = (Statement) conn.createStatement();
		ResultSet rs = stat.executeQuery(query);
		Map<Integer,String> urlMap= new HashMap<Integer,String>();
		while(rs.next()){
			int UrlID = rs.getInt(1);
			String Url = rs.getString(2);
			urlMap.put(UrlID, Url);
		}
		rs.close();stat.close();
		return urlMap;
	}

	@Override
	/**
	 * @Title: IsExist
	 * @Description: Judge Whether the User Info in Which the UserName is Given Has Existed
	 * @param UrlUrl
	 * @return boolean: true is exist and false is not exist
	 * @throws Exception
	 */
	public boolean isExist(int UrlID) throws Exception {
		String query = "select count(*) from Url where UserName=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		boolean isexist = false;
		while(rs.next()){
			if(rs.getInt(1)!=0)	isexist = true;
		}
		rs.close();ps.close();
		return isexist;
	}

	@Override
	public void free() throws Exception {}
	
	@Override
	/**
	 * 
	 * @Title: doFindTitle
	 * @Description: Find the Title By UrlID
	 * @param UrlID
	 * @return String
	 * @throws Exception
	 */
	public String doFindTitle(int UrlID) throws Exception {
		String query = "select title from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		String newTitle = null;
		while(rs.next()){
			newTitle = rs.getString(1);
		}
		rs.close();ps.close();
		return newTitle;
	}
	
}
