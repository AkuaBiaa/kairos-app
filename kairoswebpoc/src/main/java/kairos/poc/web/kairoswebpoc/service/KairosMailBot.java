package kairos.poc.web.kairoswebpoc.service;


import kairos.poc.web.kairoswebpoc.utility.Securitas;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * //// TODO: 9/12/18 Emails the points accumulated for the day
 * //// TODO: 9/12/18 Have the total points in the subject for the day.
 * //// TODO: 9/12/18 Have the task,points,and time detail in the body for the day.
 * //// TODO: 9/12/18 JavaTime to send points periodically 11:59PM, and 6PM.
 * //// TODO: 9/13/18 Store auth in properties file instead if java object, use encrypter to encrypt/decrypt from HR
 */
public class KairosMailBot {
    private static Securitas securitas;
    private String username;
    private String password;

    public static void main(String[] args) {
        KairosMailBot mailBot = new KairosMailBot();

        mailBot.setUsername(mailBot.getSecuritas().getPass());
        mailBot.setPassword(mailBot.getSecuritas().getPass());

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailBot.getUsername(), mailBot.getPassword());
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailBot.getUsername()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailBot.getUsername()));
            message.setSubject("Testing Points Sedining Points. Time:"+ new Date().toString());
            message.setText("Dear Kairos Bot,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public Securitas getSecuritas() {
        return securitas;
    }

    public void setSecuritas(Securitas securitas) {
        this.securitas = securitas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
