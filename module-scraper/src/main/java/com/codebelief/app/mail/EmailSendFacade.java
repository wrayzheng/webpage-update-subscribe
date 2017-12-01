package com.codebelief.app.mail;

/**
 * �ʼ����������࣬���ڿͻ���ֱ�ӵ���
 * @author Administrator
 *
 */
public class EmailSendFacade {
	private EmailTemplateService emailTemplateService;
	private EmailServer emailServer;
	public void setEmailTemplateService(EmailTemplateService emailTemplateService) {
		this.emailTemplateService = emailTemplateService;
	}
	public void setEmailServer(EmailServer emailServer) {
		this.emailServer = emailServer;
	}
	/**
	 * �����ʼ�
	 * @param emailInfo �ʼ�������װ��emailInfo��title��content�ֶε�ֵ��������Ϊʵ�ʵ�ֵ
	 */
	public void send(EmailInfo emailInfo){
		String title = emailTemplateService.getText("title", emailInfo.getParameters());
		String content = emailTemplateService.getText("body", emailInfo.getParameters());
		emailInfo.setContent(content);
		emailInfo.setTitle(title);
		
		//调试输出邮件主体内容
		//System.out.println(content);
		
		emailServer.send(emailInfo);
	}
}
