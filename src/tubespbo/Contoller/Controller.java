package tubespbo.Contoller;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tubespbo.Model.*;

public class Controller {

    static DatabaseHandler conn = new DatabaseHandler();

    public boolean inputDataToDB(String nama, String email, String password, String photoPath, int kategoriUser) {
        conn.connect();
        String query = "INSERT INTO users (name, email, password, idCategory, photo) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, nama);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setInt(4, kategoriUser);
            stmt.setString(5, photoPath);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public ArrayList<User> getUserCategory(int category) {
//        conn.connect();
//        String query = "SELECT * FROM users WHERE idCategory = '" + category + "'";
//        ArrayList<User> listUsers = new ArrayList<>();
//        try {
//            Statement stmt = conn.con.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                User users = new User();
//                users.setId(rs.getInt("id"));
//                users.setName(rs.getString("name"));
//                users.setEmail(rs.getString("email"));
//                users.setPassword(rs.getString("password"));
//                users.setIdCategory(rs.getInt("idCategory"));
//                users.setPhoto(rs.getString("photo"));
//
//                listUsers.add(users);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return (listUsers);
//    }

    public ArrayList<String> getCategory() {
        conn.connect();
        String query = "SELECT name FROM categoryusers";
        ArrayList<String> listUsers = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                listUsers.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listUsers);
    }

    public int getIntCategory(String category) {
        conn.connect();
        String query = "SELECT id FROM categoryusers WHERE name = '" + category +"'";
        int id = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = (rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (id);
    }

    public String getStringCategory(int category) {
        conn.connect();
        String query = "SELECT name FROM categoryusers WHERE id = '" + category + "'";
        String name = "";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                name = (rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (name);
    }
    
    public boolean logIn(String username, String password) {
        conn.connect();
        String query = "SELECT * FROM users WHERE user_name = '" + username + "' AND user_pass = '" + password + "'";
        boolean exists = false;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public String getIDUser(String username) {
        conn.connect();
        String query = "SELECT user_id FROM users WHERE user_name = '" + username + "'";
        String id = "";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = (rs.getString("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return id;
    }
    
    public String getRolesUser(String userID) {
        conn.connect();
        String query = "SELECT user_role FROM users WHERE user_id = '" + userID + "'";
        String roles = "";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                roles = (rs.getString("user_role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return roles;
    }
}
