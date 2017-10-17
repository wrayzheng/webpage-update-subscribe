/*
 * @(#)SeeUTomorrow --- IContentDAO.java 
 */
package com.codebelief.app.DAO;

import com.codebelief.app.VO.Content;
/**
 * @author 何涛
 * @version 1st   on 2017年10月14日
 */
public interface IContentDAO {
	/**
	 * 
	 * @Title: doInsert
	 * @Description: Insert a piece of new Content Info
	 * @param content
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doInsert(Content content) throws Exception;
	
	/**
	 * 
	 * @Title: doDelete
//	 * @Description: Delete a piece of Content Info
	 * @param ContentID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doDelete(int ContentID) throws Exception;
	
	/**
	 * 
	 * @Title: doUpdateTitle
	 * @Description: Update Title of a content
	 * @param ContentID
	 * @param Title
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doUpdateTitle(int ContentID,String Title) throws Exception;
	
	/**
	 * 
	 * @Title: doUpdateHtmlAndDelta
	 * @Description: Update Html and Delta of a content
	 * @param ContentID
	 * @param Html
	 * @param Delta
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doUpdateHtmlAndDelta(int ContentID,String Html,String Delta) throws Exception;
	
	/**
	 * 
	 * @Title: doFindUriID
	 * @Description: Find the ContentID By a special ContentID
	 * @param ContentID
	 * @return int
	 * @throws Exception
	 */
	public int doFindUrlID(int ContentID) throws Exception;
	
	/**
	 * 
	 * @Title: doFindTitle
	 * @Description: Find Title By ContentID
	 * @param ContentID
	 * @return String
	 * @throws Exception
	 */
	public String doFindTitle(int ContentID) throws Exception;
	
	/**
	 * 
	 * @Title: doFindHtml
	 * @Description: Find Html code By ContentID
	 * @param ContentID
	 * @return String
	 * @throws Exception
	 */
	public String doFindHtml(int ContentID) throws Exception;
	
	/**
	 * 
	 * @Title: doFindDelta
	 * @Description: Find Delta by ContentID 
	 * @param ContentID
	 * @return String
	 * @throws Exception
	 */
	public String doFindDelta(int ContentID) throws Exception;
	
	/**
	 * 
	 * @Title: doFindAll
	 * @Description: Find All Messages By ContentID
	 * @param ContentID
	 * @return Content
	 * @throws Exception
	 */
	public Content doFindAll(int ContentID) throws Exception;
	
	/**
	 * 
	 * @Title: IsExist
	 * @Description: Judge Whether the Url Info in Which the UrlID is Given Has Existed
	 * @param ContentID
	 * @return boolean: true is exist and false is not exist
	 * @throws Exception
	 */
	public boolean IsExist(int ContentID) throws Exception;
	
	/**
	 * 
	 * @Title: Free
	 * @Description: Close the Connection to Database
	 * @throws Exception
	 */
	public void Free() throws Exception;
}
