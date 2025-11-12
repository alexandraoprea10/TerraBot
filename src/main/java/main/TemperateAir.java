package main;

public class TemperateAir extends Air{
    private double pollenLevel;
    private String season;
    // constructori
    public TemperateAir(String nume, double mass) {
        super(nume, mass);
        this.pollenLevel = 0.0;
    }
    public TemperateAir(String name, double mass, double humidity, double temperature, double oxygenLevel, double pollenLevel) {
        super(name, mass,  humidity, temperature, oxygenLevel);
        this.pollenLevel = pollenLevel;
    }
    // getter
    public double getPollenLevel() {
        return pollenLevel;
    }
    public String getSeason() {
        return season;
    }
    // setter
    public void setPollenLevel(double pollenLevel) {
        this.pollenLevel = pollenLevel;
    }
    public void setSeason(String season) {
        this.season = season;
    }
    @Override
    public double air_quality() {
        double score = ((getOxygenLevel() * 2) + (getHumidity() * 0.7) - (pollenLevel * 0.1));
        double normalize_score = Math.max(0, Math.min(100, score));
        double result = Math.round(normalize_score * 100.0) / 100.0;
        return result;
    }
    @Override
    public double air_toxicity() {
        double toxicityAQ = 100 * (1 - air_quality() / 84.0);
        double result = Math.round(toxicityAQ * 100.0) / 100.0;
        return result;
    }
    @Override
    public String toxicity() {
        double toxicityAQ = 100 * (1 - air_quality() / 84.0);
        if (toxicityAQ > (0.8 * 84.0))
            return "toxic";
        else return "not toxic";
    }
    @Override
    public String result_event() {
        return "newSeason";
    }
    @Override
    public double update_air_quality() {
        double seasonPenalty = 0.0;
        if (season.equalsIgnoreCase("Spring"))
            seasonPenalty = 15.0;
        double result = getAir_quality() - seasonPenalty;
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
        return true;
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
