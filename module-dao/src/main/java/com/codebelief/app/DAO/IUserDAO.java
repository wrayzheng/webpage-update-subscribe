package com.codebelief.app.DAO;

import java.sql.Time;
import java.util.LinkedList;

import com.codebelief.app.VO.User;
/**
 * @author 何涛
 * @version 1st   on 2017年10月13日
 */
public interface IUserDAO {
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
	 * @Title: doUpdate
	 * @Description: Update a User's Password and Email, 
	 * 				 Choose Different Updating ways 
	 * 				 by judging the whether the Password is NULL.
	 * @param UserName
	 * @param newPassword
	 * @param newEmail
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doUpdatePasswordAndEmail(String UserName,String newPassword,String newEmail) throws Exception;
	
	/**
	 * 
	 * @Title: doUpdatePushTime
	 * @Description: Update a User's PushTime
	 * @param UserName
	 * @param PushTime
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doUpdatePushTime(String UserName,Time PushTime) throws Exception;
	
	/**
	 * 
	 * @Title: doFindEmail
	 * @Description: Find Email By UserName
	 * @param UserName
	 * @return UserName
	 * @throws Exception
	 */
	public String doFindEmail(String UserName) throws Exception;
	
	/**
	 * 
	 * @Title: doFindPassword
	 * @Description: Find the Password By UserName
	 * @param UserName
	 * @return String
	 * @throws Exception
	 */
	public String doFindPassword(String UserName) throws Exception;
	
	/**
	 * 
	 * @Title: doFindPasswordAndEmail
	 * @Description: Find the Password and Email By UserName
	 * @param UserName
	 * @return LinkedList<String>
	 * @throws Exception
	 */
	public LinkedList<String> doFindPasswordAndEmail(String UserName) throws Exception;
	
	/**
	 * 
	 * @Title: doFindAll
	 * @Description: Find the Detailed Message By UserName
	 * @param UserName
	 * @return User
	 * @throws Exception
	 */
	public User doFindAll(String UserName) throws Exception;
	
	/**
	 * 
	 * @Title: doFindPushTime
	 * @Description: Find PushTime By UserName
	 * @param UserName
	 * @return java.sql.Time
	 * @throws Exception
	 */
	public Time doFindPushTime(String UserName) throws Exception;
	
	/**
	 * 
	 * @Title: IsExist
	 * @Description: Judge Whether the User Info in Which the UserName is Given Has Existed
	 * @param UserName
	 * @return boolean: true is Exist and false is not Exist
	 * @throws Exception
	 */
	public boolean isExist(String UserName) throws Exception;
	
	/**
	 * 
	 * @Title: Free
	 * @Description: Close the Connnection
	 * @throws Exception
	 */
	public void free() throws Exception;
}
