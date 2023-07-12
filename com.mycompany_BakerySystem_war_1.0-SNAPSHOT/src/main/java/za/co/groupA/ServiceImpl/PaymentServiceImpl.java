package za.co.groupA.ServiceImpl;

import java.util.List;
import za.co.groupA.Dao.PaymentDao;
import za.co.groupA.Model.Payment;
import za.co.groupA.Service.PaymentService;

public class PaymentServiceImpl implements PaymentService{
    
    private PaymentDao paymentDao;
    
    public PaymentServiceImpl(PaymentDao paymentDao){
        this.paymentDao=paymentDao;
    }

    @Override
    public boolean addPayment(Payment payment) {
        return paymentDao.addPayment(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentDao.getAllPayments();
    }

    @Override
    public Payment getPayment(int paymentId) {
        return paymentDao.getPayment(paymentId);
    }

    

    @Override
    public boolean updatePayment(Payment payment) {
        return paymentDao.updatePayment(payment);
    }

   

   
    
 
  
}
