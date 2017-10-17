/*
 * @(#)SeeUTomorrow --- ContentDAOFactory.java 
 */
package com.codebelief.app.DAOFactory;

import com.codebelief.app.DAO.ContentDAOProxy;
import com.codebelief.app.DAO.IContentDAO;
/**
 * 
 * @ClassName: ContentDAOFactory
 * @Description: Return an instantiated UrlDAO
 * @author 何涛
 * @date 2017年10月14日
 *
 */
public class ContentDAOFactory {
	public static IContentDAO getContentDAOInstance() throws Exception{
		return new ContentDAOProxy();
	}
}
