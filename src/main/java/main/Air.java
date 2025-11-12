package main;

abstract public class Air extends Entity {
    private double humidity;
    private double temperature;
    private double oxygenLevel;
    private String event;
    private double air_quality;
    // constructori
    public Air(String name, double mass) {
        super(name, mass, 0);
        this.humidity = 0.0;
        this.temperature = 0.0;
        this.oxygenLevel = 0.0;
    }
    public Air(String name, double mass, double humidity, double temperature, double oxygenLevel) {
        super(name, mass, 0);
        this.humidity = humidity;
        this.temperature = temperature;
        this.oxygenLevel = oxygenLevel;
    }
    // getteri
    public double getHumidity() {
        return humidity;
    }
    public double getTemperature() {
        return temperature;
    }
    public double getOxygenLevel() {
        return oxygenLevel;
    }
    public  String getEvent() {
        return event;
    }
    public double getAir_quality() {
        return air_quality;
    }
    // setteri
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    public void setOxygenLevel(double oxygenLevel) {
        this.oxygenLevel = oxygenLevel;
    }
    public void setEvent(String event) {
        this.event = event;
    }
    public void setAir_quality(double air_quality) {
        this.air_quality = air_quality;
    }
    public abstract double air_quality();
    public abstract double air_toxicity();
    public String result_air(double quality) {
        if (quality < 40)
            return "poor";
        else if ((quality >= 40) && (quality <= 70))
            return "moderate";
        return "good";
    }
    public abstract String result_event();
    public abstract double update_air_quality();
    public abstract String toxicity();
    @Override
    public boolean isPlant() {
        return false;
    }
    @Override
    public boolean isAnimal() {
        return false;
    }
    @Override
    public boolean isWater() {
        return false;
    }
    @Override
    public boolean isSoil() {
        return false;
    }
    @Override
    public boolean isAir() {
        return true;
    }
    public abstract boolean isTropical();
    public abstract boolean isPolar();
    public abstract boolean isTemperate();
    public abstract boolean isDesert();
    public abstract boolean isMountain();
}
