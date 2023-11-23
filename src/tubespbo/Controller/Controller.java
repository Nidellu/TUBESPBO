package tubespbo.Controller;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import tubespbo.Model.*;

public class Controller {

    static DatabaseHandler conn = new DatabaseHandler();

    // input user's data
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

    // input driver's data
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

    // input passanger's data
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

    //update username passanger
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

    //update phone number passanger
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

    // update passanger's password
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

    // get list of passangers
    public ArrayList<Passanger> getPassangerByID(int id) {
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

    public ArrayList<Driver> getDriverByID(int id) {
        conn.connect();
        String query = "SELECT users.user_name, users.user_pass, drivers.driver_phonNum, drivers.vehicle_name, drivers.vehicle_type, drivers.vehicle_plate "
                + "FROM users "
                + "JOIN drivers ON drivers.driver_id = users.user_id "
                + "WHERE drivers.driver_id = '" + id + "'";
        ArrayList<Driver> listDriver = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Driver drivers = new Driver();
                drivers.setUser_name(rs.getString("users.user_name"));
                drivers.setUser_pass(rs.getString("users.user_pass"));
                drivers.setDriver_phonNum(rs.getString("drivers.driver_phonNum"));
                drivers.setVehicle_name(rs.getString("drivers.vehicle_name"));
                drivers.setVehicle_type(rs.getString("drivers.vehicle_type"));
                drivers.setVehicle_plate(rs.getString("drivers.vehicle_plate"));

                listDriver.add(drivers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listDriver);
    }

    // order status
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

    // get list of detail order
    public ArrayList<Order> getDetailOrder(int idOrder) {
        conn.connect();
        String query = "SELECT order_id, driver_id, cust_id, promo_id, order_pickup, order_destination, order_price, order_final_price, order_date, order_status, order_vehicle_name, order_vehicle_plate "
                + "FROM orders WHERE order_id = '" + idOrder + "'";
        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Order orders = new Order();
                orders.setDriver_id(rs.getInt("driver_id"));
                orders.setPromo_id(rs.getInt("promo_id"));
                orders.setCust_id(rs.getInt("cust_id"));
                orders.setOrder_pickup(rs.getString("order_pickup"));
                orders.setOrder_destination(rs.getString("order_destination"));
                orders.setOrder_date(rs.getDate("order_date"));
                orders.setOrder_price(rs.getDouble("order_price"));
                orders.setOrder_final_price(rs.getDouble("order_final_price"));
                orders.setOrder_status(getEnum(rs.getString("order_status")));
                orders.setOrder_vehicle_name(rs.getString("order_vehicle_name"));
                orders.setOrder_vehicle_plate(rs.getString("order_vehicle_plate"));
                listOrder.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listOrder);
    }

    public String getTimeOrder(int idOrder) {
        conn.connect();
        String query = "SELECT order_date "
                + "FROM orders WHERE order_id = '" + idOrder + "'";
        String listOrder = "";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Order orders = new Order();
                listOrder = (rs.getString("order_date"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listOrder);
    }

    public ArrayList<Order> getOrderNow(int id) {
        conn.connect();
        String query = "SELECT order_id, order_destination, order_date, order_final_price, order_status, order_vehicle_name "
                + "FROM orders WHERE cust_id = '" + id + "' AND order_status = 'NOW'";
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
                listOrder.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listOrder);
    }

    public ArrayList<Order> getOrderHistory(int id) {
        conn.connect();
        String query = "SELECT * "
                + "FROM orders WHERE cust_id = '" + id + "' AND order_status <> 'NOW'";
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
                listOrder.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listOrder);
    }

    // get user by username
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
    
    public boolean getOrder(int idOrder) {
        conn.connect();
        String query = "SELECT * FROM orders WHERE order_id = '" + idOrder + "'";
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

    // login
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

    // get user's wallet
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

    // promo's logic start here
    // adding new promo
    public boolean addNewPromo(String promoCode, float promoValue, Date expired) {
        conn.connect();
        String query = "INSERT INTO promo (promo_code, promo_exp, promo_value) VALUES (?, ?, ?)";
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, promoCode);
            stmt.setDate(2, (java.sql.Date) expired);
            stmt.setFloat(3, promoValue);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // show all promo 
    public ArrayList<Promo> getPromoList() {
        conn.connect();
        String query = "SELECT * FROM promo ORDER BY promo_exp"; // biar nampilin dari promo yang terbaru
        ArrayList<Promo> listpPromos = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Promo prm = new Promo();
                prm.setPromoID(rs.getInt("promo_id"));
                prm.setPromoCode(rs.getString("promo_code"));
                prm.setPromoValue(rs.getFloat("promo_value"));
                prm.setExpired(rs.getDate("promo_exp"));
                listpPromos.add(prm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listpPromos);
    }

    // check if the promo code still valid or not compared to current date
    public boolean checkPromoValidation(int id) {
        boolean valid = true;

        try {
            conn.connect();
            String query = "SELECT promo_exp FROM promo WHERE promo_id = '" + id + "'";
            try (Statement stmt = conn.con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                if (rs.next()) {
                    // Move the date retrieval inside the 'if' block
                    LocalDate expirationDate = rs.getDate("promo_exp").toLocalDate();
                    LocalDate currentDate = LocalDate.now();

                    // Compare dates using LocalDate methods
                    valid = !currentDate.isAfter(expirationDate);
                } else {
                    System.out.println("Promo code not found for id: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valid;
    }

    // deleting promo
    public static boolean deletePromo(int id) {
        conn.connect();

        String query = "DELETE FROM promo WHERE promo_id='" + id + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
