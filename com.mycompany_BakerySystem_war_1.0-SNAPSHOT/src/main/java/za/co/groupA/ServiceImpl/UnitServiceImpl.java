
package za.co.groupA.ServiceImpl;

import java.util.List;
import za.co.groupA.Dao.UnitDao;
import za.co.groupA.Model.Unit;
import za.co.groupA.Service.UnitService;

public class UnitServiceImpl implements UnitService {

    private final UnitDao unitDao;
    
    public UnitServiceImpl(UnitDao unitDao){
        this.unitDao=unitDao;
    }
    @Override
    public boolean addUnit(Unit unit) {
      
        return unitDao.addUnit(unit);
        
    }

    @Override
    public List<Unit> getAllUnits() {
        return unitDao.getAllUnits().isEmpty()?null:unitDao.getAllUnits();
    }

    @Override
    public Unit getUnit(String type) {
        
        return unitDao.getUnit(type)!=null?unitDao.getUnit(type):null;
        
    }

    @Override
    public boolean updateUnit(Unit unit) {
        return unitDao.updateUnit(unit);
    }

    @Override
    public String getUnitByName(int unitId) {
        return unitDao.getUnitByName(unitId);
    }

   
    
}
