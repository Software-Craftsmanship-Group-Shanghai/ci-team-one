package com.dandelion.springTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 邮件工具类
 * @author liuzhenguo
 *
 */
public class MailUtil {

	public static final String MAILHOST = "mail.smtp.host";
	public static final String MAILAUTH = "mail.smtp.auth";
	public static final String WRAP = "<br>";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String FROMMAIL = "frommail";
	public static final String CHARSET = "charset";
	public static final String PREFIX = "prefix";
	public static final String RECEIVERNAME = "用户";
	
	
	/**
	   * 乱码处理
	   * @param temp
	   * @return
	   */
	private static String correctDisplay(String temp) {
		try {
			temp = new String(temp.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	/** 
     * 把主题转换为中文 
     *  
     * @param strText 
     * @return 
     */  
    public static String transferChinese(String strText) {  
  
        try {  
            strText = MimeUtility.encodeText(new String(strText.getBytes(),  
                    "UTF-8"), "UTF-8", "B");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return strText;  
    }  
	
	/**
	 * 发送邮件
	 * @param userInfo 根据邮箱查找出的用户
	 * @param toMail 发件人的email
	 * @return 是否发送邮件成功
	 * @throws UnsupportedEncodingException 
	 */
	public static boolean sendEmail(UserInfo userInfo, String toMail, String key) throws UnsupportedEncodingException {
		String receiveUser = userInfo.getAlias();
		if (receiveUser == null) receiveUser = RECEIVERNAME;
		Session session = Session.getInstance(getSessionProperties(),new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(getProperties().getProperty(USERNAME), 
								getProperties().getProperty(PASSWORD));
						}});
		MimeMessage message;
		try {
			String subject = getEmailHeah(receiveUser);
			message = new MimeMessage(session);
			String setFromEmail = getProperties().getProperty(FROMMAIL);
			message.setFrom(new InternetAddress(setFromEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
			message.setSubject(correctDisplay(subject));
			message.setSentDate(new Date());
			String body = getEmailBody(receiveUser, toMail, key);
			message.setContent(correctDisplay(body), "text/html;charset=utf-8");
			
			// 发送邮件；
			Transport.send(message);
			log.debug(" send email success ");
			return true;
		} catch (AddressException e) {
			log.error("AddressException" + e.getMessage());
		} catch (MessagingException e) {
			log.error("MessagingException" + e.getMessage());
		}
		return false;
	}
	
	public static boolean sendMailViaSpring(UserInfo userInfo, String toMail, String key) throws Exception{
		String receiveUser = userInfo.getAlias();
		if (receiveUser == null) {
			receiveUser = RECEIVERNAME;
		}
		
		
		
		
		String subject = getEmailHeah(receiveUser);
		String body = getEmailBody(receiveUser, toMail, key);
		String setFromEmail = getProperties().getProperty(FROMMAIL);
	    JavaMailSenderImpl senderImpl = new JavaMailSenderImpl(); 
	    senderImpl.setUsername(getProperties().getProperty(USERNAME)) ; // 根据自己的情况,设置username
	    senderImpl.setPassword(getProperties().getProperty(PASSWORD)) ; // 根据自己的情况, 设置password
	    Properties prop = getSessionProperties();
	    prop.put("mail.smtp.timeout", "25000") ; 
	    senderImpl.setJavaMailProperties(prop); 
	    senderImpl.setHost(getSessionProperties().getProperty(MAILHOST)); 
	    
	    MimeMessage mailMessage = senderImpl.createMimeMessage(); 
	    MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");  
	    messageHelper.setTo(toMail); 
	    messageHelper.setFrom(setFromEmail); 
	    messageHelper.setSubject(subject); 
	    messageHelper.setText(body,true);
	    //发送邮件 
	    senderImpl.send(mailMessage); 
	    return true;
	} 
	/**
	 * 获取session的properties
	 * @return 配置信息
	 */
	public static Properties getSessionProperties() {
		Properties pro = new Properties();
		pro.put(MAILHOST, getProperties().get(MAILHOST));
		pro.put(MAILAUTH, getProperties().get(MAILAUTH));
		return pro;
	}
	/**
	 * 获取邮件的文件头
	 * @param userName 用户的名字
	 * @return 返回邮件的标题内容
	 */
	public static String getEmailHeah(String userName) {
		StringBuffer sb = new StringBuffer();
		sb.append(userName).append(",您的找回密码邮件");
		return sb.toString();
	}

	/**
	 * 获取邮件主体内容
	 * @param userName 用户名
	 * @param email 用户的邮箱
	 * @return 主体内容
	 */
	public static String getEmailBody(String userName, String email, String key) {
		StringBuffer sb = new StringBuffer();
		sb.append("<html><head>")
		  .append("<meta http-equiv='content-type' content='text/html; charset=utf-8'>")
		  .append("</head><body>")
			.append("您好,").append(userName)
			.append(" : ").append(WRAP).append("您申请重置账号，如非本人操作，请忽略此邮件。")
			.append(WRAP).append(WRAP).append("立即重置密码,")
			.append("请点击下面的地址:")
			.append("<a href=" + "\"" + getKeyStr(email,key) + "\"" + ">")
			.append(" 请点击这里 ").append("</a>").append("完成修改密码").append(WRAP)
			.append(getKeyStr(email,key)).append(WRAP).append(WRAP)
			.append("祝您使用愉快！").append(WRAP);
		sb.append("").append("</body></html>");
		log.debug("send to user is " + sb.toString());
		return sb.toString();
	}

	/**
	 * 获取邮件链接的问好后面的串
	 * @param email 用户的邮箱
	 * @return 返回链接的串
	 */
	public static String getKeyStr(String email, String key) {
		String requestAddressPrefix = getProperties().getProperty(PREFIX);
		StringBuffer sb = new StringBuffer();
		sb.append(requestAddressPrefix).append("?").append("key=")
				.append(key);
		//.append("&").append("email=").append(email)
		String result = sb.toString();
		return result;
	}

	/**
	 * 对字符串加密
	 * @param email 用户的邮箱
	 * @return 加密串
	 */
	public static String spliceString(String email) {
		//根据需求加密带在用户链接的url参数
		String emailSplice = email + DateUtil.getDate();
		log.info("the splice string insert into db is " + emailSplice);
		String result = Utils.getMD5(emailSplice);
		return result;
	}

	/**
	 * 获取配置文件的信息
	 * @return 配置文件内容
	 */
	public static Properties getProperties() {
		Properties pro = new Properties();
		InputStream in = MailUtil.class.getClassLoader().getResourceAsStream(
				"email.properties");
		try {
			pro.load(in);
		} catch (IOException e) {
			log.error("load the email properties fail");
		}
		return pro;
	}
	private static final Logger log = LoggerFactory.getLogger(MailUtil.class);
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		UserInfo userInfo = new UserInfo();
		userInfo.setAlias("dandelion");
		String email = "wow2009zfx@gmail.com";
		String key = spliceString(email);
		sendEmail(userInfo, email, key);
	}
}
