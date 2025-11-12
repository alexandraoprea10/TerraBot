package main;

public class MountainAir extends Air {
    private double altitude;
    private int numberOfHikers;
    // constructori
    public MountainAir(String nume, double mass) {
        super(nume, mass);
        this.altitude = 0.0;
    }
    public MountainAir(String nume, double mass, double humidity, double temperature, double oxygenLevel, double altitude) {
        super(nume, mass, humidity, temperature, oxygenLevel);
        this.altitude = altitude;
    }
    // getter
    public double getAltitude() {
        return altitude;
    }
    public int getNumberOfHikers() {
        return numberOfHikers;
    }
    // setter
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    public void setNumberOfHikers(int numberOfHikers) {
        this.numberOfHikers = numberOfHikers;
    }
    @Override
    public double air_quality() {
        double oxygenFactor = getOxygenLevel() - (altitude / 1000 * 0.5);
        double score = oxygenFactor * 2 + getHumidity() * 0.6;
        double normalize_score = Math.max(0, Math.min(100, score));
        double result = Math.round(normalize_score * 100.0) / 100.0;
        return result;
    }
    @Override
    public double air_toxicity() {
        double toxicityAQ = 100 * (1 - air_quality() / 78.0);
        double result = Math.round(toxicityAQ * 100.0) / 100.0;
        return result;
    }
    @Override
    public String toxicity() {
        double toxicityAQ = 100 * (1 - air_quality() / 78.0);
        if (toxicityAQ > (0.8 * 78.0))
            return "toxic";
        else return "not toxic";
    }
    @Override
    public String result_event() {
        return "peopleHiking";
    }
    @Override
    public double update_air_quality() {
        double result = getAir_quality() - (numberOfHikers * 0.1);
        return result;
    }
    @Override
    public boolean isTropical() {
        return false;
    }
    @Override
    public boolean isPolar() {
        return false;
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
        return true;
    }
}
