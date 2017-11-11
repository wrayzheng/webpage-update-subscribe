package com.codebelief.app.DAO;

import java.sql.*;

import com.codebelief.app.VO.Content;

/**
 * @ClassName: ContentDAO
 * @Description: Define Some Concrete Functions For Content Table
 * @author 何涛
 * @date 2017年10月17日
 *
 */
public class ContentDAO implements IContentDAO{
	private Connection conn = null;
	private PreparedStatement ps = null;
	
	public ContentDAO(Connection conn){
		this.conn = conn;
	}
	
	@Override
	/**
	 * @Title: doInsert
	 * @Description: Insert a piece of new Content Info
	 * @param content
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doInsert(Content content) throws Exception {
		String query = "insert into Content values(?,?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, content.getContentID());
		ps.setInt(2, content.getUrlID());
		//ps.setString(3,content.getTitle());
		ps.setString(3, content.getHtml());
		ps.setString(4, content.getDelta());
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
//	 * @Description: Delete a piece of Content Info
	 * @param ContentID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doDelete(int ContentID) throws Exception {
		String query = "delete from Content where COntentID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, ContentID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
	}

	@Override
	/**
	 * @Title: doUpdateHtmlAndDelta
	 * @Description: Update Html and Delta of a content
	 * @param ContentID
	 * @param Html
	 * @param Delta
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doUpdate(Content content) throws Exception {
		String Html = content.getHtml();
		String Delta = content.getDelta();
		int ContentID = content.getContentID();
		String query = "update Content set Html=?,Delta=? where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, Html);
		ps.setString(2, Delta);
		ps.setInt(3, ContentID);
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
	 * @Title: doUpdateHtml
	 * @Description: update the Html singly
	 * @param ContentID
	 * @param Html
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean doUpdateHtml(int ContentID, String Html) throws SQLException {
		String query = "update Content set Html=? where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, Html);
		ps.setInt(2, ContentID);
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
	 * @Title: doUpdateDelta
	 * @Description: update the Delta singly
	 * @param ContentID
	 * @param Delta
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean doUpdateDelta(int ContentID, String Delta) throws SQLException {
		String query = "update Content set Delta=? where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, Delta);
		ps.setInt(2, ContentID);
		if(ps.executeUpdate() == 0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
	}
	
	@Override
	/**
	 * @Title: doFindUriID
	 * @Description: Find the ContentID By a special ContentID
	 * 				 return -1 if finding UrlID failing
	 * @param ContentID
	 * @return int
	 * @throws Exception
	 */
	public int doFindUrlID(int ContentID) throws Exception {
		String query = "select UrlID from Content where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, ContentID);
		ResultSet rs = ps.executeQuery();
		int UrlID = -1;		//-1 stands for finding UrlID failing
		while(rs.next()){
			UrlID = rs.getInt(1);
		}
		rs.close();ps.close();
		return UrlID;
	}

	@Override
	/**
	 * @Title: doFindHtml
	 * @Description: Find Html code By ContentID
	 * @param ContentID
	 * @return String
	 * @throws Exception
	 */
	public String doFindHtml(int ContentID) throws Exception {
		String query = "select Html from Content where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, ContentID);
		ResultSet rs = ps.executeQuery();
		String Html = null;
		while(rs.next()){
			Html = rs.getString(1);
		}
		rs.close();ps.close();
		return Html;
	}

	@Override
	/**
	 * @Title: doFindDelta
	 * @Description: Find Delta by ContentID 
	 * @param ContentID
	 * @return String
	 * @throws Exception
	 */
	public String doFindDelta(int ContentID) throws Exception {
		String query = "select Delta from Content where ContrneID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, ContentID);
		ResultSet rs = ps.executeQuery();
		String Delta = null;
		while(rs.next()){
			Delta = rs.getString(1);
		}
		rs.close();ps.close();
		return Delta;
	}

	@Override
	/**
	 * @Title: doFindAll
	 * @Description: Find All Messages By ContentID
	 * @param ContentID
	 * @return Content
	 * @throws Exception
	 */
	public Content doFindAll(int ContentID) throws Exception {
		String query = "select * from Content where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, ContentID);
		ResultSet rs = ps.executeQuery();
		Content newContent = null;//new Content(ContentID);
		while(rs.next()){
			newContent = new Content(ContentID);
			newContent.setUrlID(rs.getInt(2));
			newContent.setHtml(rs.getString(3));
			newContent.setDelta(rs.getString(4));
		}
		rs.close();ps.close();
		return newContent;
	}

	@Override
	/**
	 * @Title: IsExist
	 * @Description: Judge Whether the Url Info in Which the UrlID is Given Has Existed
	 * @param ContentID
	 * @return boolean: true is exist and false is not exist
	 * @throws Exception
	 */
	public boolean isExist(int ContentID) throws Exception {
		String query = "select count(*) from Content where ContentID=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, ContentID);
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

}
