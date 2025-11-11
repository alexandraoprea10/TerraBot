package main;

public class Robot {
    private int battery;
    private int poz_x;
    private int poz_y;
    private boolean isCharging;

    // constructor
    public Robot(int battery, int poz_x, int poz_y, boolean isCharging) {
        this.battery = battery;
        this.poz_x = poz_x;
        this.poz_y = poz_y;
        this.isCharging = isCharging;
    }
    // getteri
    public int getBattery() {
        return battery;
    }
    public int getPoz_x() {
        return poz_x;
    }
    public int getPoz_y() {
        return poz_y;
    }
    public boolean isCharging() {
        return isCharging;
    }
    // setteri
    public void setBattery(int battery) {
        this.battery = battery;
    }
    public void setPoz_x(int poz_x) {
        this.poz_x = poz_x;
    }
    public void setPoz_y(int poz_y) {
        this.poz_y = poz_y;
    }
    public void setIsCharging(boolean isCharging) {
        this.isCharging = isCharging;
    }
}
