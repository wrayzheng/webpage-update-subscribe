package com.codebelief.app.DAO;

import java.sql.SQLException;

import com.codebelief.app.DatabaseConnection.*;
import com.codebelief.app.VO.Url;
/**
 * 
 * @ClassName: UrlDAOProxy
 * @Description: obtaining and closing the database, 
 * 				 and implementing UrlDAO interface 
 * 				 using functions in UrlDAO type
 * @author 何涛
 * @date 2017年10月14日
 *
 */
public class UrlDAOProxy implements IUrlDAO{
	private MySQLDatabaseConnection dbc=null;
	private UrlDAO dao=null;
	
	public UrlDAOProxy() throws Exception{
		dbc=DatabaseConnectionFactory.getMySQLDatabaseConnection();
		dao=new UrlDAO(dbc.getConnection());
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
		boolean success=dao.doInsert(url);
		//dbc.free();
		return success;
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
		boolean success = dao.doDelete(UrlID);
		//dbc.free();
		return success;
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
		boolean success = dao.doUpdate(urlInstance);
		//dbc.free();
		return success;
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
		boolean success = dao.doUpdateTitle(urlID, title);
		return success;
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
	public boolean doUpdateUrl(int urlID, String url) throws SQLException{
		boolean success = dao.doUpdateUrl(urlID, url);
		return success;
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
		boolean success = dao.doUpdateEnable(urlID, Enable);
		return success;
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
	public boolean doUpdateRealTimePush(int urlID, boolean RealTimePuah) throws SQLException {
		boolean success = dao.doUpdateRealTimePush(urlID, RealTimePuah);
		return success;
	}

	@Override
	/**
	 * @Title: doFindUserName
	 * @Description: Find the UserName By UrlID;
	 * @param UrlID
	 * @return String
	 * @throws Exception
	 */
	public String doFindUserName(int UrlID) throws Exception {
		String UserName=dao.doFindUserName(UrlID);
		//dbc.free();
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
		String url=dao.doFindUrl(UrlID);
		//dbc.free();
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
		boolean enable=dao.doFindEnable(UrlID);
		//dbc.free();
		return enable;
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
		boolean RealTimePush=dao.doFindRealTimePush(UrlID);
		//dbc.free();
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
		Url newUrl=dao.doFindAll(UrlID);
		//dbc.free();
		return newUrl;
	}

	@Override
	/**
	 * @Title: IsExist
	 * @Description: Judge Whether the Url Info in Which the UrlID is Given Has Existed
	 * @param UrlUrl
	 * @return boolean: true is exist and false is not exist
	 * @throws Exception
	 */
	public boolean isExist(int UrlID) throws Exception {
		boolean isexist=dao.isExist(UrlID);
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

	public String doFindTitle(int UrlID) throws Exception {
		String title=dao.doFindTitle(UrlID);
		return title;
	}

}
