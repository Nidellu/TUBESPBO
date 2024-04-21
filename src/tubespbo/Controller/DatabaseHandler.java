package tubespbo.Controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.TimeZone;

import javax.swing.JOptionPane;

public class DatabaseHandler {

    private static DatabaseHandler instance;

    public static synchronized DatabaseHandler getInstance() {
        if (instance == null) // Lazy instantiation
        {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    public Connection con;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/gojek?serverTimezone=" + TimeZone.getDefault().getID();
    private final String username = "root";
    private final String password = "";

    private Connection logOn() {
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, "Error Ocurred when login" + ex);
        }
        return con;
    }

    private void logOff() {
        try {
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error Ocurred when login" + ex);
        }
    }

    public void connect() {
        try {
            con = logOn();
        } catch (Exception ex) {
            System.out.println("Error occured when connecting to database");
        }
    }

    public void disconnect() {
        try {
            logOff();
        } catch (Exception ex) {
            System.out.println("Error occured when connecting to database");
        }
    }
}
