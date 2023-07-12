package za.co.groupA.DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Dao.PaymentDao;
import za.co.groupA.Model.Payment;

public class PaymentDaoImpl implements PaymentDao {

    private Connection con = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;
    private static PaymentDao paymentDaoImpl = null;
    List<Payment> allPaymentsList;

    private PaymentDaoImpl(Connection con) {
        this.con = con;
        allPaymentsList = getAllPaymentsFromDb();
    }

    //*****************************************************************************************************
    public static PaymentDao getInstance(DBPoolManagerBasic db) {
        if (paymentDaoImpl == null) {
            try {
                paymentDaoImpl = new PaymentDaoImpl(db.getConnection());
            } catch (SQLException ex) {
                System.out.println("Cannot obtain a connection");
            }
        }
        return paymentDaoImpl;

    }

    //***************************************************************************************************************8
    private List<Payment> getAllPaymentsFromDb() {
        List<Payment> allPayments = new ArrayList<>();
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT payment.paymentId, payment.invoiceId, payment.paymentDate, payment.paymentTime, payment.paymentMethod FROM payment JOIN invoice ON payment.invoiceId = invoice.invoiceId");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    allPayments.add(new Payment(resultSet.getInt("paymentId"),
                            resultSet.getInt("invoiceId"),
                            resultSet.getDate("paymentDate"),
                            resultSet.getTime("paymentTime"),
                            resultSet.getString("paymentMethod")));
                }
            } catch (SQLException ex) {
                System.out.println("could not get the payments " + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }

        return allPayments;
    }

    //****************************************************************************************************
    public boolean idExists(int paymentId) {
        return allPaymentsList.stream().anyMatch(pay -> pay.getPaymentId() == paymentId);
    }

    //*****************************************************************************************************************888
    @Override
    public boolean addPayment(Payment payment) {

        boolean retVal = false;
        if (con != null && !idExists(payment.getPaymentId())) {
            try {
                Timestamp orderDateTime = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

                preparedStatement = con.prepareStatement("INSERT INTO payment(paymentId, invoiceId, paymentDate, paymentTime, paymentMethod) VALUES (?,?,?,?,?)");
                preparedStatement.setInt(1, payment.getPaymentId());
                preparedStatement.setInt(2, payment.getInvoiceId());
                preparedStatement.setTimestamp(3, orderDateTime);
                preparedStatement.setTimestamp(4, orderDateTime);

                preparedStatement.setString(5, payment.getPaymentMethod());

                if (preparedStatement.executeUpdate() > 0) {
                    allPaymentsList.add(payment);
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("could not add the payment: " + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    } finally {
                        preparedStatement = null;
                    }
                }
            }
        }
        return retVal;
    }

    //******************************************************************************************************8888
    @Override
    public List<Payment> getAllPayments() {
        return new ArrayList(allPaymentsList);
    }

    //***********************************************************************************************888
    @Override
    public Payment getPayment(int paymentId) {
        return allPaymentsList.stream().filter(paym -> paym.getPaymentId() == (paymentId)).findFirst().orElse(null);
    }

    //*********************************************************************************************************88
//    @Override
//    public boolean deletePayment(int paymentId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    //*********************************************************************************************************************88
    @Override
    public boolean updatePayment(Payment payment) {
        boolean retVal = false;
        if (con != null && idExists(payment.getPaymentId())) {
            try {
                preparedStatement = con.prepareStatement("UPDATE payment SET InvoiceId = ?, paymentDate = ? ,paymentTime =?, paymentMethod = ? WHERE paymentId = ?");
                preparedStatement.setInt(1, payment.getInvoiceId());
                preparedStatement.setDate(2, payment.getPaymentDate());
                preparedStatement.setTime(3, payment.getPaymentTime());

                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("could not update payments " + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    } finally {
                        preparedStatement = null;
                    }
                }
            }
        }
        if (retVal) {
            allPaymentsList = getAllPaymentsFromDb();
        }
        return retVal;
    }

    //*******************************************************************************************************************************
    public static void main(String[] args) {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bakerySystem", "root", "root"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (con != null) {
            System.out.println("Got connection");
        }

        System.out.println(new PaymentDaoImpl(con).addPayment(new Payment(0, 1, null, null, "paypal")));
//             System.out.println(new PaymentDaoImpl(con).getAllPayments());
        System.out.println(new PaymentDaoImpl(con).allPaymentsList);

    }
}
