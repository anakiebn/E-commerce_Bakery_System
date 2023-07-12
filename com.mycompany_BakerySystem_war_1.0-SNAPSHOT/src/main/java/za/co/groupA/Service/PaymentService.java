/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package za.co.groupA.Service;

import java.util.List;
import za.co.groupA.Model.Payment;

/**
 *
 * @author Train
 */
public interface PaymentService {
      
       
    public boolean addPayment(Payment payment);

    public List<Payment> getAllPayments();

    public Payment getPayment(int paymentId);

//    public boolean deletePayment(int paymentId);

    public boolean updatePayment(Payment payment);
    
}
