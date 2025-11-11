package main;

public class DesertAir extends Air{
    private double dustParticles;
    private boolean desertStorm;
    // constructori
    public DesertAir(String nume, double mass) {
        super(nume, mass);
        this.dustParticles = 0.0;
    }
    public DesertAir(String nume, double mass, double humidity, double temperature, double oxygenLevel, double dustParticles) {
        super(nume, mass, humidity, temperature, oxygenLevel);
        this.dustParticles = dustParticles;
    }
    // getter
    public double getDustParticles() {
        return dustParticles;
    }
    public boolean getDesertStorm() {
        return desertStorm;
    }
    // setter
    public void setDustParticles(double dustParticles) {
        this.dustParticles = dustParticles;
    }
    public void setDesertStorm(boolean desertStorm) {
        this.desertStorm = desertStorm;
    }
    @Override
    public double air_quality() {
        double score = ((getOxygenLevel() * 2) - (dustParticles * 0.2) - (getTemperature() * 0.3));
        double result = Math.round(score * 100.0) / 100.0;
        return result;
    }
    @Override
    public double air_toxicity() {
        double toxicityAQ = 100 * (1 - air_quality() / 65.0);
        double result = Math.round(toxicityAQ * 100.0) / 100.0;
        return result;
    }
    @Override
    public String toxicity() {
        double toxicityAQ = 100 * (1 - air_quality() / 65.0);
        if (toxicityAQ > (0.8 * 65.0))
            return "toxic";
        else return "not toxic";
    }
    @Override
    public String result_event() {
        return "desertStorm";
    }
    @Override
    public double update_air_quality() {
        double r;
        if (desertStorm)
            r = 30.0;
        else r = 0.0;
        double result = getAir_quality() - r;
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
        return true;
    }
    @Override
    public boolean isMountain() {
        return false;
    }
}
