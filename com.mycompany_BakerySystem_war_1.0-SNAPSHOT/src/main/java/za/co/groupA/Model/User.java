
package za.co.groupA.Model;

import java.io.Serializable;
import java.util.Objects;


public class User implements Serializable {
    
    private String emailAddress;
    private String userName;
    private String userSurname;
    private String userTitle;
    private String idNumber;
    private String userTel;
    private int roleId;
    private String userPassword;
    private Address address;

    public User(){
        
    }


    public User(String emailAddress, String userName, String userSurname, String userTitle, String idNumber, String userTel, int roleId, String userPassword, Address address) {
        this.emailAddress = emailAddress;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userTitle = userTitle;
        this.idNumber = idNumber;
        this.userTel = userTel;
        this.roleId = roleId;
        this.userPassword = userPassword;
        this.address = address;

    }

    public User(String emailAddress, String userName, String userSurname, String userTitle, String idNumber, String userTel, int roleId, String userPassword) {
        this.emailAddress = emailAddress;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userTitle = userTitle;
        this.idNumber = idNumber;
        this.userTel = userTel;
        this.roleId = roleId;
        this.userPassword = userPassword;
       
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.emailAddress);
        hash = 53 * hash + Objects.hashCode(this.userName);
        hash = 53 * hash + Objects.hashCode(this.userSurname);
        hash = 53 * hash + Objects.hashCode(this.userTitle);
        hash = 53 * hash + Objects.hashCode(this.idNumber);
        hash = 53 * hash + Objects.hashCode(this.userTel);
        hash = 53 * hash + this.roleId;
        hash = 53 * hash + Objects.hashCode(this.userPassword);
        hash = 53 * hash + Objects.hashCode(this.address);
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
        final User other = (User) obj;
        if (this.roleId != other.roleId) {
            return false;
        }
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.userSurname, other.userSurname)) {
            return false;
        }
        if (!Objects.equals(this.userTitle, other.userTitle)) {
            return false;
        }
        if (!Objects.equals(this.idNumber, other.idNumber)) {
            return false;
        }
        if (!Objects.equals(this.userTel, other.userTel)) {
            return false;
        }
        if (!Objects.equals(this.userPassword, other.userPassword)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "emailAddress=" + emailAddress + ", userName=" + userName + ", userSurname=" + userSurname + ", userTitle=" + userTitle + ", idNumber=" + idNumber + ", userTel=" + userTel + ", roleId=" + roleId + ", userPassword=" + userPassword + ", address=" + address  + '}';
    }
    
    


    
    

    

    


    
    
}
