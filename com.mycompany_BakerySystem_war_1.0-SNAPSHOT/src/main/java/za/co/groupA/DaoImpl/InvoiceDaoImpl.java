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
import za.co.groupA.Dao.IngredientDao;
import za.co.groupA.Dao.InvoiceDao;
import za.co.groupA.Model.Invoice;

public class InvoiceDaoImpl implements InvoiceDao {

    private Connection con = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;
    private static InvoiceDao invoiceDaoImpl = null;
    List<Invoice> allInvoicesList;

    private InvoiceDaoImpl(Connection con) {
        this.con = con;
        allInvoicesList = getAllInvoiceFromDb();
    }

    //*********************************************************************************************
    public static InvoiceDao getInstance(DBPoolManagerBasic db) {
        if (invoiceDaoImpl == null) {
            try {
                invoiceDaoImpl = new InvoiceDaoImpl(db.getConnection()) {
                };
            } catch (SQLException ex) {
                System.out.println("Cannot obtain a connection" + ex.getMessage());
            }
        }
        return invoiceDaoImpl;
    }

    //***********************************************************************************************
    private List<Invoice> getAllInvoiceFromDb() {
        List<Invoice> allInvoices = new ArrayList<>();
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT invoice.invoiceId, invoice.orderId, invoice.invoiceDate, invoice.status, invoice.totalAmount, invoice.invoiceTime FROM invoice JOIN `order` ON invoice.orderId= order.orderId JOIN status ON invoice.status = status.statusId");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    allInvoices.add(new Invoice(resultSet.getInt("invoiceId"),
                            resultSet.getInt("orderId"),
                            resultSet.getDate("invoiceDate"),
                            resultSet.getTime("invoiceTime"),
                            resultSet.getInt("status"),
                            resultSet.getDouble("totalAmount")));
                }
            } catch (SQLException ex) {
                System.out.println("could not get the invoices " + ex.getMessage());
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

        return allInvoices;
    }

    //*************************************************************************************************
    public boolean idExists(int invoiceId) {
        return allInvoicesList.stream().anyMatch(inv -> inv.getInvoiceId() == invoiceId);
    }

    //****************************************************************************
    @Override
    public boolean addInvoice(Invoice invoice) {
        boolean retVal = false;
        if (con != null && !idExists(invoice.getInvoiceId())) {
            try {
                Timestamp invoiceDateTime = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

                preparedStatement = con.prepareStatement("INSERT INTO invoice(invoiceId, orderId,invoiceDate, status, totalAmount, invoiceTime) values(?,?,?,?,?,?)");
                preparedStatement.setInt(1, invoice.getInvoiceId());
                preparedStatement.setInt(2, invoice.getOrderId());
                preparedStatement.setTimestamp(3, invoiceDateTime);
                preparedStatement.setInt(4, invoice.getStatusId());
                preparedStatement.setDouble(5, invoice.getTotalAmount());
                preparedStatement.setTimestamp(6, invoiceDateTime);
                if (preparedStatement.executeUpdate() > 0) {
                    allInvoicesList.add(invoice);
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("could not add invoice: " + ex.getMessage());
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

    //*******************************************************************************************************
    @Override
    public List<Invoice> getAllInvoices() {
        return new ArrayList(allInvoicesList);
    }

    //****************************************************************************************************************
    @Override
    public Invoice getInvoice(int invoiceId) {
        return allInvoicesList.stream().filter(inv -> inv.getInvoiceId() == (invoiceId)).findFirst().orElse(null);
    }

    //*******************************************************************************************************
    @Override
    public boolean deleteInvoice(int invoiceId, int statusId) {
        boolean retVal = false;

        if (con != null && idExists(invoiceId)) {
            try {
                preparedStatement = con.prepareStatement("UPDATE invoice SET status = ? WHERE invoiceId = ?");
                preparedStatement.setInt(1, statusId);
                preparedStatement.setInt(2, invoiceId);
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println(" deleteInvoice ERROR" + ex.getMessage());
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
        if (retVal) {
            allInvoicesList = getAllInvoiceFromDb();
        }
        return retVal;

    }

    //*************************************************************************************************************************
    @Override
    public boolean updateInvoice(Invoice invoice) {
        boolean retVal = false;
        if (con != null && idExists(invoice.getInvoiceId())) {
            try {
                preparedStatement = con.prepareStatement("UPDATE invoice SET orderId = ?, invoiceDate = ? ,status =?, totalAmout = ?, invoiceTime = ? WHERE invoiceId = ?");
                preparedStatement.setInt(1, invoice.getOrderId());
                preparedStatement.setDate(2, invoice.getInvoiceDate());
                preparedStatement.setInt(3, invoice.getStatusId());
                preparedStatement.setDouble(4, invoice.getTotalAmount());
                preparedStatement.setTime(5, invoice.getInvoiceTime());
                preparedStatement.setInt(6, invoice.getInvoiceId());
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("could not update Invoice " + ex.getMessage());
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
            allInvoicesList = getAllInvoiceFromDb();
        }
        return retVal;
    }

    //***************************************************************************************************
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

        System.out.println(new InvoiceDaoImpl(con).addInvoice(new Invoice(0, 23, 8, 300.0)));
//         System.out.println(new InvoiceDaoImpl(con).allInvoicesList);
//         System.out.println(new InvoiceDaoImpl(con).getAllInvoices());
//            System.out.println(new InvoiceDaoImpl(con).getInvoice(1));
//            System.out.println(new InvoiceDaoImpl(con).deleteInvoice(1, 9));

    }
}
