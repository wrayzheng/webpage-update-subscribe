package com.codebelief.app.DAO;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;

import com.codebelief.app.VO.Url;
/**
 * @author ����
 * @version 1st   on 2017��10��13��
 */
public interface IUrlDAO {
	/**
	 * 
	 * @Title: doInsert
	 * @Description: Insert a piece of Url Info
	 * @param url
	 * @return boolean
	 * @throws Exception
	 */
	public int doInsert(
			String UserName, 
			String title,
			String url,
			boolean enable,
			boolean realPushTime) throws Exception;
	
	/**
	 * 
	 * @Title: doDelete
	 * @Description: Delete a piece of Url Info
	 * @param UrlID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doDelete(int UrlID) throws Exception;
	
	/**
	 * 
	 * @Title: doUpdate
	 * @Description: Update the a complete url instance
	 * @param UrlID
	 * @param url
	 * @param Enable
	 * @param RealTimePush
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doUpdate(Url urlInstance) throws Exception;
	
	/**
	 * 
	 * @Title: doUpdateTitle
	 * @Description: update the Title singly
	 * @param UrlID
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateTitle(int UrlID, String title) throws Exception;
	
	/**
	 * 
	 * @Title: doUpdateUrl
	 * @Description: update the Url singly
	 * @param UrlID
	 * @param url
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateUrl(int UrlID, String url) throws SQLException;
	
	/**
	 * 
	 * @Title: doUpdateEnable
	 * @Description: update the Enable singly
	 * @param UrlID
	 * @param Enable
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateEnabled(int UrlID, boolean Enable) throws SQLException;
	
	/**
	 * 
	 * @Title: doUpdateRealTimePush
	 * @Description: update the ReanTimePush singly
	 * @param urlID
	 * @param RealTimePuah
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateRealTimePush(int urlID, boolean RealTimePuah) throws SQLException;
	
	/**
	 * 
	 * @Title: doFindUserName
	 * @Description: Find the UserName By UrlID;
	 * @param UrlID
	 * @return String
	 * @throws Exception
	 */
	public String doFindUserName(int UrlID) throws Exception;
	/**
	 * 
	 * @Title: doFindTitle
	 * @Description: Find the title By UrlID
	 * @param UrlID
	 * @return
	 * @throws Exception
	 */
	public String doFindTitle(int UrlID) throws Exception;
	
	/**
	 * 
	 * @Title: doFindUrl
	 * @Description: Find the Url By UrlID
	 * @param UrlID
	 * @return String
	 * @throws Exception
	 */
	public String doFindUrl(int UrlID) throws Exception;
	
	/**
	 * 
	 * @Title: doFindEnable
	 * @Description: Find the Enable By UrlID
	 * @param UrlID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doFindEnabled(int UrlID) throws Exception;
	
	/**
	 * 
	 * @Title: doFindRealTimePush
	 * @Description: Find the RealTimePush
	 * @param UrlID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doFindRealTimePush(int UrlID) throws Exception;
	
	/**
	 * 
	 * @Title: FindAllByUserName
	 * @Description: ͨ��UserName��Url������Ҷ�Ӧ��Url����������
	 * @param UserName
	 * @return LinkedList<Url>
	 * @throws SQLException
	 */
	public LinkedList<Url> doFindAll(String UserName) throws SQLException;
	
	public Map<Integer, String> doFindAllEnabledAndRealTimePush() throws SQLException;
	
	public Map<Integer, String> doFindAllEnabledAndNotRealTimePush() throws SQLException;
	
	/**
	 * 
	 * @Title: getAllUrl
	 * @Description: ����Url��������������UrlID�Ͷ�ӦUrl��
	 * @return Map<Integer,String>
	 * @throws SQLException
	 */
	public Map<Integer,String> getAllUrl() throws SQLException;
	
	/**
	 * 
	 * @Title: IsExist
	 * @Description: Judge Whether the Url Info in Which the UrlID is Given Has Existed
	 * @param UrlUrl
	 * @return boolean: true is exist and false is not exist
	 * @throws Exception
	 */
	public boolean isExist(int UrlID) throws Exception;
	
	/**
	 * 
	 * @Title: Free
	 * @Description: Close the Connection to Database
	 * @throws Exception
	 */
	public void free() throws Exception;
}
