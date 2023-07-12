
package za.co.groupA.Service;

import java.util.List;
import za.co.groupA.Model.Status;

/**
 *
 * @author t
 */
public interface StatusService {

    public boolean addStatus(Status status);

    public List<Status> getAllStatuss();

    public Status getStatus(int statusId);

    public boolean updateStatus(Status status);

}
