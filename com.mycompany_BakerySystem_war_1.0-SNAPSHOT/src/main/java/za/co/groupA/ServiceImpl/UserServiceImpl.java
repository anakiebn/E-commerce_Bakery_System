package za.co.groupA.ServiceImpl;

import za.co.groupA.Dao.UserDao;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import za.co.groupA.Model.User;
import za.co.groupA.Service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean addUser(User user) throws NullPointerException {

        user.setUserPassword(BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt()));
        return userDao.addUser(user);

    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers() != null ? userDao.getAllUsers() : null;
    }

    @Override
    public User getUser(String userEmail) {
        return userDao.getUser(userEmail.trim());
    }

    @Override
    public boolean updateUser(User user) {
        user.setUserPassword(BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt()));
        return userDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(String emailAddress, boolean delete) {
        return userDao.deleteUser(emailAddress, delete);
    }

    @Override
    public boolean userExists(String emailAddress) {
        return userDao.userExists(emailAddress);
    }

}
