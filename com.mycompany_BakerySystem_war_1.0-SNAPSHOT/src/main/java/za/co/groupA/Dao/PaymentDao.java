
package za.co.groupA.Dao;

import java.util.List;
import za.co.groupA.Model.Payment;



public interface PaymentDao {
    
      
    public boolean addPayment(Payment payment);

    public List<Payment> getAllPayments();

    public Payment getPayment(int paymentId);

//    public boolean deletePayment(int paymentId);

    public boolean updatePayment(Payment payment);
    
}
