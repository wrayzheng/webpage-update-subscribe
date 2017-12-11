package com.codebelief.app.DAO;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;

import com.codebelief.app.DatabaseConnection.*;
import com.codebelief.app.VO.Url;
/**
 * 
 * @ClassName: UrlDAOProxy
 * @Description: obtaining and closing the database, 
 * 				 and implementing UrlDAO interface 
 * 				 using functions in UrlDAO type
 * @author ����
 * @date 2017��10��14��
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
	public int doInsert(String UserName, 
			String title,
			String url,
			boolean enable,
			boolean realPushTime) throws Exception {
		int success=dao.doInsert(UserName, title, url, enable, realPushTime);
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
	public boolean doUpdateEnabled(int urlID, boolean Enable) throws SQLException {
		boolean success = dao.doUpdateEnabled(urlID, Enable);
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
	public boolean doFindEnabled(int UrlID) throws Exception {
		boolean enable=dao.doFindEnabled(UrlID);
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
		return RealTimePush;
	}
	
	@Override
	/**
	 * 
	 * @Title: getAllUrl
	 * @Description: ����Url��������������UrlID�Ͷ�ӦUrl��
	 * @return Map<Integer,String>
	 * @throws SQLException
	 */
	public Map<Integer, String> getAllUrl() throws SQLException {
		Map<Integer,String> urlMap = dao.getAllUrl();
		return urlMap;
	}
	
	@Override
	/**
	 * 
	 * @Title: doFindAll
	 * @Description: ͨ��UserName��Url������Ҷ�Ӧ��Url����������
	 * @param UserName
	 * @return LinkedList<Url>
	 * @throws SQLException
	 */
	public LinkedList<Url> doFindAll(String UserName) throws SQLException {
		LinkedList<Url> lst = dao.doFindAll(UserName);
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
		return dao.doFindAllEnabled(UserName);
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
		Url url = dao.doFind(UrlID);
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
		Map<Integer, String> urlMap = dao.doFindAllEnabledAndRealTimePush();
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
	public Map<Integer, String> doFindAllEnabledAndNotRealTimePush() throws SQLException{
		Map<Integer, String> urlMap = dao.doFindAllEnabledAndNotRealTimePush();
		return urlMap;
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
