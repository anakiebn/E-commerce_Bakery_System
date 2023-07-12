package za.co.groupA.DaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class DBPoolManagerBasic {

    private BasicDataSource dataSource;

    public DBPoolManagerBasic(
            String driver,
            String url,
            String username,
            String password) {
        try {
            Class.forName(driver);
            System.out.println("Loading driver ----- " + driver);
            System.out.println("Loading URL ----- " + url);
            System.out.println("Loading username ----- " + username);
            System.out.println("Loading password ----- " + password);
            dataSource = new BasicDataSource();
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setMinIdle(5);
            dataSource.setMaxIdle(100);
            dataSource.setMaxOpenPreparedStatements(1000);
            System.out.println("Datasource Loaded ----- " + (dataSource != null));
        } catch (ClassNotFoundException ex) {
            System.out.println("Rats!! cannot load driver");
        }
    }
    // *******************************************

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // *******************************************
    public void closePool() {
        if (this.dataSource != null) {
            try {
                this.dataSource.close();
            } catch (SQLException ex) {
                System.out.println("ERROR closing connection " + ex.getMessage());
            }
        }
    }
// *******************************************
}
