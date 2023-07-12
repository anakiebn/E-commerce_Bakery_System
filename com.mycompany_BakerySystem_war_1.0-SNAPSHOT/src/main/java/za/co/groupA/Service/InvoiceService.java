package za.co.groupA.Service;

import java.util.List;
import za.co.groupA.Model.Invoice;

public interface InvoiceService {

    public boolean addInvoice(Invoice invoice);

    public List<Invoice> getAllInvoices();

    public Invoice getInvoice(int invoiceId);

    public boolean deleteInvoice(int invoiceId, int statusId);

    public boolean updateInvoice(Invoice invoice);
}
