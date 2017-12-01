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
 * @author ����
 * @date 2017��10��14��
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
	 * @Title: doUpdatePasswordAndEmail
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
	public boolean doUpdate(User user) throws SQLException {
		boolean success = dao.doUpdate(user);
		return success;
	}

	@Override
	/**
	 * 
	 * @Title: doUpdatePassword
	 * @Description: update the password singly
	 * @param UserName
	 * @param newPassword
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean doUpdatePassword(String UserName, String newPassword) throws SQLException {
		boolean success = dao.doUpdatePassword(UserName, newPassword);
		return success;
	}

	@Override
	/**
	 * 
	 * @Title: doUpdateEmail
	 * @Description: update the email singly
	 * @param UserName
	 * @param newEmail
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean doUpdateEmail(String UserName, String newEmail) throws SQLException {
		boolean success = dao.doUpdateEmail(UserName, newEmail);
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
	public LinkedList<User> doFindAllByPushTime(Time pushTime) throws Exception {
		LinkedList<User> UserList = dao.doFindAllByPushTime(pushTime);
		return UserList;
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
	public boolean isExist(String UserName) throws Exception {
		boolean isexist=dao.isExist(UserName);
		//dbc.free();
		return isexist;
	}
	
	@Override
	/**
	 * @Title: Free
	 * @Description: Close the Connnection
	 * @throws Exception
	 */
	public void free() throws Exception{
		dbc.free();
	}
}
