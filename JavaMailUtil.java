package sample1;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
public class JavaMailUtil {

	public static void sendMail(String recepient) throws Exception
	{
		System.out.println("prepairing to sent Mail");
	Properties properties = new Properties();
	properties.put("mail.smtp.auth","true");
	properties.put("mail.smtp.starttls.enable","true");
	properties.put("mail.smtp.host","smtp.gmail.com");
	properties.put("mail.smtp.port","587");
	String myAccountEmail="dhruvil.shah1999@gmail.com";
	String password="Dhruvil8735";
	Session session=Session.getInstance(properties,new Authenticator(){
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(myAccountEmail,password);
		}
	});
	Message message = prepareMessage(session,myAccountEmail,recepient);
	Transport.send(message );
	System.out.println("message sent successfully");
	
	}
	private static Message prepareMessage(Session session,String myAccountEmail,String recepient) {
		try {
		Message message=new MimeMessage(session);
		message.setFrom(new InternetAddress(myAccountEmail));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
		message.setSubject("My first email form java app");
		message.setText("Hey there,\n look my Email");
		return message;
		}catch(Exception ex) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
}
