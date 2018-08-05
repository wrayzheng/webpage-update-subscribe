package com.codebelief.app;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.codebelief.app.mail.SendMail;

/**
 * @author: Wray Zheng
 * @date: 2017-12-05
 * @description: 封装用户注册信息至对应  URL，用于邮箱验证。或从验证 URL 中提取用户注册信息。
 */
public class SignupInfoHandler {
	final private static String password = "82920385948273940392871948572939";
	final private static String actionUrl = "http://app.codebelief.com/webpage-update-subscribe/emailConfirm.do";
	
	/**
	 * 从加密 URL 参数中获得用户注册信息
	 * 返回值为字符串数组，依次为 userName、email、password
	 * @throws Exception 
	 */
	public static String[] getSignupInfo(String paramEncoded) throws Exception {
		String paramsDecoded = SignupInfoHandler.decodeAndDecrypted(paramEncoded);
		String[] signupInfo = paramsDecoded.split(";");
		return signupInfo;
	}
	
	/* 发送验证邮件 */
	public static void sendConfirmEmail(String userName, String email, String password) throws Exception {
		// 生成验证 URL，以分号隔开各个参数
		String param = userName + ";" + email + ";" + password;
		String paramsEncrypted = encryptAndEncode(param);
		String url = actionUrl + "?info=" + paramsEncrypted;
		
		// 发送验证邮件
		Map<Object, Object> parameter = new HashMap<>();
		parameter.put("url", url);
		SendMail.sendMail("signup", "欢迎注册网页更新订阅系统", email, parameter);

	}
	
	/**
	 * 替换 Base64 中的特殊字符
	 * 将 /、+、=替换为-、_、~
	 */
	public static String Base64ToUrl(String base64) {
		return base64.replace('/', '-').replace('+', '_').replace('=', '~');
	}
	
	/**
	 * 将 URL 转换回 Base64 编码
	 */
	public static String UrlToBase64(String base64) {
		return base64.replace('-', '/').replace('_', '+').replace('~', '=');
	}
	
	/**
	 * 加密并编码为 Base64
	 * @throws Exception 
	 */
	public static String encryptAndEncode(String source) throws Exception {
		byte[] encrypted = DES.encrypt(source.getBytes(), password);
		byte[] encoded = Base64.encodeBase64(encrypted);
		String strEncoded = Base64ToUrl(new String(encoded));
		return strEncoded;
	}
	
	/**
	 * 解码 Base64 并解密
	 * @throws Exception 
	 */
	public static String decodeAndDecrypted(String encoded) throws Exception {
		String strDecoded = UrlToBase64(encoded);
		byte[] decoded = Base64.decodeBase64(strDecoded);
		byte[] decrypted = DES.decrypt(decoded, password);
		return new String(decrypted);
	}
}
