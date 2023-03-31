package com.bd.userusasmspanel.smspanel.Services;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class UserEmailServises {


    public boolean MailSender(String subject,String Massage,String to) throws MessagingException {
        boolean type=false;

        String from = "volpnonenumber@gmail.com";

        String host="smtp.gmail.com";
        Properties properties=System.getProperties();
        System.out.println("Properties"+properties);

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

       Session session= Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(" volpnonenumber@gmail.com",
                        "pfnhimiwpbzqertt");
            }
        });
       session.setDebug(true);
        MimeMessage message=new MimeMessage(session);
       try {
           message.setFrom(from);
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
           message.setSubject(subject);
           message.setText(Massage);
           Transport.send(message);

           type=true;
       }
       catch (Exception exception){
           exception.printStackTrace();
       }
    return type;

    }
}
