
package za.co.groupA.Model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;



public class Payment implements Serializable  {
    
    private int paymentId;
    private int  invoiceId;
    private Date paymentDate;
    private Time paymentTime;
    private String paymentMethod;

    public Payment(int paymentId, int invoiceId, Date paymentDate, Time paymentTime, String paymentMethod) {
        this.paymentId = paymentId;
        this.invoiceId = invoiceId;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.paymentMethod = paymentMethod;
    }
    
    public Payment(){
        
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Time getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Time paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.paymentId;
        hash = 97 * hash + this.invoiceId;
        hash = 97 * hash + Objects.hashCode(this.paymentDate);
        hash = 97 * hash + Objects.hashCode(this.paymentTime);
        hash = 97 * hash + Objects.hashCode(this.paymentMethod);
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
        final Payment other = (Payment) obj;
        if (this.paymentId != other.paymentId) {
            return false;
        }
        if (this.invoiceId != other.invoiceId) {
            return false;
        }
        if (!Objects.equals(this.paymentMethod, other.paymentMethod)) {
            return false;
        }
        if (!Objects.equals(this.paymentDate, other.paymentDate)) {
            return false;
        }
        if (!Objects.equals(this.paymentTime, other.paymentTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentId=" + paymentId + ", invoiceId=" + invoiceId + ", paymentDate=" + paymentDate + ", paymentTime=" + paymentTime + ", paymentMethod=" + paymentMethod + '}';
    }


}
