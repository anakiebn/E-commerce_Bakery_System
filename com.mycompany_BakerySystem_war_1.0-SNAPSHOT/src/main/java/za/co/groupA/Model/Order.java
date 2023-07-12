package za.co.groupA.Model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Map;
import java.util.Objects;

public class Order implements Serializable  {

    private int orderId;
    private Map<Integer, CartLineItem> products_In_A_Cart;
    private int deliveryNoteId;
    private String emailAddress;
    private int addressId;
    private Date orderDate;
    private Time orderTime;
    private int status;
    private double totalAmount;

    public Order() {
    }
    
    public Order(int orderId, Map<Integer, CartLineItem> products_In_A_Cart, int deliveryNoteId, String emailAddress, int addressId, int status, double totalAmount) {
        this.orderId = orderId;
        this.products_In_A_Cart = products_In_A_Cart;
        this.deliveryNoteId = deliveryNoteId;
        this.emailAddress = emailAddress;
        this.addressId = addressId;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public Order(int orderId, int deliveryNoteId, String emailAddress, int addressId, Date orderDate, Time orderTime, int status, double totalAmount) {
        this.orderId = orderId;
        this.deliveryNoteId = deliveryNoteId;
        this.emailAddress = emailAddress;
        this.addressId = addressId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Map<Integer, CartLineItem> getProducts_In_A_Cart() {
        return products_In_A_Cart;
    }

    public void setProducts_In_A_Cart(Map<Integer, CartLineItem> products_In_A_Cart) {
        this.products_In_A_Cart = products_In_A_Cart;
    }

    public int getDeliveryNoteId() {
        return deliveryNoteId;
    }

    public void setDeliveryNoteId(int deliveryNoteId) {
        this.deliveryNoteId = deliveryNoteId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.orderId;
        hash = 53 * hash + Objects.hashCode(this.products_In_A_Cart);
        hash = 53 * hash + this.deliveryNoteId;
        hash = 53 * hash + Objects.hashCode(this.emailAddress);
        hash = 53 * hash + this.addressId;
        hash = 53 * hash + Objects.hashCode(this.orderDate);
        hash = 53 * hash + Objects.hashCode(this.orderTime);
        hash = 53 * hash + this.status;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.totalAmount) ^ (Double.doubleToLongBits(this.totalAmount) >>> 32));
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
        final Order other = (Order) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.deliveryNoteId != other.deliveryNoteId) {
            return false;
        }
        if (this.addressId != other.addressId) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalAmount) != Double.doubleToLongBits(other.totalAmount)) {
            return false;
        }
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        if (!Objects.equals(this.products_In_A_Cart, other.products_In_A_Cart)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.orderTime, other.orderTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", products_In_A_Cart=" + products_In_A_Cart + ", deliveryNoteId=" + deliveryNoteId + ", emailAddress=" + emailAddress + ", addressId=" + addressId + ", orderDate=" + orderDate + ", orderTime=" + orderTime + ", status=" + status + ", totalAmount=" + totalAmount + '}';
    }
    
    

}
