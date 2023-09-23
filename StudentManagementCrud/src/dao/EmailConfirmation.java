package dao;

/**
 * Created by ASUS on 22-Sep-23.
 */
    import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
    import java.util.Properties;
    import javax.mail.*;
    import javax.mail.internet.*;


public class EmailConfirmation {
    public void sendEmail(String recipientEmail,String subject,String message){


        // Gmail SMTP server settings
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "sabilhasan2018@gmail.com"; // Your Gmail email address
        String password = "password"; // The App Password you generated

        // Set up JavaMail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // Create a Session instance
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            // Send the email
            Transport.send(mimeMessage);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EmailConfirmation email = new EmailConfirmation();
        email.sendEmail("thschowdhury2020@gmail.com","Start working No chill","WHATS UPPP");

    }
}


