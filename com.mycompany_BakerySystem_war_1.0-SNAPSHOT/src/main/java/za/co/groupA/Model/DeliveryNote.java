package za.co.groupA.Model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class DeliveryNote {

    private int noteId;
    private String note;
    private Date deliveryDate;
    private Time deliveryTime;
    private int status;

    public DeliveryNote() {
    }

    public DeliveryNote(int noteId, String note, Date deliveryDate, Time deliveryTime, int status) {
        this.noteId = noteId;
        this.note = note;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.status = status;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Time getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Time deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.noteId;
        hash = 23 * hash + Objects.hashCode(this.note);
        hash = 23 * hash + Objects.hashCode(this.deliveryDate);
        hash = 23 * hash + Objects.hashCode(this.deliveryTime);
        hash = 23 * hash + this.status;
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
        final DeliveryNote other = (DeliveryNote) obj;
        if (this.noteId != other.noteId) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        if (!Objects.equals(this.deliveryDate, other.deliveryDate)) {
            return false;
        }
        if (!Objects.equals(this.deliveryTime, other.deliveryTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DeliveryNote{" + "noteId=" + noteId + ", note=" + note + ", deliveryDate=" + deliveryDate + ", deliveryTime=" + deliveryTime + ", status=" + status + '}';
    }

}
