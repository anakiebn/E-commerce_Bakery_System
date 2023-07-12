package za.co.groupA.Dao;

import java.util.List;
import za.co.groupA.Model.Address;

public interface AddressDao {

    public boolean addAddress(Address address);

    public List<Address> getAllAddresses();

    public Address getAddress(int addressId);

    public boolean updateAddress(Address address);

    public boolean deleteAddress(int addressId, boolean delete);

    public List<Address> getAddressByUserEmail(String emailAddress);

    public boolean addAddressForExistingUser(String emailAddress, Address address);

}
