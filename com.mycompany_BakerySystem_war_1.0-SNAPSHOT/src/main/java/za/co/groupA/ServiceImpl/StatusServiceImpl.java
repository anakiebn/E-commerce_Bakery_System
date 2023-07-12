/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.groupA.ServiceImpl;

import java.util.List;
import za.co.groupA.Dao.StatusDao;
import za.co.groupA.Model.Status;
import za.co.groupA.Service.StatusService;

/**
 *
 * @author t
 */
public class StatusServiceImpl implements StatusService {

    private StatusDao statusDao;

    public StatusServiceImpl(StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    @Override
    public boolean addStatus(Status status) {
        return statusDao.addStatus(status);
    }

    @Override
    public List<Status> getAllStatuss() {
        return statusDao.getAllStatuss();
    }

    @Override
    public Status getStatus(int statusId) {
        return statusDao.getStatus(statusId);
    }

    @Override
    public boolean updateStatus(Status status) {
        return statusDao.updateStatus(status);
    }


}
