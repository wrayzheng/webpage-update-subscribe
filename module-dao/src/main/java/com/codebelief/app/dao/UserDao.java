package com.codebelief.app.dao;

import java.util.List;

import com.codebelief.app.bean.User;

/**
 * @author: Wray Zheng
 * @date: 2018-08-03
 * @description: TODO
 */
public interface UserDao {
	/**
	 * 
	 * @Title: doInsert
	 * @Description: Insert a piece of User Info
	 * @param user
	 * @return boolean 
	 * @throws Exception
	 */
	public boolean doInsert(User user) throws Exception;

	/**
	 * 
	 * @Title: doFindEmail
	 * @Description: Find Email By userName
	 * @param userName
	 * @return userName
	 * @throws Exception
	 */
	public String doFindEmail(String userName) throws Exception;
	
	/**
	 * 
	 * @Title: doFindPassword
	 * @Description: Find the Password By userName
	 * @param userName
	 * @return String
	 * @throws Exception
	 */
	public String doFindPassword(String userName) throws Exception;
	
	/**
	 * 
	 * @Title: doFindAll
	 * @Description: Find the Detailed Message By userName
	 * @param userName
	 * @return User
	 * @throws Exception
	 */
	public User doFindAll(String userName) throws Exception;
	
	/**
	 * 
	 * @Title: doFindAllByPushTime
	 * @Description: Find the user's detailed Message By pushTime
	 * @param Time
	 * @return LinkedList<User>
	 * @throws Exception
	 */
	public List<User> doFindAllByPushTime(java.sql.Time pushTime) throws Exception;
	
	/**
	 * 
	 * @Title: IsExist
	 * @Description: Judge Whether the User Info in Which the userName is Given Has Existed
	 * @param userName
	 * @return boolean: true is Exist and false is not Exist
	 * @throws Exception
	 */
	public boolean isExist(String userName) throws Exception;

}