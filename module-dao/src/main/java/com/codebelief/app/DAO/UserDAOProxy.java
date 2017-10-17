/*
 * @(#)SeeUTomorrow --- UserDAOProxy.java 
 */
package com.codebelief.app.DAO;

import java.sql.*;
import java.util.LinkedList;

import com.codebelief.app.DatabaseConnection.DatabaseConnectionFactory;
import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.VO.User;
/**
 * 
 * @ClassName: UserDAOProxy
 * @Description: obtaining and closing the database, 
 * 				 and implementing UserDAO interface 
 * 				 using functions in UserDAO type
 * @author 何涛
 * @date 2017年10月14日
 *
 */
public class UserDAOProxy implements IUserDAO{
	private MySQLDatabaseConnection dbc=null;
	private UserDAO dao=null;
	
	public UserDAOProxy() throws Exception{
		this.dbc=DatabaseConnectionFactory.getMySQLDatabaseConnection();
		this.dao=new UserDAO(dbc.getConnection());
	}
	
	@Override
	/**
	 * @Title: doInsert
	 * @Description: Insert a piece of User Info
	 * @param user
	 * @return boolean 
	 * @throws Exception
	 */
	public boolean doInsert(User user) throws Exception {
		boolean success=dao.doInsert(user);
		//dbc.free();
		return success;
	}

	@Override
	/**
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
	public boolean doUpdatePasswordAndEmail(String UserName, String newPassword, String newEmail) throws Exception {
		boolean success=dao.doUpdatePasswordAndEmail(UserName, newPassword, newEmail);
		//dbc.free();
		return success;
	}

	@Override
	/**
	 * @Title: doUpdatePushTime
	 * @Description: Update a User's PushTime
	 * @param UserName
	 * @param PushTime
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doUpdatePushTime(String UserName, Time PushTime) throws Exception {
		boolean success=dao.doUpdatePushTime(UserName, PushTime);
		//dbc.free();
		return success;
	}

	@Override
	/**
	 * @Title: doFindEmail
	 * @Description: Find Email By UserName
	 * @param UserName
	 * @return UserName
	 * @throws Exception
	 */
	public String doFindEmail(String UserName) throws Exception {
		String Email=dao.doFindEmail(UserName);
		//dbc.free();
		return Email;
	}

	@Override
	/**
	 * @Title: doFindPassword
	 * @Description: Find the Password By UserName
	 * @param UserName
	 * @return String
	 * @throws Exception
	 */
	public String doFindPassword(String UserName) throws Exception {
		String Email=dao.doFindPassword(UserName);
		//dbc.free();
		return Email;
	}

	@Override
	/**
	 * @Title: doFindPasswordAndEmail
	 * @Description: Find the Password and Email By UserName
	 * @param UserName
	 * @return LinkedList<String>
	 * @throws Exception
	 */
	public LinkedList<String> doFindPasswordAndEmail(String UserName) throws Exception {
		LinkedList<String> PasswordAndEmail=dao.doFindPasswordAndEmail(UserName);
		//dbc.free();
		return PasswordAndEmail;
	}

	@Override
	/**
	 * @Title: doFindAll
	 * @Description: Find the Detailed Message By UserName
	 * @param UserName
	 * @return User
	 * @throws Exception
	 */
	public User doFindAll(String UserName) throws Exception {
		User user=dao.doFindAll(UserName);
		//dbc.free();
		return user;
	}

	@Override
	/**
	 * @Title: doFindPushTime
	 * @Description: Find PushTime By UserName
	 * @param UserName
	 * @return java.sql.Time
	 * @throws Exception
	 */
	public Time doFindPushTime(String UserName) throws Exception {
		Time PushTime=dao.doFindPushTime(UserName);
		//dbc.free();
		return PushTime;
	}

	@Override
	/**
	 * @Title: IsExist
	 * @Description: Judge Whether the User Info in Which the UserName is Given Has Existed
	 * @param UserName
	 * @return boolean: true is Exist and false is not Exist
	 * @throws Exception
	 */
	public boolean IsExist(String UserName) throws Exception {
		boolean isexist=dao.IsExist(UserName);
		//dbc.free();
		return isexist;
	}
	
	@Override
	/**
	 * @Title: Free
	 * @Description: Close the Connnection
	 * @throws Exception
	 */
	public void Free() throws Exception{
		dbc.free();
	}

}
