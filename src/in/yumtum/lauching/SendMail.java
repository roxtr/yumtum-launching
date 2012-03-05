package in.yumtum.lauching;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	private String from;
	private String to;
	private String subject;
	private String text;
	
	public SendMail(String from, String to, String subject, String text){
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
	}
	
	public void send(){
		Message simpleMessage = new MimeMessage(getSession());
		
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;
		try {
			fromAddress = new InternetAddress(from);
			toAddress = new InternetAddress(to);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			simpleMessage.setFrom(fromAddress);
			simpleMessage.setRecipient(RecipientType.TO, toAddress);
			simpleMessage.setSubject(subject);
			simpleMessage.setContent(text,"text/html");
			
			Transport.send(simpleMessage);			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private Session getSession() {
		Authenticator authenticator = new Authenticator();

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.auth", "true");

		properties.setProperty("mail.smtp.host", "smtp.yumtum.in");
		properties.setProperty("mail.smtp.port", "25");

		return Session.getInstance(properties, authenticator);
	}
	
	private class Authenticator extends javax.mail.Authenticator {
		private PasswordAuthentication authentication;

		public Authenticator() {
			String username = "hello@yumtum.in";
			String password = "ujjwala@TCS1";
			authentication = new PasswordAuthentication(username, password);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}
	
	public static void main(String[] args) {
		String urefId="1234";
		String from = "hello@yumtum.in";
		String to = "makamhareesh@gmail.com";
		String subject = "Thanks for signing Up";
		String message = "<div>Hi "+to+", <p>Thanks for coming back. We will contact you shortly with a beta invite.</p><p>In the meantime, you can follow us on <span><a href='www.twitter.com/yumtumindia'>Twitter</a></span>  or on <span><a href='www.facebook.com/yumtumindia'>Facebook</a></span> to get the inside scoop .</p></div>" +
				"<p>Go ahead and share the URL ( http://www.yumtum.in/?refId="+urefId+" )with your friends and spread the joy.</p><p>\n \n \n \n - Team Yumtum</p>";

		SendMail sendMail = new SendMail(from, to, subject, message);
		sendMail.send();
	}
	
	
}
