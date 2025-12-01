package main.Air;

import main.MagicNumbersDouble;
import main.MagicNumbersInt;

public final class TemperateAir extends Air {
    private double pollenLevel;
    private String season;
    // constructori
    public TemperateAir(final String nume, final double mass) {
        super(nume, mass);
        this.pollenLevel = 0.0;
    }
    public TemperateAir(final String name, final double mass, final double humidity,
                        final double temperature, final double oxygenLevel,
                        final double pollenLevel, final String typ) {
        super(name, mass,  humidity, temperature, oxygenLevel, typ);
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
    public void setPollenLevel(final double pollenLevel) {
        this.pollenLevel = pollenLevel;
    }
    public void setSeason(final String season) {
        this.season = season;
    }
    @Override
    public double airQuality() {
        double score = ((getOxygenLevel() * MagicNumbersInt.oxygen.getNumar())
                + (getHumidity() * MagicNumbersDouble.humidity.getNumar())
                - (pollenLevel * MagicNumbersDouble.pollen.getNumar()));
        double normalizeScore = Math.max(0,
                Math.min(MagicNumbersInt.suta.getNumar(), score));
        double result = Math.round(normalizeScore
                * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        if (result > MagicNumbersDouble.normalize.getNumar()) {
            result = MagicNumbersDouble.normalize.getNumar();
        }
        return result;
    }
    @Override
    public double airToxicity() {
        double toxicityAQ = MagicNumbersInt.suta.getNumar()
                * (1 - airQuality() / MagicNumbersDouble.airTemperate.getNumar());
        double result = Math.round(toxicityAQ * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        if (result < 0) {
            return 0.0;
        }
        return result;
    }
    @Override
    public String toxicity() {
        double toxicityAQ = MagicNumbersInt.suta.getNumar()
                * (MagicNumbersInt.unu.getNumar() - airQuality()
                / MagicNumbersDouble.airTemperate.getNumar());
        if (toxicityAQ > (MagicNumbersDouble.toxic.getNumar()
                * MagicNumbersDouble.airTemperate.getNumar())) {
            return "toxic";
        } else {
            return "not toxic";
        }
    }
    @Override
    public String resultEvent() {
        return "newSeason";
    }
    @Override
    public double updateAirQuality() {
        double seasonPenalty = 0.0;
        if (season != null) {
            if (season.equalsIgnoreCase("Spring")) {
                seasonPenalty = MagicNumbersDouble.penalty.getNumar();
            }
        }
        double result = getAirQuality() - seasonPenalty;
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
