package za.co.groupA.Model;

import java.io.Serializable;

public enum Role implements Serializable {

    ADMIN (1), CUSTOMER(2);

    private int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

}
