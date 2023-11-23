/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tubespbo.Model;

/**
 *
 * @author brian
 */
public class Driver extends User {
    private int driver_id;
    private String driver_phonNum;
    private String vehicle_name;
    private String vehicle_type;
    private String vehicle_plate;
    private DriverStatEnum statusDriver;

    public Driver(int driver_id, String driver_phonNum, String vehicle_name, String vehicle_type, String vehicle_plate, int user_id, String user_name, String user_pass, String user_role, float user_wallet) {
        super(user_id, user_name, user_pass, user_role, user_wallet);
        this.driver_id = driver_id;
        this.driver_phonNum = driver_phonNum;
        this.vehicle_name = vehicle_name;
        this.vehicle_type = vehicle_type;
        this.vehicle_plate = vehicle_plate;
    }

    public Driver(int driver_id, String driver_phonNum, String vehicle_name, String vehicle_type, String vehicle_plate) {
        this.driver_id = driver_id;
        this.driver_phonNum = driver_phonNum;
        this.vehicle_name = vehicle_name;
        this.vehicle_type = vehicle_type;
        this.vehicle_plate = vehicle_plate;
    }

    public Driver() {
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getDriver_phonNum() {
        return driver_phonNum;
    }

    public void setDriver_phonNum(String driver_phonNum) {
        this.driver_phonNum = driver_phonNum;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getVehicle_plate() {
        return vehicle_plate;
    }

    public void setVehicle_plate(String vehicle_plate) {
        this.vehicle_plate = vehicle_plate;
    }

}
