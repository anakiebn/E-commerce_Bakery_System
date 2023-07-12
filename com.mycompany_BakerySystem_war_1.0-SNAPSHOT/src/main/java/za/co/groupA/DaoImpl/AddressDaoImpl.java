package za.co.groupA.DaoImpl;

import za.co.groupA.Dao.AddressDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Model.Address;

public class AddressDaoImpl implements AddressDao {

    public AddressDaoImpl() {
    }

    private Connection con = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;
    private static AddressDao addressDaoImpl = null;
    private List<Address> allAddressList;

    // ********************************************************************************************
    public AddressDaoImpl(Connection con) {
        this.con = con;
        allAddressList = getAllAddressesFromDb();

    }
    // ********************************************************************************************

    public static AddressDao getInstance(DBPoolManagerBasic db) {
        if (addressDaoImpl == null) {
            try {
                addressDaoImpl = new AddressDaoImpl(db.getConnection());
            } catch (SQLException ex) {
                System.out.println("Cannot obtain a connection");
            }
        }
        return addressDaoImpl;
    }

    //*************************************************************************************************
    public static AddressDao getAddressDaoImplInstance() {
        return addressDaoImpl;
    }

    // ********************************************************************************************
    private List<Address> getAllAddressesFromDb() {
        List<Address> addressList = new ArrayList<>();
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT AddressId, streetName, houseNumber, town, postalCode FROM address");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    addressList.add(new Address(resultSet.getInt("addressId"), resultSet.getString("streetName"), resultSet.getInt("houseNumber"), resultSet.getString("town"), resultSet.getString("postalCode")));
                }
            } catch (SQLException ex) {
                System.out.println("ERROR!" + ex.getMessage());
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
        return addressList;
    }
    // ********************************************************************************************

    @Override
    public boolean addAddress(Address address) {
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("INSERT INTO address( AddressId, streetName, houseNumber, town, postalCode) values(?,?,?,?,?)");
                preparedStatement.setInt(1, address.getAddressId());
                preparedStatement.setString(2, address.getStreetName());
                preparedStatement.setInt(3, address.getHouseNumber());
                preparedStatement.setString(4, address.getTown());
                preparedStatement.setString(5, address.getPostalCode());
                if (preparedStatement.executeUpdate() > 0) {
                    allAddressList.add(address);
                    return true;
                }
            } catch (SQLException ex) {
                System.out.println("could not add the address: " + ex.getMessage());
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
        return false;
    }
    // ********************************************************************************************

    @Override
    public List<Address> getAllAddresses() {
        return new ArrayList(allAddressList);
    }
    // ********************************************************************************************

    public boolean exists(int addressId) {
        return allAddressList.stream().anyMatch(add -> add.getAddressId() == (addressId));
    }
    // ********************************************************************************************

    @Override
    public Address getAddress(int addressId) {
        return allAddressList.stream().filter(add -> add.getAddressId() == addressId).findFirst().orElse(null);
    }


    //*******************************************************************************************************
    public List<Address> getAddressByUserEmail(String emailAddress) {
        List<Address> address = new ArrayList<>();
        if (con != null) {
            try {

                preparedStatement = con.prepareStatement("SELECT addressId FROM user_address WHERE emailAddress  = ? ");
                preparedStatement.setString(1, emailAddress);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int addressId = resultSet.getInt("addressId");
                    getAllAddresses().stream().filter(a -> (a.getAddressId() == addressId)).forEachOrdered(a -> {
                        address.add(a);
                    });

                }
            } catch (SQLException ex) {
                System.out.println("could not get the addresses");
            }

        }
        return address;
    }

    // ********************************************************************************************
    @Override
    public boolean updateAddress(Address address) {
        boolean retVal = false;
        if (con != null && exists(address.getAddressId())) {
            try {
                preparedStatement = con.prepareStatement("UPDATE address SET streetName = ? , houseNumber = ?, town = ? , postalCode = ? WHERE addressId = ?");
                preparedStatement.setString(1, address.getStreetName());
                preparedStatement.setInt(2, address.getHouseNumber());
                preparedStatement.setString(3, address.getTown());
                preparedStatement.setString(4, address.getPostalCode());
                preparedStatement.setInt(5, address.getAddressId());
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("could not update the address " + ex.getMessage());
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
            allAddressList = getAllAddressesFromDb();

        }
        return retVal;
    }
    // ********************************************************************************************

    @Override
    public boolean deleteAddress(int addressId, boolean delete) {
        boolean retVal = false;

        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("UPDATE user_address set isActive = ? WHERE addressId = ?");
                preparedStatement.setBoolean(1, !delete);
                preparedStatement.setInt(2, addressId);

                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println(" could not delete the address " + ex.getMessage());
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
        return retVal;
    }

    @Override
    public boolean addAddressForExistingUser(String emailAddress, Address address) {
        boolean retVal = false;
        boolean inOk = true;
        if (con != null) {
            try {
                con.setAutoCommit(false);
                preparedStatement = con.prepareStatement("INSERT INTO address( AddressId, streetName, houseNumber, town, postalCode) values(?,?,?,?,?)");
                preparedStatement.setInt(1, address.getAddressId());
                preparedStatement.setString(2, address.getStreetName());
                preparedStatement.setInt(3, address.getHouseNumber());
                preparedStatement.setString(4, address.getTown());
                preparedStatement.setString(5, address.getPostalCode());
            } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.out.println(" could not add address " + ex.getMessage());
                }
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex1) {
                    System.out.println(" could not add address " + ex.getMessage());
                }
                return retVal;
            }

            try {
                if (preparedStatement.executeUpdate() > 0) {
                    preparedStatement = con.prepareStatement("SELECT LAST_INSERT_ID()");
                    resultSet = preparedStatement.executeQuery();
                    int addressId = 1;

                    if (resultSet.next()) {
                        addressId = resultSet.getInt(1);
                    }
                    resultSet = preparedStatement.executeQuery();
                    preparedStatement = con.prepareStatement("INSERT INTO user_address (emailAddress, addressId, isActive) VALUES (?, ?, ?)");
                    preparedStatement.setString(1, emailAddress);
                    preparedStatement.setInt(2, addressId);
                    preparedStatement.setBoolean(3, true);

                }
                if (preparedStatement.executeUpdate() < 1) {
                    con.rollback();
                    inOk = false;
                }
                if (inOk) {
                    con.commit();
                    allAddressList.add(address);
                    retVal = true;
                }

            } catch (SQLException ex1) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    System.out.println(" could not add an address " + ex.getMessage());
                }
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                    System.out.println(" could not add an address" + ex.getMessage());
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

    // ********************************************************************************************
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
//
        System.out.println(new AddressDaoImpl(con).addAddress(new Address(0, "the", 23, "tembisa", "0965")));
//        System.out.println(new AddressDaoImpl(con).addAddress(new Address(0, "themfn", 23, "town", "0965")));
//          System.out.println(new AddressDaoImpl(con).addAddress(new Address(0, "ddhdd", 23, "towndfd", "0965")));
//         System.out.println(new AddressDaoImpl(con).getAllAddresses());
//         System.out.println(new AddressDaoImpl(con).getAddress(1));
//        System.out.println(new AddressDaoImpl(con).updateAddress(new Address(1, "ffff", 45, "dddddd", "0956")));
//        System.out.println(new AddressDaoImpl(con).deleteAddress(8, true));
        System.out.println(new AddressDaoImpl(con).getAddressByUserEmail("the"));

    }
}
