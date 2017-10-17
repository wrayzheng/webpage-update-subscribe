/*
 * @(#)SeeUTomorrow --- UrlDAOProxy.java 
 */
package com.codebelief.app.DAO;

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
	public boolean doUpdate(int UrlID, String url, boolean Enable, boolean RealTimePush) throws Exception {
		boolean success = dao.doUpdate(UrlID, url, Enable, RealTimePush);
		//dbc.free();
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
	public boolean IsExist(int UrlID) throws Exception {
		boolean isexist=dao.IsExist(UrlID);
		//dbc.free();
		return isexist;
	}

	@Override
	/**
	 * @Title: Free
	 * @Description: Close the Connection to Database
	 * @throws Exception
	 */
	public void Free() throws Exception {
		dbc.free();
	}

}
