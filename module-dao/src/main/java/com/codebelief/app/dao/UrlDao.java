package com.codebelief.app.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.codebelief.app.bean.Url;

/**
 * @author: Wray Zheng
 * @date: 2018-08-03
 * @description: TODO
 */
public interface UrlDao {
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
	 * @return int
	 * @throws Exception
	 */
	public boolean doDelete(int urlID) throws Exception;
	
	/**
	 * 
	 * @Title: doUpdateTitle
	 * @Description: update the Title singly
	 * @param urlID
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateTitle(@Param("urlID") int urlID, @Param("title") String title) throws Exception;
	
	/**
	 * 
	 * @Title: doUpdateUrl
	 * @Description: update the Url singly
	 * @param UrlID
	 * @param url
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateUrl(@Param("urlID") int urlID, @Param("url") String url) throws SQLException;
	
	/**
	 * 
	 * @Title: doUpdateEnable
	 * @Description: update the Enable singly
	 * @param UrlID
	 * @param Enable
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateEnabled(@Param("urlID") int urlID, @Param("enabled") boolean enabled) throws SQLException;
	
	/**
	 * 
	 * @Title: doUpdateRealTimePush
	 * @Description: update the ReanTimePush singly
	 * @param urlID
	 * @param RealTimePuah
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateRealTimePush(@Param("urlID") int urlID, @Param("realTimePush") boolean realTimePuah) throws SQLException;
	
	/**
	 * 
	 * @Title: doFindUserName
	 * @Description: Find the UserName By UrlID;
	 * @param UrlID
	 * @return String
	 * @throws Exception
	 */
	public String doFindUserName(int urlID) throws Exception;
	
	/**
	 * 
	 * @Title: FindAllByUserName
	 * @Description: ͨFind all urls belong to the given Username
	 * @param UserName
	 * @return LinkedList<Url>
	 * @throws SQLException
	 */
	public List<Url> doFindAll(String userName) throws SQLException;
	
	/**
	 * 
	 * @Title: doFindAllEnabled
	 * @Description: ͨFind all enabled urls belong to the given Username
	 * @param UserName
	 * @return LinkedList<Url>
	 * @throws SQLException
	 */
	public List<Url> doFindAllEnabled(String userName) throws SQLException;
	
	/**
	 * 
	 * @Title: doFind
	 * @Description: ͨGet a whole url record by UrlID
	 * @param UrlID
	 * @return Url
	 * @throws SQLException
	 */
	public Url doFind(int urlID) throws SQLException;
	
	/**
	 * 
	 * @Title: getAllUrl
	 * @Description: ����Url��������������UrlID�Ͷ�ӦUrl��
	 * @return Map<Integer,String>
	 * @throws SQLException
	 */
	public List<Map<String,String>> getAllUrl() throws SQLException;
	
}
