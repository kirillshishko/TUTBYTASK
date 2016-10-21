package by.tut.helperClasses;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


public class EmailOperations {
    private static final String SMTP_HOST = "smtp.yandex.ru";
    private static final String IMAP_HOST = "imap.yandex.ru";
    private static final Integer SMTP_PORT = 465;
    private static final Integer IMAP_PORT = 465;
    private static final String IMAP_SENT_FOLDER_NAME = "Отправленные";
    private static final String IMAP_STORE_TYPE = "imaps";
    private static final String ENCODING = "UTF-8";


    public static void sendSimpleMessage(String user, String password, String sender, String reciever,
                                         String subject, String content)
            throws MessagingException, UnsupportedEncodingException {


        Authenticator auth = new EmailOperations.MyAuthenticator(user, password);

        Session session = Session.getInstance(getEmailConfig(), auth);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sender));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(reciever));
        msg.setSubject(subject);
        msg.setText(content);
        Transport.send(msg);

        addMessageToSentFolder(msg, session, user, password);
    }


    private static void addMessageToSentFolder(Message message, Session session, String login, String password) throws MessagingException {
        IMAPStore store = (IMAPStore) session.getStore(EmailOperations.IMAP_STORE_TYPE);
        store.connect(EmailOperations.IMAP_HOST, login, password);
        IMAPFolder folder = (IMAPFolder) store.getDefaultFolder().getFolder(EmailOperations.IMAP_SENT_FOLDER_NAME);
        if (folder.exists() || folder.create(IMAPFolder.READ_WRITE)) {
            if (!folder.isOpen()) {
                folder.open(Folder.READ_WRITE);
            }

            Message msg = new MimeMessage((MimeMessage) message);

            msg.setFlag(Flags.Flag.SEEN, true);
            folder.appendMessages(new Message[]{msg});
            folder.close(false);
            store.close();
        }
    }

    private static class MyAuthenticator extends Authenticator {
        private String user;
        private String password;

        MyAuthenticator(String user, String password) {
            this.user = user;
            this.password = password;
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String user = this.user;
            String password = this.password;
            return new PasswordAuthentication(user, password);
        }
    }

    private static Properties getEmailConfig() {
        Properties properties = System.getProperties();

        properties.put("mail.smtp.port", SMTP_PORT.toString());
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.port", SMTP_PORT.toString());
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.mime.charset", ENCODING);
        properties.put("mail.imap.port", IMAP_PORT.toString());
        properties.put("mail.imap.host", IMAP_HOST);
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imap.ssl.enable", "true");


        return properties;
    }
}
