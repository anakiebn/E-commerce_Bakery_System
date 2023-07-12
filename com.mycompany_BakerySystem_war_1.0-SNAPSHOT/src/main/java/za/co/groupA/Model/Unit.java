
package za.co.groupA.Model;

import java.io.Serializable;
import java.util.Objects;




public class Unit implements Serializable  {
    private int unitId;
    private String unitType;


    public Unit(){
        
    }
    public Unit(int unitId, String unitType) {
        this.unitId = unitId;
        this.unitType = unitType;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.unitId;
        hash = 53 * hash + Objects.hashCode(this.unitType);
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
        final Unit other = (Unit) obj;
        if (this.unitId != other.unitId) {
            return false;
        }
        if (!Objects.equals(this.unitType, other.unitType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unit{" + "unitId=" + unitId + ", unitType=" + unitType + '}';
    }
     
    
    
    
    
}
