 package za.co.groupA.DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.groupA.Dao.AddressDao;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Dao.UserDao;
import za.co.groupA.Model.Address;

import za.co.groupA.Model.User;

public class UserDaoImpl implements UserDao {

    private Connection con = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;
    private static UserDao userDaoImpl = null;
    List<User> allUserList;


    private UserDaoImpl(Connection con) {
        this.con = con;
        allUserList = getAllUsersFromDb();

    }
    //*****************************************************************************************************************

    public static UserDao getInstance(DBPoolManagerBasic db) {

        if (userDaoImpl == null) {
            try {
                userDaoImpl = new UserDaoImpl(db.getConnection());
            } catch (SQLException ex) {
                System.out.println("Cannot obtain a connection " + ex.getMessage());
            }
        }
        return userDaoImpl;
    }

    //*********************************************************************************************************************
    private List<User> getAllUsersFromDb() {
        List<User> userList = new ArrayList<>();
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT user.emailAddress, user.userName, user.userSurname, user.userTitle, user.idNumber, user.userTel, user.roleId, user.userPassword From user JOIN role On user.roleId = role.roleId ");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    userList.add(new User(resultSet.getString("emailAddress"),
                            resultSet.getString("userName"),
                            resultSet.getString("userSurname"),
                            resultSet.getString("userTitle"),
                            resultSet.getString("idNumber"),
                            resultSet.getString("userTel"),
                            resultSet.getInt("roleId"),
                            resultSet.getString("userPassword")
                    ));
                }
            } catch (SQLException ex) {
                System.out.println("could not get the users " + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }

        return userList;
    }

    //****************************************************************************************************************
    @Override
    public boolean addUser(User user) {
        boolean retVal = false;
        boolean inOk = true;


        if (con != null && !userExists(user.getEmailAddress())) {
            try {
                con.setAutoCommit(false);
                preparedStatement = con.prepareStatement("INSERT INTO user(emailAddress,userName,userSurname,userTitle, idNumber, userTel, roleId, userPassword) values(?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, user.getEmailAddress());
                preparedStatement.setString(2, user.getUserName());
                preparedStatement.setString(3, user.getUserSurname());
                preparedStatement.setString(4, user.getUserTitle());
                preparedStatement.setString(5, user.getIdNumber());
                preparedStatement.setString(6, user.getUserTel());
                preparedStatement.setInt(7, user.getRoleId());
                preparedStatement.setString(8, user.getUserPassword());
            } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.out.println(" could not add a user " + ex.getMessage());
                }
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex1) {
                    System.out.println(" could not add a user " + ex.getMessage());
                }
                return retVal;
            }

            try {
                if (preparedStatement.executeUpdate() > 0) {
                    System.out.println("getting in the > 0");
                    preparedStatement = con.prepareStatement("SELECT emailAddress FROM user ORDER BY emailAddress");
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        String emailAddress = user.getEmailAddress();
                        AddressDao addressDaoImpl = new AddressDaoImpl(con);
                        if (addressDaoImpl.addAddress(user.getAddress())) {
                            preparedStatement = con.prepareStatement("SELECT LAST_INSERT_ID()");
                            resultSet = preparedStatement.executeQuery();
                            int addressId = 1;

                            if (resultSet.next()) {
                                addressId = resultSet.getInt(1);
                            }

                            preparedStatement = con.prepareStatement("INSERT INTO user_address (emailAddress, addressId, isActive) VALUES (?, ?, ?)");
                            preparedStatement.setString(1, emailAddress);
                            preparedStatement.setInt(2, addressId);
                            preparedStatement.setBoolean(3, true);
                        }
                    }
                    if (preparedStatement.executeUpdate() < 1) {
                        con.rollback();
                        inOk = false;
                    }
                    if (inOk) {
                        con.commit();
                        allUserList.add(user);
                        retVal = true;
                    }

                }
            } catch (SQLException ex1) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    System.out.println(" could not add a user " + ex.getMessage());
                }
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                    System.out.println(" could not add a user " + ex.getMessage());
                }
                return retVal;
            } finally {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return retVal;

    }

    //**********************************************************************************************************************
    @Override
    public List<User> getAllUsers() {
        return new ArrayList(allUserList);
    }

    //***************************************************************************************************************
    @Override
    public User getUser(String emailAddress) {
        return allUserList.stream().filter(addre -> addre.getEmailAddress().equalsIgnoreCase(emailAddress)).findFirst().orElse(null);
    }

    //**********************************************************************************************************
    @Override
    public boolean userExists(String emailAddress) {
        return allUserList.stream().anyMatch(email -> email.getEmailAddress().equals(emailAddress));
    }

    //*********************************************************************************************************
    @Override
    public boolean deleteUser(String emailAddress, boolean delete) {

        boolean retVal = false;

        if (con != null && userExists(emailAddress)) {
            try {
                preparedStatement = con.prepareStatement("UPDATE user_address SET isActive = ? WHERE emailAddress = ?");
                preparedStatement.setBoolean(1, !delete);
                preparedStatement.setString(2, emailAddress);
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println(" could not delete user " + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        if (retVal) {
            allUserList = getAllUsersFromDb();
        }
        return retVal;
    }
    //************************************************************************************************************

    @Override
    public boolean updateUser(User user) {

        boolean retVal = false;
        if (con != null && userExists(user.getEmailAddress())) {
            try {
                preparedStatement = con.prepareStatement("UPDATE user SET userName = ? ,userSurname=?, userTitle =?, idNumber=? ,userTel=?, roleId=?,userPassword=?, isActive=? WHERE emailAddress = ?");
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getUserSurname());
                preparedStatement.setString(3, user.getUserTel());
                preparedStatement.setString(4, user.getIdNumber());
                preparedStatement.setString(5, user.getUserTel());
                preparedStatement.setInt(6, user.getRoleId());
                preparedStatement.setString(7, user.getUserPassword());
                preparedStatement.setBoolean(8, true);
                preparedStatement.setString(9, user.getEmailAddress());

                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("could not update user " + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        if (retVal) {
            allUserList = getAllUsersFromDb();
        }
        return retVal;
    }

    //***********************************************************************************************************************
    public static void main(String[] args) {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bakerySystem", "root", "root"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (con != null) {
            System.out.println("Got connection");
        }

//        System.out.println(new UserDaoImpl(con).addUser(new User("sherman@gmail", "ria", "maluta", "miss", "003", "062", 2, "pass", new Address(0, "rerr", 23, "rtstt", "ttt"), true)));
//        System.out.println(new UserDaoImpl(con).getAllUsers());
//        System.out.println(new UserDaoImpl(con).deleteUser("sherman@gmail", true));
        System.out.println(new UserDaoImpl(con).addUser(new User("risa@gmail", "dddd", "maluta", "miss", "003", "062", 2,"", new Address(0, "dd", 23, "rtsttFFF", "ttFFFt"))));
//          System.out.println(new UserDaoImpl(con).updateUser(new User("anakie@gmail", "dddd", "qqq", "hhhh", "00356533", "062", 2, "fff", new Address(0, "ddee", 23, "rtsswtt", "tsstt"), true)));
//        System.out.println(new UserDaoImpl(con).addUser(new User("riahh@gmail", "ccccd", "q", "hhh", "00533", "062", 2, "fff", new Address(0, "ee", 23, "rtsstt", "ttt"))));

    }
}
