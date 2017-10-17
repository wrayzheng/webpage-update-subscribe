package com.codebelief.app.DAO;

import java.sql.*;
import java.util.LinkedList;

import com.codebelief.app.VO.User;
import com.mysql.jdbc.Statement;

/**
 * 
 * @ClassName: UserDAO
 * @Description: Define Some Concrete Functions For User Table
 * @author 何涛
 * @date 2017年10月14日
 *
 */
public class UserDAO implements IUserDAO{
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private Statement stat = null;
	
	public UserDAO(Connection conn){
		this.conn = conn;
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
		String query = "insert into User values(?,?,?,?)";
		this.ps = this.conn.prepareStatement(query);
		ps.setString(1, user.getUserName());
		ps.setString(2,user.getPassword());
		ps.setString(3,user.getEmail());
		ps.setTime(4, user.getPushTime());
		if(ps.executeUpdate() == 0){
			this.ps.close();
			return false;
		}
		this.ps.close();
		return true;
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
	public boolean doUpdatePasswordAndEmail(String UserName,String newPassword, String newEmail) throws Exception {
		String query = null;
		if(newEmail == null)	query = "update User set Email='"+newEmail+"' where UserName = "+UserName+"'";
		else query = "update User set Password='"+newPassword+"',Email='"+newEmail+"' where UserName='"+UserName+"'";
		stat = (Statement) conn.createStatement();
		if(stat.executeUpdate(query) == 0){
			stat.close();
			return false;
		}
		stat.close();
		return true;
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
		String query = "select Email from User where UserName='"+UserName+"'";
		stat = (Statement) conn.createStatement();
		ResultSet rs = stat.executeQuery(query);
		String Email = null;
		while(rs.next()){
			Email = rs.getString("Email");
		}
		rs.close();stat.close();
		return Email;
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
		String query = "select PushTime from User where UserName='"+UserName+"'";
		stat = (Statement) conn.createStatement();
		ResultSet rs = stat.executeQuery(query);
		java.sql.Time PushTime = null;
		while(rs.next()){
			PushTime = rs.getTime(1);
		}
		rs.close();stat.close();
		return PushTime;
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
		String query = "select Password from User where UserName='"+UserName+"'";
		stat = (Statement) conn.createStatement();
		ResultSet rs = stat.executeQuery(query);
		String Password = null;
		while(rs.next()){
			Password = rs.getString(1);
		}
		rs.close();stat.close();
		return Password;
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
		String query = "select Password,Email from User where UserName='"+UserName+"'";
		stat = (Statement) conn.createStatement();
		ResultSet rs = stat.executeQuery(query);
		LinkedList<String> answer = new LinkedList<String>();	//contain the Password and Email
		while(rs.next()){
			answer.add(rs.getString(1));
			answer.add(rs.getString(2));
		}
		rs.close();stat.close();
		return answer;
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
		String query = "update User set PushTime=? where UserName=?";
		ps = conn.prepareStatement(query);
		ps.setTime(1, PushTime);
		ps.setString(2, UserName);
		if(ps.executeUpdate()==0){
			ps.close();
			return false;
		}
		ps.close();
		return true;
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
		String query = "select * from User where UserName='"+UserName+"'";
		stat = (Statement) conn.createStatement();
		ResultSet rs = stat.executeQuery(query);
		User newUser = new User(UserName);
		while(rs.next()){
			newUser.setPassword(rs.getString(2));
			newUser.setEmail(rs.getString(3));
			newUser.setPushTime(rs.getTime(4));
		}
		rs.close();stat.close();
		return newUser;
	}

	@Override
	/**
	 * @Title: IsExist
	 * @Description: Judge whether the User info in which the UserName is given has existed
	 * @param UserName
	 * @return boolean: true is exist and false is not exist
	 * @throws Exception
	 */
	public boolean isExist(String UserName) throws Exception {
		String query = "select count(*) from User where UserName='"+UserName+"'";
		stat = (Statement) conn.createStatement();
		ResultSet rs = stat.executeQuery(query);
		boolean isexist = false;
		while(rs.next()){
			if(rs.getInt(1)!=0)
				isexist = true;
		}
		rs.close();stat.close();
		return isexist;
	}

	@Override
	public void free() throws Exception {}

}
