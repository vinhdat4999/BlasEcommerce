package com.blas.blasecommerce.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

    public void sendEmail(String email, String title, String content) {
        String HOST_NAME = "smtp.gmail.com";

        final int SSL_PORT = 465; // Port for SSL

        final int TSL_PORT = 587; // Port for TLS/STARTTLS

        final String APP_EMAIL = "blascoffeevn@gmail.com"; // your email

        final String APP_PASSWORD = "0546550566Qq"; // your password
        String RECEIVE_EMAIL = email;
        // 1) get the session object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", HOST_NAME);
        props.put("mail.smtp.socketFactory.port", SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", SSL_PORT);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
            }
        });

        // 2) compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(APP_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(RECEIVE_EMAIL));
            message.setSubject(title, "utf8");

            // 3) create MimeBodyPart object and set your message text
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
//            messageBodyPart1.setText(content, "utf8");
            messageBodyPart1.setContent(content, "text/html; charset=utf-8");

            // 4) create new MimeBodyPart object and set DataHandler object to this object
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
