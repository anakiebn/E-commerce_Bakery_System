
package za.co.groupA.Dao;

import java.util.List;
import za.co.groupA.Model.Unit;


public interface UnitDao {
    
    public boolean addUnit(Unit unit);
    public List <Unit> getAllUnits();
    public Unit getUnit (String type);
    public boolean updateUnit(Unit unit);
    public String getUnitByName(int unitId);
//    public  boolean deleteUnit(Unit unit);
    
    
    
    
}
