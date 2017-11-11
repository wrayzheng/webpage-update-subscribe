package com.codebelief.app.DAO;

import java.sql.*;

import com.codebelief.app.VO.Url;
//import com.mysql.jdbc.Statement;

/**
 * 
 * @ClassName: UrlDAO
 * @Description: Define Some Concrete Operations For Url Table
 * @author 何涛
 * @date 2017年10月14日
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
	public boolean doInsert(Url url) throws Exception {
		String query = "insert into Url values(?,?,?,?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, url.getUrlID());
		ps.setString(2, url.getUserName());
		ps.setString(3, url.getTitle());
		ps.setString(4, url.getUrl());
		ps.setBoolean(5, url.isEnable());
		ps.setBoolean(6, url.isRealTimePush());
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
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
	 * @Description: Update the Url,Enable,RealTimePush of a piece of Url Info
	 * @param UrlID
	 * @param url
	 * @param Enable
	 * @param RealTimePush
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doUpdate(Url urlInstance) throws Exception {
		String title = urlInstance.getTitle();
		String url = urlInstance.getUrl();
		boolean Enable = urlInstance.isEnable();
		boolean RealTimePush = urlInstance.isRealTimePush();
		int UrlID = urlInstance.getUrlID();
		String query = "update Url set Title=?,Url=?,Enable=?,RealTimePush=? where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, title);
		ps.setString(2, url);
		ps.setBoolean(3, Enable);
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
		String query = "update Url set url=? where UrlID=?";
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
	 * @Title: doUpdateEnable
	 * @Description: update the Enable singly
	 * @param UrlID
	 * @param Enable
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateEnable(int urlID, boolean Enable) throws SQLException {
		String query = "update Url set Enable=? where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setBoolean(1, Enable);
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
	 * @Title: doFindEnable
	 * @Description: Find the Enable By UrlID
	 * @param UrlID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doFindEnable(int UrlID) throws Exception {
		String query = "select Enable from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		boolean Enable = false;
		while(rs.next()){
			Enable = rs.getBoolean(1);
		}
		rs.close();ps.close();
		return Enable;
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
	 * @Title: doFindAll
	 * @Description: Find the UserName,Url,RealTimePush By UrlID
	 * @param UrlID
	 * @return Url
	 * @throws Exception
	 */
	public Url doFindAll(int UrlID) throws Exception {
		String query = "select * from Url where UrlID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, UrlID);
		ResultSet rs = ps.executeQuery();
		Url newUrl = null;//new Url(UrlID);
		while(rs.next()){
			newUrl = new Url(UrlID);
			newUrl.setUserName(rs.getString(2));
			newUrl.setUrl(rs.getString(3));
			newUrl.setEnable(rs.getBoolean(4));
			newUrl.setRealTimePush(rs.getBoolean(5));
		}
		rs.close();ps.close();
		return newUrl;
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
