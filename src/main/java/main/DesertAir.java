package main;

public final class DesertAir extends Air {
    private double dustParticles;
    private boolean desertStorm;
    // constructori
    public DesertAir(final String nume, final double mass) {
        super(nume, mass);
        this.dustParticles = 0.0;
    }
    public DesertAir(final String nume, final double mass,
                     final double humidity, final double temperature,
                     final double oxygenLevel, final double dustParticles, final String type) {
        super(nume, mass, humidity, temperature, oxygenLevel, type);
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
    public void setDustParticles(final double dustParticles) {
        this.dustParticles = dustParticles;
    }
    public void setDesertStorm(final boolean desertStorm) {
        this.desertStorm = desertStorm;
    }
    @Override
    public double airQuality() {
        double score = ((getOxygenLevel() * MagicNumbersInt.doi.getNumar())
                - (dustParticles * MagicNumbersDouble.dustParticles.getNumar())
                - (getTemperature() * MagicNumbersDouble.temperatura.getNumar()));
        double normalizeScore = Math.max(0, Math.min(MagicNumbersInt.suta.getNumar(), score));
        double result = Math.round(normalizeScore
                * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        return result;
    }
    @Override
    public double airToxicity() {
        double toxicityAQ = MagicNumbersInt.suta.getNumar()
                * (MagicNumbersInt.unu.getNumar() - airQuality()
                / MagicNumbersDouble.airDesert.getNumar());
        double result = Math.round(toxicityAQ
                * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        return result;
    }
    @Override
    public String toxicity() {
        double toxicityAQ = MagicNumbersInt.suta.getNumar()
                * (MagicNumbersInt.unu.getNumar() - airQuality()
                / MagicNumbersDouble.airDesert.getNumar());
        if (toxicityAQ > (MagicNumbersDouble.toxic.getNumar()
                * MagicNumbersDouble.airDesert.getNumar())) {
            return "toxic";
        }
        return "not toxic";
    }
    @Override
    public String resultEvent() {
        return "desertStorm";
    }
    @Override
    public double updateAirQuality() {
        double r;
        if (desertStorm) {
            r = MagicNumbersDouble.treizeci.getNumar();
        } else {
            r = 0.0;
        }
        double result = getAirQuality() - r;
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
