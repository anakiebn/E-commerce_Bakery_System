package za.co.groupA.ServiceImpl;

import java.util.List;
import javax.mail.MessagingException;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Dao.InvoiceDao;
import za.co.groupA.DaoImpl.InvoiceDaoImpl;
import za.co.groupA.Model.Invoice;
import za.co.groupA.Service.InvoiceService;

public class InvoiceServiceImpl implements InvoiceService {

    private final DBPoolManagerBasic db;
    private final InvoiceDao invoiceDao;

    public InvoiceServiceImpl(DBPoolManagerBasic db) {
        this.db = db;
        invoiceDao = InvoiceDaoImpl.getInstance(db);
    }

    @Override
    public boolean addInvoice(Invoice invoice) {
//        boolean retVal = false;
//        try {
//            if (invoiceDao.addInvoice(invoice)) {
//                retVal = true;
//                new EmailServiceImpl(db).sendInvoiceEmail(invoice.getOrderId());
//            }
//        } catch (MessagingException ex) {
//            System.out.println("Failed to send mail");
//        }
//        return retVal;
    return invoiceDao.addInvoice(invoice);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceDao.getAllInvoices();
    }

    @Override
    public Invoice getInvoice(int invoiceId) {
        return invoiceDao.getInvoice(invoiceId);
    }

    @Override
    public boolean deleteInvoice(int invoiceId, int statusId) {
        return invoiceDao.deleteInvoice(invoiceId, statusId);
    }

    @Override
    public boolean updateInvoice(Invoice invoice) {
        return invoiceDao.updateInvoice(invoice);
    }

}
