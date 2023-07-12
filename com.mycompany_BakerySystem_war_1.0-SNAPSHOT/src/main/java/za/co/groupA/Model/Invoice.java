package za.co.groupA.Model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Invoice implements Serializable {

    private int invoiceId;
    private int orderId;
    private Date invoiceDate;
    private Time invoiceTime;
    private int statusId;
    private double totalAmount;

    public Invoice() {
    }

    public Invoice(int invoiceId, int orderId, int statusId, double totalAmount) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.statusId = statusId;
        this.totalAmount = totalAmount;
    }
    

    public Invoice(int invoiceId, int orderId, Date invoiceDate, Time invoiceTime, int statusId, double totalAmount) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.invoiceDate = invoiceDate;
        this.invoiceTime = invoiceTime;
        this.statusId = statusId;
        this.totalAmount = totalAmount;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Time getInvoiceTime() {
        return invoiceTime;
    }

    public void setInvoiceTime(Time invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.invoiceId;
        hash = 97 * hash + this.orderId;
        hash = 97 * hash + Objects.hashCode(this.invoiceDate);
        hash = 97 * hash + Objects.hashCode(this.invoiceTime);
        hash = 97 * hash + this.statusId;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.totalAmount) ^ (Double.doubleToLongBits(this.totalAmount) >>> 32));
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
        final Invoice other = (Invoice) obj;
        if (this.invoiceId != other.invoiceId) {
            return false;
        }
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.statusId != other.statusId) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalAmount) != Double.doubleToLongBits(other.totalAmount)) {
            return false;
        }
        if (!Objects.equals(this.invoiceDate, other.invoiceDate)) {
            return false;
        }
        if (!Objects.equals(this.invoiceTime, other.invoiceTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Invoice{" + "invoiceId=" + invoiceId + ", orderId=" + orderId + ", invoiceDate=" + invoiceDate + ", invoiceTime=" + invoiceTime + ", statusId=" + statusId + ", totalAmount=" + totalAmount + '}';
    }

}
