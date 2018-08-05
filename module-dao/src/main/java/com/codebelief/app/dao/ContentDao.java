package com.codebelief.app.dao;

import java.util.List;

import com.codebelief.app.bean.Content;

/**
 * @author: Wray Zheng
 * @date: 2018-08-02
 * @description: TODO
 */
public interface ContentDao {
	/**
	 * 
	 * @Title: doInsert
	 * @Description: Insert a piece of new Content Info
	 * @param content
	 * @return boolean
	 * @throws Exception
	 */
	public int doInsert(Content content) throws Exception;
	
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
	public boolean doUpdate(Content content) throws Exception;
	
	/**
	 * 
	 * @Title: doFindAllByUrlID
	 * @Description: Find Content by urlID
	 * @param UrlID
	 * @return Content
	 * @throws Exception
	 */
	public Content doFindAllByUrlID(int urlID) throws Exception;
	
	/**
	 * 
	 * @Title: doFindAllChanged
	 * @Description: ͨFind all content with Delta field updated
	 * @return LinkedList<Content>
	 * @throws Exception
	 */
	public List<Content> doFindAllChanged() throws Exception;
	
	/**
	 * 
	 * @Title: doDeleteByUrlID
	 * @Description: Delete a piece of Content Info by UrlID
	 * @param UrlID
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doDeleteByUrlID(int urlID) throws Exception;
}
