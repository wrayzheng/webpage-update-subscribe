package com.codebelief.app.DAO;

import com.codebelief.app.VO.Url;
/**
 * @author 何涛
 * @version 1st   on 2017年10月13日
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
	public boolean doInsert(Url url) throws Exception;
	
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
	 * @Description: Update the Url,Enable,RealTimePush of a piece of Url Info
	 * @param UrlID
	 * @param url
	 * @param Enable
	 * @param RealTimePush
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doUpdate(int UrlID,String title,String url,boolean Enable,boolean RealTimePush) throws Exception;
	
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
	public boolean doFindEnable(int UrlID) throws Exception;
	
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
	 * @Title: doFindAll
	 * @Description: Find the UserName,Url,RealTimePush By UrlID
	 * @param UrlID
	 * @return Url
	 * @throws Exception
	 */
	public Url doFindAll(int UrlID) throws Exception;
	
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
