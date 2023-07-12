
package za.co.groupA.Service;

import java.util.List;
import za.co.groupA.Model.User;

public interface UserService {
    
    public boolean addUser(User user);

    public List<User> getAllUsers();

    public User getUser(String emailAddress);

    public boolean deleteUser(String emailAddress, boolean delete);

    public boolean updateUser(User user);
    
     public boolean userExists(String emailAddress);
    
}

