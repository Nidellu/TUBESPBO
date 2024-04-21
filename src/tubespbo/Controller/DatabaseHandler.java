<<<<<<< HEAD
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
=======
package tubespbo.Controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.TimeZone;

import javax.swing.JOptionPane;

public class DatabaseHandler {

    private static DatabaseHandler instance;

    private DatabaseHandler() {
        // May be necessary to obtain
        // starting value elsewhere...
    }

    public static synchronized DatabaseHandler getInstance() {
        if (instance == null) // Lazy instantiation
        {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    public Connection con;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/gojek?serverTimezone=" + TimeZone.getDefault().getID();
    private String username = "root";
    private String password = "";

    private Connection logOn() {
        try {
            //Load JDBC Driver
            Class.forName(driver).newInstance();
            //Buat Object Connection
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, "Error Ocurred when login" + ex);
        }
        return con;
    }

    private void logOff() {
        try {
            //tutup koneksi
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
>>>>>>> master
