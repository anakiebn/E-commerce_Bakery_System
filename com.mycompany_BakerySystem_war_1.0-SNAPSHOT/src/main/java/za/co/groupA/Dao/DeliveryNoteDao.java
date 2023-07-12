
package za.co.groupA.Dao;

import java.util.List;
import za.co.groupA.Model.DeliveryNote;


public interface DeliveryNoteDao {
    
    public boolean addDeliveryNote(DeliveryNote note);

    public List<DeliveryNote> getAllDeliveryNotes();

    public DeliveryNote getDeliveryNote(int noteId);

    public boolean deleteDeliveryNote(int noteId, int statusId);

    public boolean updateDeliveryNote(DeliveryNote deliveryNote);
    
}
