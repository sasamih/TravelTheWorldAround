package net.etfbl.email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendMail
{
   public static void main(String [] args)
   {    
      // Recipient's email ID needs to be mentioned.
      String to = "sasamihajlica@gmail.com";

      // Sender's email ID needs to be mentioned
      final String from = "sasamihajlica@gmail.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
     // Properties properties = System.getProperties();

      // Setup mail server
      //properties.setProperty("mail.smtp.host", host);

      final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
      // Get a Properties object
         Properties props = System.getProperties();
         props.setProperty("mail.smtp.host", "smtp.gmail.com");
         props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
         props.setProperty("mail.smtp.socketFactory.fallback", "false");
         props.setProperty("mail.smtp.port", "465");
         props.setProperty("mail.smtp.socketFactory.port", "465");
         props.put("mail.smtp.auth", "true");
         props.put("mail.debug", "true");
         props.put("mail.store.protocol", "pop3");
         props.put("mail.transport.protocol", "smtp");
      
      // Get the default Session object.
     // Session session = Session.getDefaultInstance(props);

      try{
    	  
    	  Session session = Session.getDefaultInstance(props, 
                  new Authenticator(){
                     protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, "pass");
                     }});
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Now set the actual message
         message.setText("This is actual message");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}
