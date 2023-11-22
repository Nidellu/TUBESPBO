package tubespbo.Controller;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import tubespbo.Model.*;

public class Controller {

    static DatabaseHandler conn = new DatabaseHandler();

    public boolean inputUserDataToDB(String username, String password, String kategoriUser) {
        conn.connect();
        String query = "INSERT INTO users (user_name, user_pass, user_role, user_wallet) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, kategoriUser);
            stmt.setDouble(4, 0);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean inputDriverDataToDB(int id, String phonNum, String namaKendaraan, String tipe, String plat) {
        conn.connect();
        String query = "INSERT INTO drivers (driver_id, driver_phonNum, vehicle_name, vehicle_type, vehicle_plate) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, phonNum);
            stmt.setString(3, namaKendaraan);
            stmt.setString(4, tipe);
            stmt.setString(5, plat);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean inputPassangerDataToDB(int id, String phonNum) {
        conn.connect();
        String query = "INSERT INTO passangers (passanger_id, passanger_phonNum) VALUES (?, ?)";
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, phonNum);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserNameDataPassangerToDB(int idMasuk, String username) {
        conn.connect();
        String query = "UPDATE users SET user_name = '" + username + "' WHERE user_id = '" + idMasuk + "';";
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePhoneNumDataPassangerToDB(int idMasuk, String telepon) {
        conn.connect();
        String query = "UPDATE passangers SET passanger_phonNum = '" + telepon + "' WHERE passanger_id = '" + idMasuk + "';";
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePasswordDataPassangerToDB(int idMasuk, String pass) {
        conn.connect();
        String query = "UPDATE users SET user_pass = '" + pass + "' WHERE user_id = '" + idMasuk + "';";
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Passanger> getUserByID(int id) {
        conn.connect();
        String query = "SELECT users.user_name, users.user_pass, passangers.passanger_phonNum "
                + "FROM users "
                + "JOIN passangers ON passangers.passanger_id = users.user_id "
                + "WHERE passangers.passanger_id = '" + id + "'";
        ArrayList<Passanger> listPass = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Passanger pass = new Passanger();
                pass.setUser_name(rs.getString("users.user_name"));
                pass.setUser_pass(rs.getString("users.user_pass"));
                pass.setPhone_number(rs.getString("passangers.passanger_phonNum"));

                listPass.add(pass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listPass);
    }

    public OrderStatusEnum getEnum(String type) {
        if (type.equalsIgnoreCase("FINISHED")) {
            return OrderStatusEnum.FINISHED;
        } else if (type.equalsIgnoreCase("NOW")) {
            return OrderStatusEnum.NOW;
        } else if (type.equalsIgnoreCase("CANCEL")) {
            return OrderStatusEnum.CANCEL;
        } else {
            return null;
        }
    }
    
    public ArrayList<Order> getDetailOrder(int idOrder) {
        conn.connect();
        String query = "SELECT order_destination, order_date, order_final_price, order_status, order_vehicle_name "
                + "FROM orders WHERE order_id = '" + idOrder + "'";
        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Order orders = new Order();
                orders.setOrder_destination(rs.getString("order_destination"));
                orders.setOrder_date(rs.getDate("order_date"));
                orders.setOrder_final_price(rs.getDouble("order_final_price"));
                orders.setOrder_status(getEnum(rs.getString("order_status")));
                orders.setOrder_vehicle_name(rs.getString("order_vehicle_name"));
                listOrder.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listOrder);
    }

    public ArrayList<Order> getOrderNow(int id) {
        conn.connect();
        String query = "SELECT order_id, order_destination, order_date, order_final_price, order_status, order_vehicle_name "
                + "FROM orders WHERE cust_id = '" + id + "'";
        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Order orders = new Order();
                orders.setOrder_id(rs.getInt("order_id"));
                orders.setOrder_destination(rs.getString("order_destination"));
                orders.setOrder_date(rs.getDate("order_date"));
                orders.setOrder_final_price(rs.getDouble("order_final_price"));
                orders.setOrder_status(getEnum(rs.getString("order_status")));
                orders.setOrder_vehicle_name(rs.getString("order_vehicle_name"));
                listOrder.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listOrder);
    }
    
    public boolean getByUserName(String username) {
        conn.connect();
        String query = "SELECT * FROM users WHERE user_name = '" + username + "'";
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
        return (exists);
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

    public int getIDUser(String username) {
        conn.connect();
        String query = "SELECT user_id FROM users WHERE user_name = '" + username + "'";
        int id = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = (rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return id;
    }

    public String getUsername(int id) {
        conn.connect();
        String query = "SELECT user_name FROM users WHERE user_id = '" + id + "'";
        String username = "";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                username = (rs.getString("user_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return username;
    }

    public String getRolesUser(int userID) {
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

    public double getWallet(int id) {
        conn.connect();
        String query = "SELECT user_wallet FROM users WHERE user_id = '" + id + "'";
        double walletResult = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                walletResult = (rs.getDouble("user_wallet"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (walletResult);
    }
}

// promo's logic start here
    // adding new promo
    // public boolean addNewPromo (String promoCode, float promoValue, Date expired) {
    //    return false; 
    // }