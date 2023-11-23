package tubespbo.Controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import tubespbo.Model.Driver;
import tubespbo.Model.JopayWaitingList;
import tubespbo.Model.Order;
import tubespbo.Model.OrderStatusEnum;
import tubespbo.Model.Passanger;
import tubespbo.Model.Promo;
import tubespbo.Model.User;

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
            stmt.setFloat(4, 0);
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
    
    public boolean inputJopayList(int id, int idDriver, float saldo) {
        conn.connect();
        String query = "INSERT INTO gopaylist (passanger_id, driver_id, nominal) VALUES (?, ?, ?)";
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setInt(2, idDriver);
            stmt.setFloat(3, saldo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // update username passanger
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

    // update phone number passanger
    public boolean updatePhoneNumDataPassangerToDB(int idMasuk, String telepon) {
        conn.connect();
        String query = "UPDATE passangers SET passanger_phonNum = '" + telepon + "' WHERE passanger_id = '" + idMasuk
                + "';";
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

    public int getOrderCount() {
        conn.connect();
        String query = "SELECT COUNT(order_id) FROM orders";
        int result = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                result = (rs.getInt("COUNT(order_id)"));
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
    
    public boolean updateStatusOrder(int idOrder, String state) {
        conn.connect();
        String query = "UPDATE orders SET order_status = '" + state + "' WHERE order_id = '" + idOrder + "';";
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
        String query = "SELECT * "
                + "FROM users "
                + "JOIN passangers ON passangers.passanger_id = users.user_id "
                + "WHERE passangers.passanger_id = '" + id + "'";
        ArrayList<Passanger> listPass = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Passanger pass = new Passanger();
                pass.setUser_id(rs.getInt("user_id"));
                pass.setUser_name(rs.getString("users.user_name"));
                pass.setUser_pass(rs.getString("users.user_pass"));
                pass.setPhone_number(rs.getString("passangers.passanger_phonNum"));
                pass.setUser_wallet(rs.getFloat("user_wallet"));

                listPass.add(pass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listPass);
    }
    
    public ArrayList<JopayWaitingList> getWaitingList(int idDriver) {
        conn.connect();
        String query = "SELECT * "
                + "FROM gopaylist WHERE driver_id = '" + idDriver + "'";
        ArrayList<JopayWaitingList> listWaiting = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                JopayWaitingList wait = new JopayWaitingList();
                wait.setCust_id(rs.getInt("passanger_id"));
                wait.setDriver_id(rs.getInt("driver_id"));
                wait.setNominal(rs.getFloat("nominal"));
                listWaiting.add(wait);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listWaiting);
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

    public boolean inputDriverDataToWaitingList (String username, String password, String phonNum, String namaKendaraan, String tipe, String plat) {
        conn.connect();
        String query = "INSERT INTO waitinglist (driver_username, driver_password, driver_phonNum, vehicle_name, vehicle_type, vehicle_plate) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, username);            
            stmt.setString(2, password);
            stmt.setString(3, phonNum);
            stmt.setString(4, namaKendaraan);
            stmt.setString(5, tipe);
            stmt.setString(6, plat);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<Driver> getWaitingDriver(int id) {
        conn.connect();
        String query = "SELECT driver_username, driver_password, driver_phonNum, vehicle_name, vehicle_type, vehicle_plate "
                + "FROM waitinglist ";
        ArrayList<Driver> listDriver = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Driver drivers = new Driver();
                drivers.setUser_name(rs.getString("driver_username"));
                drivers.setUser_pass(rs.getString("driver_password"));
                drivers.setDriver_phonNum(rs.getString("driver_phonNum"));
                drivers.setVehicle_name(rs.getString("vehicle_name"));
                drivers.setVehicle_type(rs.getString("vehicle_type"));
                drivers.setVehicle_plate(rs.getString("vehicle_plate"));

                listDriver.add(drivers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listDriver);
    }

    public boolean updateUsernameDataDriverToDB(int idMasuk, String username) {
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

    public boolean updatePhoneNumDataDriverToDB (int idMasuk, String telepon) {
        conn.connect();
        String query = "UPDATE drivers SET driver_phonNum = '" + telepon + "' WHERE driver_id = '" + idMasuk + "';";
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

    public boolean updateVehicleNameDataDriverToDB (int idMasuk, String vehicleName) {
        conn.connect();
        String query = "UPDATE drivers SET vehicle_name = '" + vehicleName + "' WHERE driver_id = '" + idMasuk + "';";
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

    public boolean updateVehiclePlateDataDriverToDB (int idMasuk, String vehiclePlate) {
        conn.connect();
        String query = "UPDATE drivers SET vehicle_plate = '" + vehiclePlate + "' WHERE driver_id = '" + idMasuk + "';";
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

    public boolean deleteWaitingDriver(String username) {
        conn.connect();
        String query = "DELETE FROM waitinglist WHERE driver_username = '" + username + "'";
        boolean exists = false;
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            exists = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (exists);
    }
    
    public boolean deleteWaitingJopay(int idUser, int idDriver) {
        conn.connect();
        String query = "DELETE FROM gopaylist WHERE driver_id = '" + idDriver + "' AND passanger_id = '" + idUser +"'";
        boolean exists = false;
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            exists = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (exists);
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
                orders.setOrder_price(rs.getFloat("order_price"));
                orders.setOrder_final_price(rs.getFloat("order_final_price"));
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
                + "FROM orders WHERE (cust_id = '" + id + "' OR driver_id = '" + id + "') AND order_status = 'NOW'";
        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Order orders = new Order();
                orders.setOrder_id(rs.getInt("order_id"));
                orders.setOrder_destination(rs.getString("order_destination"));
                orders.setOrder_date(rs.getDate("order_date"));
                orders.setOrder_final_price(rs.getFloat("order_final_price"));
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
                + "FROM orders WHERE (cust_id = '" + id + "' OR driver_id = '" + id + "') AND order_status <> 'NOW'";
        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Order orders = new Order();
                orders.setOrder_id(rs.getInt("order_id"));
                orders.setOrder_destination(rs.getString("order_destination"));
                orders.setOrder_date(rs.getDate("order_date"));
                orders.setOrder_final_price(rs.getFloat("order_final_price"));
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
    public float getWallet(int id) {
        conn.connect();
        String query = "SELECT user_wallet FROM users WHERE user_id = '" + id + "'";
        float walletResult = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                walletResult = (rs.getFloat("user_wallet"));
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

    // get promo ID
    public int getPromoIdByCode(String inpCode) {
        int Id = 0;
        conn.connect();
        String query = "SELECT promo_id FROM promo WHERE promo_code = '" + inpCode + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Id = (rs.getInt("promo_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Id;
    }
//promo ends here


// update JoPay
    public boolean updateJoPay(int id, double saldo) {

        conn.connect();

        String query = "UPDATE users SET user_wallet = " + saldo + "WHERE user_id = " + id + ";";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
// order ride start here
    private static Map<Character, Integer> letterToNumber = new HashMap<>();

    // Menetapkan urutan huruf dan angka yang sesuai buat menentukan lokasi
    static {
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        for (int i = 0; i < letters.length; i++) {
            letterToNumber.put(letters[i], i + 1);
        }
    }

    // hitung tarif berdasarkan lokasi
    public int calculateCost(char source, char destination) {
        // Mendapatkan nilai urutan untuk setiap huruf
        int sourceNumber = letterToNumber.get(source);
        int destinationNumber = letterToNumber.get(destination);

        // Menghitung jarak dan harga
        int distance = Math.abs(destinationNumber - sourceNumber);
        int cost = distance * 1000;

        return cost;
    }

    // kalau jenis kendaraan nya mobil, harganya di kali 2
    public int calculateFinalCost(int baseCost, String selectedVehicle) {
        return selectedVehicle.equals("Mobil") ? baseCost * 2 : baseCost;
    }
    
    public boolean findPromo (String inpCode) {
        float val = 0;
        conn.connect();
        String query = "SELECT * FROM promo WHERE promo_code = '" + inpCode + "'";
        boolean found = false;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                found = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    // get promo value
    public float getPromoVal (String inpCode) {
        float val = 0;
        conn.connect();
        String query = "SELECT promo_value FROM promo WHERE promo_code = '" + inpCode + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                val = (rs.getFloat("promo_value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public User getUserByID(int id) {
        float val = 0;
        conn.connect();
        String query = "SELECT * FROM users WHERE user_id = " + id;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            User user = new User();
            while (rs.next()) {
                user.setUser_id(rs.getInt("user_id"));
                user.setUser_name(rs.getString("user_name"));
                user.setUser_pass(rs.getString("user_pass"));
                user.setUser_role(rs.getString("user_role"));
                user.setUser_wallet(rs.getFloat("user_wallet"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // find driver who's available
    public Driver getDriverAvailable(String jenisKendaraan) {
        Driver dr = new Driver();
        conn.connect();
        String query = "SELECT u.user_name, d.driver_id, d.driver_phonNum, d.vehicle_type, d.vehicle_name, d.vehicle_plate\r\n" + 
                "FROM drivers d \r\n" + 
                "JOIN users u ON u.user_id = d.driver_id\r\n" + 
                "WHERE u.user_id = d.driver_id AND d.vehicle_type = '" + jenisKendaraan + "' AND d.driver_status = \"AVAILABLE\" LIMIT 1;";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int dId = rs.getInt("d.driver_id");
                String dName = rs.getString("u.user_name");
                String dPhone = rs.getString("d.driver_phonNum");
                String dVtype = rs.getString("d.vehicle_type");
                String dPlate = rs.getString("d.vehicle_plate");
                dr = new Driver(dId, dName, dPhone, dVtype, dPlate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dr;
    }

    // user order
    public boolean createUserOrder (int custID, int promoID, String asal, String tujuan, Float harga, Float hargaAkhir, Driver dr) {
    
        conn.connect();
        String query = "INSERT INTO orders (cust_id, promo_id, driver_id, order_date, order_pickup, order_destination, order_price, order_final_price, order_vehicle_name, order_vehicle_plate, order_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt;
        try {
            java.sql.Date currentDate = getCurrentDate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, custID);
            stmt.setInt(2, promoID);
            stmt.setInt(3, dr.getDriver_id());
            stmt.setDate(4, currentDate);
            stmt.setString(5, asal);
            stmt.setString(6, tujuan);
            stmt.setFloat(7, harga);
            stmt.setFloat(8, hargaAkhir);
            stmt.setString(9, dr.getVehicle_name());
            stmt.setString(10, dr.getVehicle_plate());
            stmt.setString(11, "NOW");

            changeDriverStat(dr.getDriver_id());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // get current Date
    private java.sql.Date getCurrentDate() {
        java.util.Date today = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(today);
        return java.sql.Date.valueOf(formattedDate);
    }

    // change driver's status if they're taking an order
    private boolean changeDriverStat (int drvID) {
        conn.connect();
        String query = "UPDATE drivers\r\n" + //
                "SET driver_status = 'BOOKED'\r\n" + //
                "WHERE driver_id = '" + drvID + "';"; 
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

    // kembalikan status driver menjadi available setelah menyelesaikan order
    public boolean changeToAvailable (int drvID) {
        conn.connect();
        String query = "UPDATE drivers\r\n" + //
                "SET driver_status = 'AVAILABLE'\r\n" + //
                "WHERE driver_id = '" + drvID + "';"; 
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
    

// order ride end

// start switch on off status driver
    private String getDriverStat(int driverID) {
        String stat = "";
        conn.connect();
        String statsQuery = "SELECT driver_status FROM drivers WHERE driver_id = '" + driverID + "';";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(statsQuery);
            while (rs.next()) {
                stat = rs.getString("driver_status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stat;
    }

    
    public String getSwitchStatusText(int driverID) {
        String currentStatus = getDriverStat(driverID);
        if ("OFFLINE".equalsIgnoreCase(currentStatus)) {
            return "Now Offline";
        } else if ("AVAILABLE".equalsIgnoreCase(currentStatus)) {
            return "Now Available";
        } else {
            return "Unknown Status";
        }
    }

    public boolean driverOnOffStat(int driverID) {
        conn.connect();
        String stats = getDriverStat(driverID);

        if ("AVAILABLE".equalsIgnoreCase(stats)) {
            updateDriverStatus(driverID, "OFFLINE");
            return true;
        } else if ("OFFLINE".equalsIgnoreCase(stats)) {
            updateDriverStatus(driverID, "AVAILABLE");
            return true;
        } else {
            return false; 
        }
    }

    private void updateDriverStatus(int driverID, String newStatus) {
        String query = "UPDATE drivers SET driver_status = '" +  newStatus + "' WHERE driver_id = '" + driverID + "';";
        try {
            PreparedStatement statement = conn.con.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// end on off status driver
}