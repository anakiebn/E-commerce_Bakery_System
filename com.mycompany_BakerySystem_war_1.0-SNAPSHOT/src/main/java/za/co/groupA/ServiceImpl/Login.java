package za.co.groupA.ServiceImpl;

import java.util.UUID;
import javax.mail.MessagingException;

import org.mindrot.jbcrypt.BCrypt;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.UserDaoImpl;
import za.co.groupA.Model.User;
import za.co.groupA.Service.UserService;

public class Login {

    private final UserService userService;
    private String confirmationCode;
    private final String emailAddress;
    private final String password;

    public Login(String emailAddress,String password,DBPoolManagerBasic db) {
        this.password=password;
        this.emailAddress=emailAddress;
        if (db == null) {
            throw new RuntimeException("ERROR: no database manager supplied. ");
        }

        userService = new UserServiceImpl(UserDaoImpl.getInstance(db));
    }

    public boolean passwordsMatch() {
        return BCrypt.checkpw(password, userService.getUser(emailAddress).getUserPassword());
    }

    public boolean userExists(){
        return userService.userExists(emailAddress);
    }
    public User getUser(){
        return userService.getUser(emailAddress);
    }
 
    public boolean sendCode() {

        confirmationCode = UUID.randomUUID().toString().substring(0, 8);
        try {
            new EmailServiceImpl().sendPasswordRecoveryEmail(emailAddress, confirmationCode);
        } catch (MessagingException ex) {
            System.out.println("Failed to sendPasswordRecoveryEmail: " + ex.getMessage());
        }
        return true;
    }

    public String getCode() {
        return confirmationCode;
    }
}
