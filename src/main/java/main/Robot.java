package main;

public final class Robot {
    private int battery;
    private int pozX;
    private int pozY;
    private boolean isCharging;
    private int fostaBaterie;

    // constructor
    public Robot(final int battery, final int pozX,
                 final int pozY, final boolean isCharging) {
        this.battery = battery;
        this.pozX = pozX;
        this.pozY = pozY;
        this.isCharging = isCharging;
    }
    // getteri
    public int getBattery() {
        return battery;
    }
    public int getPozX() {
        return pozX;
    }
    public int getPozY() {
        return pozY;
    }
    public boolean isCharging() {
        return isCharging;
    }
    public int getFostaBaterie() {
        return fostaBaterie;
    }
    // setteri
    public void setBattery(final int battery) {
        this.battery = battery;
    }
    public void setPozX(final int pozX) {
        this.pozX = pozX;
    }
    public void setPozY(final int pozY) {
        this.pozY = pozY;
    }
    public void setIsCharging(final boolean isCharging) {
        this.isCharging = isCharging;
    }
    public void setFostaBaterie(final int fostaBaterie) {
        this.fostaBaterie = fostaBaterie;
    }
}
