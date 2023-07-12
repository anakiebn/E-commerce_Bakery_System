package za.co.groupA.Model;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {

    private int addressId;
    private String streetName;
    private int houseNumber;
    private String postalCode;
    private String town;

    public Address() {
    }

    public Address(int addressId, String streetName, int houseNumber, String town, String postalCode) {
        this.addressId = addressId;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.town = town;
        this.postalCode = postalCode;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.addressId;
        hash = 17 * hash + Objects.hashCode(this.streetName);
        hash = 17 * hash + this.houseNumber;
        hash = 17 * hash + Objects.hashCode(this.town);
        hash = 17 * hash + Objects.hashCode(this.postalCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (this.addressId != other.addressId) {
            return false;
        }
        if (this.houseNumber != other.houseNumber) {
            return false;
        }
        if (!Objects.equals(this.streetName, other.streetName)) {
            return false;
        }
        if (!Objects.equals(this.town, other.town)) {
            return false;
        }
        if (!Objects.equals(this.postalCode, other.postalCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "addressId=" + addressId + ", streetName=" + streetName + ", houseNumber=" + houseNumber + ", town=" + town + ", postalCode=" + postalCode + '}';
    }

}
