
package util;

import java.util.Properties;  
  
import javax.mail.*;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
  
public class MailUtil {  
public static void send(String to,String subject,String msg){  
  
    final String user="comparadordeportatilesweb@gmail.com"; 
    final String pass="ohbphszfwulgkxvp";  
    
    Properties props = new Properties();  
    props.put("mail.smtp.host", "smtp.gmail.com");  
    props.put("mail.smtp.port", "587"); 
    props.put("mail.smtp.auth", "true");  
    props.put("mail.smtp.starttls.enable", "true"); 

    Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
    protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,pass);  
    }  
    });  
    try {  
        MimeMessage message = new MimeMessage(session);  
        message.setFrom(new InternetAddress(user));  
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
        message.setSubject(subject);  
        message.setText(msg);  
        
        Transport.send(message);  
                
        } catch (MessagingException e) {  
            throw new RuntimeException(e);  
        }  
            
    }  
}  