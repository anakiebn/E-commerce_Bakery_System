package za.co.groupA.ServiceImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Model.Order;
import za.co.groupA.Service.OrderService;

public class EmailServiceImpl {

    private final String senderEmail;
    private final String senderPassword;
    private DBPoolManagerBasic db;

    public EmailServiceImpl(String senderEmail, String senderPassword) {
        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;
    }

    public EmailServiceImpl() {
        this("topieforbakery2@gmail.com", "jmswyppsiiadpexj");
    }

    public EmailServiceImpl(DBPoolManagerBasic db) {
        this("topieforbakery2@gmail.com", "jmswyppsiiadpexj");
        this.db = db;

    }

    private boolean sendEmail(String recipientEmail, String subject, String body) throws MessagingException {
        boolean sentEmail = false;
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try (Transport transport = session.getTransport()) {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());

            System.out.println("Email sent successfully!");
            sentEmail = true;
        }
        return sentEmail;
    }

    public boolean sendRegistrationEmail(String userName, String recipientEmail, String confirmationCode) throws MessagingException {
        String subject = "Welcome to ToPieFor Online Bakery - Account Registration Confirmation";
        StringBuilder bodyBuilder = new StringBuilder();

        bodyBuilder.append("Dear ").append(userName).append(",\n\nThank you for joining ToPieFor Online Bakery ! Your account registration is nearly complete.")
                .append("Use this code to activate your account: ")
                .append(confirmationCode).append("\n")
                .append("If you have any questions or need assistance, please contact our support team at 0606729075.\n\n")
                .append("Welcome aboard\n\nToPieFor Bakery Team");
        String body = bodyBuilder.toString();

        return sendEmail(recipientEmail, subject, body);
    }

    public void sendPasswordRecoveryEmail(String recipientEmail, String confirmationCode) throws MessagingException {
        String subject = " ToPieFor Password Recovery System";
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("Click the following link to reset your password:\n")
                .append("Use this code to activate your account")
                .append(confirmationCode)
                .append("If you have any questions or need assistance, please contact our support team at 0607629075.\n\n")
                .append("Welcome aboard\n\nToPieFor Bakery Team");

        String body = bodyBuilder.toString();

        sendEmail(recipientEmail, subject, body);
    }

    public boolean sendInvoiceEmail(Order order) throws MessagingException {

        StringBuilder bodyBuilder = new StringBuilder();
       

        String subject = "";

        if (order!= null) {
            subject = "ToPieFor Invoice";
            bodyBuilder.append(order.getProducts_In_A_Cart());
            
            
        } else {
            bodyBuilder.append("Order failed");

            subject = "ToPieFor Payment Error MSG";
        }
        String body = bodyBuilder.toString();

        return sendEmail(order.getEmailAddress(), subject, body);
    }

}
