package za.co.groupA.DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Dao.DeliveryNoteDao;
import za.co.groupA.Model.DeliveryNote;

public class DeliveryNoteDaoImpl implements DeliveryNoteDao {

    private Connection con = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;
    private static DeliveryNoteDao deliveryNoteDaoImpl = null;
    List<DeliveryNote> allNotesList;

    private DeliveryNoteDaoImpl(Connection con) {
        this.con = con;
        allNotesList = getAllNotesFromDb();
    }

    //************************************************************************************************************
   
    public static DeliveryNoteDao getInstance(DBPoolManagerBasic db) {
        if (deliveryNoteDaoImpl == null) {
            try {
                deliveryNoteDaoImpl = new DeliveryNoteDaoImpl(db.getConnection());
            } catch (SQLException ex) {
                System.out.println("Cannot obtain a connection");
            }
        }
        return deliveryNoteDaoImpl;

    }
    //*****************************************************************************************************************

    private List<DeliveryNote> getAllNotesFromDb() {
        List<DeliveryNote> notesList = new ArrayList<>();
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT deliverynote.noteId, deliverynote.note,deliverynote.deliveryDate, deliverynote.deliveryTime, deliverynote.status FROM deliverynote JOIN status ON deliverynote.status = status.statusId ");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    notesList.add(new DeliveryNote(
                            resultSet.getInt("noteId"),
                            resultSet.getString("note"),
                            resultSet.getDate("deliveryDate"),
                            resultSet.getTime("deliveryTime"),
                            resultSet.getInt("status")));
                }
            } catch (SQLException ex) {
                System.out.println("ERROR!" + ex.getMessage());
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
        return notesList;
    }
    
        public boolean idExists(int noteId) {
        return  allNotesList.stream().anyMatch(cat -> cat.getNoteId() == noteId);
    }

    //*************************************************************************************************
    @Override
    public boolean addDeliveryNote(DeliveryNote note) {

        boolean retVal = false;
        if (con != null &&  !idExists(note.getNoteId())) {
            try {
                preparedStatement = con.prepareStatement("INSERT INTO deliverynote (noteId, note,deliveryDate, deliveryTime, status) VALUES(?,?,?,?,?)");
                preparedStatement.setInt(1, note.getNoteId());
                preparedStatement.setString(2, note.getNote());
                preparedStatement.setDate(3, note.getDeliveryDate());
                preparedStatement.setTime(4, note.getDeliveryTime());
                preparedStatement.setInt(5, note.getStatus());

                if (preparedStatement.executeUpdate() > 0) {
                   allNotesList.add(note);
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
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
        return retVal;
    }

    //******************************************************************************************
    @Override
    public List<DeliveryNote> getAllDeliveryNotes() {
        return new ArrayList(allNotesList);
    }

    //*******************************************************************************************
    @Override
    public DeliveryNote getDeliveryNote(int noteId) {
        return allNotesList.stream().filter(note -> note.getNoteId() == (noteId)).findFirst().orElse(null);
    }

    //******************************************************************************
    @Override
    public boolean deleteDeliveryNote(int noteId, int statusId) {
        boolean retVal = false;

        if (con != null && idExists(noteId)) {
            try {
                preparedStatement = con.prepareStatement("UPDATE deliverynote SET status = ? WHERE noteId = ?");
                preparedStatement.setInt(1, statusId);
                preparedStatement.setInt(2, noteId);
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println(" deleteNote ERROR" + ex.getMessage());
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
            allNotesList = getAllNotesFromDb();
        }
        return retVal;

    }

    //***************************************************************************************************************
    @Override
    public boolean updateDeliveryNote(DeliveryNote deliveryNote) {
        boolean retVal = false;
        if (con != null && idExists(deliveryNote.getNoteId())) {
            try {
                preparedStatement = con.prepareStatement("UPDATE deliverynote SET note = ?, status = ? ,deliveryDate =?, deliveryTime = ? WHERE noteId = ?");
                preparedStatement.setString(1, deliveryNote.getNote());
                preparedStatement.setInt(2, deliveryNote.getStatus());
                preparedStatement.setDate(3, deliveryNote.getDeliveryDate());
                preparedStatement.setTime(4, deliveryNote.getDeliveryTime());
                preparedStatement.setInt(3, deliveryNote.getNoteId());
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR" + ex.getMessage());
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
            allNotesList = getAllNotesFromDb();
        }
        return retVal;
    }

    //*********************************************************************************************************
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

        System.out.println(new DeliveryNoteDaoImpl(con).addDeliveryNote(new DeliveryNote(0, "hallo this is note one",null, null,9)));
//        System.out.println(new DeliveryNoteDaoImpl(con).getAllNotes());
//        System.out.println(new DeliveryNoteDaoImpl(con).getDeliveryNote(1));
//        System.out.println(new DeliveryNoteDaoImpl(con).updateDeliveryNote(new DeliveryNote(1,"an updated version",8)));
    }
}
