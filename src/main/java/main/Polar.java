package main;

public class Polar extends Air{
    private double iceCrystalConcentration;
    private double windSpeed;
    // constructori
    public Polar(String nume, double mass) {
        super(nume, mass);
        this.iceCrystalConcentration = 0.0;
    }
    public Polar(String nume, double mass, double humidity, double temperature, double oxygenLevel, double iceCrystalConcentration) {
        super(nume, mass,  humidity, temperature, oxygenLevel);
        this.iceCrystalConcentration = iceCrystalConcentration;
    }
    // getter
    public double getIceCrystalConcentration() {
        return iceCrystalConcentration;
    }
    public double getWindSpeed() {
        return windSpeed;
    }
    // setter
    public void setIceCrystalConcentration(double iceCrystalConcentration) {
        this.iceCrystalConcentration = iceCrystalConcentration;
    }
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    @Override
    public double air_quality() {
        double score = ((getOxygenLevel() * 2) + (100 - Math.abs(getTemperature())) - (iceCrystalConcentration * 0.5));
        double result = Math.round(score * 100.0) / 100.0;
        return result;
    }
    @Override
    public double air_toxicity() {
        double toxicityAQ = 100 * (1 - air_quality() / 142.0);
        double result = Math.round(toxicityAQ * 100.0) / 100.0;
        return result;
    }
    @Override
    public String toxicity() {
        double toxicityAQ = 100 * (1 - air_quality() / 142.0);
        if (toxicityAQ > (0.8 * 142.0))
            return "toxic";
        else return "not toxic";
    }
    @Override
    public String result_event() {
        return "polarStorm";
    }
    @Override
    public double update_air_quality() {
        double result = getAir_quality() - (windSpeed * 0.2);
        return result;
    }
    @Override
    public boolean isTropical() {
        return false;
    }
    @Override
    public boolean isPolar() {
        return true;
    }
    @Override
    public boolean isTemperate() {
        return false;
    }
    @Override
    public boolean isDesert() {
        return false;
    }
    @Override
    public boolean isMountain() {
        return false;
    }
}
