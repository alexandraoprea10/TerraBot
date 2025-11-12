package main;

public class TropicalAir extends Air {
    private double co2Level;
    private double rainfall;
    // constructori
    public TropicalAir(String nume, double mass) {
        super(nume, mass);
        this.co2Level = 0.0;
    }
    public TropicalAir(String nume, double mass, double humidity, double temperature, double oxygenLevel, double co2Level) {
        super(nume, mass,  humidity, temperature, oxygenLevel);
        this.co2Level = co2Level;
    }
    // getter
    public double getCo2Level() {
        return co2Level;
    }
    public double getRainfall() {
        return rainfall;
    }
    // setter
    public void setCo2Level(double co2Level) {
        this.co2Level = co2Level;
    }
    public void  setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }
    @Override
    public double air_quality() {
        double score = ((getOxygenLevel() * 2) + (getHumidity() * 0.5) - (co2Level * 0.01));
        double normalize_score = Math.max(0, Math.min(100, score));
        double result = Math.round(normalize_score * 100.0) / 100.0;
        return result;
    }
    @Override
    public double air_toxicity() {
        double toxicityAQ = 100 * (1 - air_quality() / 82.0);
        double result = Math.round(toxicityAQ * 100.0) / 100.0;
        double normalize_score = Math.max(0, Math.min(100, result));
        double res = Math.round(normalize_score * 100.0) / 100.0;
        return res;
    }
    @Override
    public String toxicity() {
        double toxicityAQ = 100 * (1 - air_quality() / 82.0);
        if (toxicityAQ > (0.8 * 82.0))
            return "toxic";
        else return "not toxic";
    }
    @Override
    public String result_event() {
        return "rainfall";
    }
    @Override
    public double update_air_quality() {
        double result = getAir_quality() + (rainfall * 0.3);
        return result;
    }
    @Override
    public boolean isTropical() {
        return true;
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
        return false;
    }
}
