package com.codebelief.app.DAO;

import java.sql.SQLException;

import com.codebelief.app.DatabaseConnection.*;
import com.codebelief.app.VO.Content;
/**
 * 
 * @ClassName: ContentDAOProxy
 * @Description: obtaining and closing the database, 
 * 				 and implementing ContentDAO interface 
 * 				 using functions in ContentDAO type
 * @author 何涛
 * @date 2017年10月14日
 *
 */
public class ContentDAOProxy implements IContentDAO{
	private MySQLDatabaseConnection dbc=null;
	private ContentDAO dao=null;
	
	public ContentDAOProxy() throws Exception{
		dbc=DatabaseConnectionFactory.getMySQLDatabaseConnection();
		dao=new ContentDAO(dbc.getConnection());
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
		boolean success=dao.doInsert(content);
		//dbc.free();
		return success;
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
		boolean success=dao.doDelete(ContentID);
		//dbc.free();
		return success;
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
	public boolean doUpdateHtmlAndDelta(int ContentID, String Html, String Delta) throws Exception {
		boolean success=dao.doUpdateHtmlAndDelta(ContentID, Html, Delta);
		//dbc.free();
		return success;
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
		boolean success = dao.doUpdateHtml(ContentID, Html);
		return success;
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
		boolean success = dao.doUpdateDelta(ContentID, Delta);
		return success;
	}

	@Override
	/**
	 * @Title: doFindUriID
	 * @Description: Find the ContentID By a special ContentID
	 * @param ContentID
	 * @return int
	 * @throws Exception
	 */
	public int doFindUrlID(int ContentID) throws Exception {
		int UrlID=dao.doFindUrlID(ContentID);
		//dbc.free();
		return UrlID;
	}

	@Override
	/**
	 * @Title: doFindTitle
	 * @Description: Find Title By ContentID
	 * @param ContentID
	 * @return String
	 * @throws Exception
	 */
	public String doFindTitle(int ContentID) throws Exception {
		String Title=dao.doFindTitle(ContentID);
		//dbc.free();
		return Title;
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
		String Html=dao.doFindHtml(ContentID);
		//dbc.free();
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
		String Delta=dao.doFindDelta(ContentID);
		//dbc.free();
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
		Content content=dao.doFindAll(ContentID);
		//dbc.free();
		return content;
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
		boolean isexist=dao.isExist(ContentID);
		//dbc.free();
		return isexist;
	}

	@Override
	/**
	 * @Title: Free
	 * @Description: Close the Connection to Database
	 * @throws Exception
	 */
	public void free() throws Exception {
		dbc.free();
	}

}
