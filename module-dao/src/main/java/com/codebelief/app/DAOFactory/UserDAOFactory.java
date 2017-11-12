package com.codebelief.app.DAOFactory;

import com.codebelief.app.DAO.IUserDAO;
import com.codebelief.app.DAO.UserDAOProxy;
/**
 * 
 * @ClassName: UserDAOFactory
 * @Description: Return an instantiated UserDAO
 * @author 何涛
 * @date 2017年10月14日
 *
 */
public class UserDAOFactory {
	public static IUserDAO getUserDAOInstance() throws Exception{
		return new UserDAOProxy();
	}
}
