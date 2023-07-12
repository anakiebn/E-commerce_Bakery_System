
package za.co.groupA.Dao;

import java.util.List;
import za.co.groupA.Model.Status;

public interface StatusDao {
     
    public boolean addStatus(Status status);
    public List <Status> getAllStatuss();
    public Status getStatus (int statusId);
    public boolean updateStatus(Status status);
}
