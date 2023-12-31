package utils;

import javax.mail.*;
import javax.mail.internet.*;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

/**
 * A utility class for sending emails using JavaMail and Gmail's SMTP server.
 */
public class EmailSender {

    /**
     * Sends an email with the specified recipient, subject, and body.
     *
     * @param to      The recipient's email address.
     * @param subject The subject of the email.
     * @param body    The body content of the email.
     */
    public static void sendEmail(String to, String subject, String body) {
        final String from = "ensf480proj@gmail.com"; // Your email address
        final String password = "zjyh vfek tqxm plhj"; // Your email APP password (Not your regular password as it won't
                                                       // work
        // with 2FA)

        // Set properties for the mail session
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Gmail's SMTP host
        props.put("mail.smtp.port", "587"); // Gmail's SMTP port (TLS)
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable TLS

        // Get the default Session object
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
