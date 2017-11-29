package application;



import application.MessageNotSentException;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


/**
 * Created by Myles on 10/6/17.
 */
public class Email {

    private String emailAddress;
    private String emailPassword;

    private String message = "";

    private final int SMTP = 0;
    private final int IMAP = 1;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public void openCredentials()
    {
        ObjectIO objectIO = new ObjectIO(new File("credentials.crd"));
        String[] credentials = (String[])objectIO.readObject();
        setEmailAddress(credentials[0]);
        setEmailPassword(credentials[1]);
    }

    public void writeCredentials()
    {
        ObjectIO objectIO = new ObjectIO(new File("credentials.crd"));
        String[] credentials = {getEmailAddress(), getEmailPassword()};
        objectIO.writeObject(credentials);
        emailAddress = credentials[0];
        emailPassword = credentials[1];
    }

    public Session login(int smtpOrImap)
    {
        Properties props = new Properties();

        if(smtpOrImap == SMTP) {
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
        }
        else if(smtpOrImap == IMAP)
        {
            props.put("mail.imap.auth", "true");
            props.put("mail.imap.starttls.enable", "true");
            props.put("mail.imap.host", "pop.gmail.com");
            props.put("mail.smtp.port", "995");
        }

        javax.mail.Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(getEmailAddress(), getEmailPassword());
                    }
                });
        return session;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void sendEmail() throws MessageNotSentException {
        try {
            InternetAddress address = new InternetAddress("inventoryexception@gmail.com");
            Session session = login(SMTP);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("shadyshellcorp@gmail.com", "Receipt"));
            message.setRecipient(Message.RecipientType.TO,
                    address);
            message.setSubject("Purchase Confirmation");
            //message.setContent("You have a letter", "text/html");
            message.setContent(this.message, "text/html");
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
            throw new MessageNotSentException();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
            throw new MessageNotSentException();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new MessageNotSentException();
        }
    }



   
}
