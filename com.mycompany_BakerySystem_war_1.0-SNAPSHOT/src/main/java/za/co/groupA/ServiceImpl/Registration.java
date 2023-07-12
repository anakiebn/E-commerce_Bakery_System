package za.co.groupA.ServiceImpl;

import java.util.UUID;
import javax.mail.MessagingException;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.UserDaoImpl;
import za.co.groupA.Model.User;
import za.co.groupA.Service.UserService;

public class Registration {

    private String confirmationCode;
    private User user;
    private final UserService userService;

    public Registration(DBPoolManagerBasic db, User user) {
        this.user = user;
        if (db == null) {
            throw new RuntimeException("ERROR: no database manager supplied. ");
        }

        userService = new UserServiceImpl(UserDaoImpl.getInstance(db));

    }

    public boolean sendCode() {
        boolean retVal = false;
        if (user == null) {
            throw new RuntimeException("ERROR: No user found.");
        }
        if (!userService.userExists(user.getEmailAddress())) {
            UUID uuid = UUID.randomUUID();
            confirmationCode = uuid.toString().substring(0, 8);
            try {
                retVal = new EmailServiceImpl().sendRegistrationEmail(user.getUserName(), user.getEmailAddress(), confirmationCode);
            } catch (MessagingException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return retVal;
    }

    public final String getCode() {
        return confirmationCode;
    }

    public boolean registerUser() {
        return userService.addUser(user);
    }

}
