/**
 * Created by Administrator on 2017/7/13.
 */

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * 07-13 9:39
 *
 * @author xt
 **/
public class JMail {
    private String sender;
    private String password;
    private boolean isDebug = false;

    public JMail(String sender, String password){
        this.sender = sender;
        this.password = password;
    }

    public JMail(String sender, String password, boolean isDebug){
        this.sender = sender;
        this.password = password;
        this.isDebug = isDebug;
    }

    public void send(String receiverAddress, String receiverName, String title, String content) throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.126.com");
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(isDebug);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender,"admin","UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiverAddress, receiverName, "UTF-8"));
        message.setSubject(title, "UTF-8");
        message.setContent(content,"text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();

        Transport transport = session.getTransport();
        transport.connect(sender,password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

    }




}
