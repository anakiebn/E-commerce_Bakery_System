
package za.co.groupA.Model;

import java.io.Serializable;
import java.util.Objects;


public class Status implements Serializable  {
    
    private int statusId;
    private String statusDescr;


    public Status(int statusId, String statusDescr) {
        this.statusId = statusId;
        this.statusDescr = statusDescr;
    }

    public Status(){
        
    }
    
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusDescr() {
        return statusDescr;
    }

    public void setStatusDescr(String statusDescr) {
        this.statusDescr = statusDescr;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.statusId;
        hash = 59 * hash + Objects.hashCode(this.statusDescr);
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
        final Status other = (Status) obj;
        if (this.statusId != other.statusId) {
            return false;
        }
        if (!Objects.equals(this.statusDescr, other.statusDescr)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Status{" + "statusId=" + statusId + ", statusDescr=" + statusDescr + '}';
    }
    
    
    
}
