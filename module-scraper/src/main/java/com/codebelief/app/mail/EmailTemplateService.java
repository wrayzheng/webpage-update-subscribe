package com.codebelief.app.mail;

import java.util.Map;

/**
 * 郵件模板服務
 * @author Administrator
 *
 */
public interface EmailTemplateService {
	/**
	 * 模板引擎初始化
	 */
	public void init();
	/**
	 * 獲取模板內容
	 * @param templateId
	 * @param parameters
	 * @return
	 */
	public abstract String getText(String templateId,
			Map<Object, Object> parameters);

}
