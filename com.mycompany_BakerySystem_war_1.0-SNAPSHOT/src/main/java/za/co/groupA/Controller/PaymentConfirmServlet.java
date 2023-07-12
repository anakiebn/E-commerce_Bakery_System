package za.co.groupA.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Model.Address;
import za.co.groupA.Model.CartLineItem;
import za.co.groupA.Model.Order;
import za.co.groupA.Model.User;
import za.co.groupA.Service.OrderService;
import za.co.groupA.ServiceImpl.OrderServiceImpl;
import za.co.groupA.ServiceImpl.PaymentServiceImpl;

@WebServlet(name = "PaymentConfirmServlet", urlPatterns = {"/PaymentConfirmServlet"})
public class PaymentConfirmServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        User user = (User) request.getSession().getAttribute("user");

        int totalAmount = 0;
        OrderService orderService = new OrderServiceImpl(db);
        Address address = (Address) request.getSession().getAttribute("Address");
        Map<Integer, CartLineItem> cart = (HashMap<Integer, CartLineItem>) request.getSession().getAttribute("viewCart");

        Random random = new Random(100);
        if (user != null && address != null && cart != null) {

            for (int key : cart.keySet()) {
                totalAmount += cart.get(key).getProductQty() * cart.get(key).getProduct().getPrice();
            }

            Order order = new Order();
            order.setDeliveryNoteId(1);
            order.setProducts_In_A_Cart(cart);
            order.setEmailAddress(user.getEmailAddress());
            order.setStatus(1);
            order.setTotalAmount(totalAmount);
            order.setAddressId(address.getAddressId());

            if ((random.nextInt() % 2) == 0) {
                if (orderService.addOrder(order)) {

                    request.getRequestDispatcher("PaymentSuccessfulPopUp.jsp").forward(request, response);

                } else {
                    request.getRequestDispatcher("PaymentFailed.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("PaymentFailed.jsp").forward(request, response);
            }
        } else {
            System.out.println("something is null there");
        }

    }

}
/*
 // Set the content type to PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=receipt.pdf");

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.addTitle("Order Receipt");

            // Set up fonts
            Font headingFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font contentFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            Font datetimeFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Paragraph heading = new Paragraph("Order Receipt", headingFont);
            Paragraph datetime = new Paragraph(getCurrentDateTime(), datetimeFont);
            datetime.setSpacingAfter(10f);
            document.add(heading);
            document.add(datetime);

            // Create the table
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Set table headers
            String[] headers = {"Product title", "Quantity", "Price", "Total", "Order ID"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, contentFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // Add table rows with data
            List<OrderItem> orderItems = bsoi.getAllLineItems(43);
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            double subtotal = 0.0;
            double taxRate = 0.15;
            double taxAmount = 0.0;
            double total = 0.0;
            int orderId = 0;

            for (OrderItem orderItem : orderItems) {
                double productTotal = orderItem.getUnitPrice() * orderItem.getProductQuantity();
                subtotal += productTotal;
                taxAmount = subtotal * taxRate;
                total = subtotal + taxAmount;
                orderId = orderItem.getOrder_id();

                table.addCell(orderItem.getProduct_title());
                table.addCell(String.valueOf(orderItem.getProductQuantity()));
                table.addCell("R" + decimalFormat.format(orderItem.getUnitPrice()));
                table.addCell("R" + decimalFormat.format(productTotal));
                table.addCell("#" + orderItem.getOrder_id());
            }

            // Add the table to the document
            document.add(table);

            // Add subtotal, tax, and total amount
            Paragraph amountParagraph = new Paragraph();
            amountParagraph.setAlignment(Element.ALIGN_RIGHT);
            amountParagraph.add(new Chunk("Subtotal: ", contentFont));
            amountParagraph.add(new Chunk("R" + decimalFormat.format(subtotal), contentFont));
            amountParagraph.add(Chunk.NEWLINE);
            amountParagraph.add(new Chunk("Tax: ", contentFont));
            amountParagraph.add(new Chunk("R" + decimalFormat.format(taxAmount), contentFont));
            amountParagraph.add(Chunk.NEWLINE);
            amountParagraph.add(new Chunk("Total: ", contentFont));
            amountParagraph.add(new Chunk("R" + decimalFormat.format(total), contentFont));
            document.add(amountParagraph);

            // Close the Document
            document.close();

            System.out.println("Invoice PDF generated successfully!");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
*/