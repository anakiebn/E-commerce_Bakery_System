package za.co.groupA.ServiceImpl;

import java.util.List;
import za.co.groupA.Dao.AddressDao;
import za.co.groupA.Model.Address;
import za.co.groupA.Service.AddressService;

public class AddressServiceImpl implements AddressService {

    private AddressDao addressDao;

    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao=addressDao;
    }

    @Override
    public boolean addAddress(Address address) {
        return addressDao.addAddress(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDao.getAllAddresses();
    }

    @Override
    public Address getAddress(int addressId) {
        return addressDao.getAddress(addressId);
    }

    @Override
    public boolean updateAddress(Address address) {
        return addressDao.updateAddress(address);
    }

    @Override
    public boolean deleteAddress(int addressId, boolean delete) {
        return addressDao.deleteAddress(addressId, delete);
    }

    @Override
    public List<Address> getAddressByUserEmail(String emailAddress) {
        return addressDao.getAddressByUserEmail(emailAddress);
    }

    @Override
    public boolean addAddressForExistingUser(String emailAddress, Address address) {
        return addressDao.addAddressForExistingUser(emailAddress, address);
    }

}
