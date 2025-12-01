package main.Air;

import main.MagicNumbersDouble;
import main.MagicNumbersInt;

public final class MountainAir extends Air {
    private double altitude;
    private int numberOfHikers;
    // constructori
    public MountainAir(final String nume, final double mass) {
        super(nume, mass);
        this.altitude = 0.0;
    }
    public MountainAir(final String nume, final double mass, final double humidity,
                       final double temperature, final double oxygenLevel,
                       final double altitude, final String typ) {
        super(nume, mass, humidity, temperature, oxygenLevel, typ);
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
    public void setAltitude(final double altitude) {
        this.altitude = altitude;
    }
    public void setNumberOfHikers(final int numberOfHikers) {
        this.numberOfHikers = numberOfHikers;
    }
    @Override
    public double airQuality() {
        double oxygenFactor = getOxygenLevel()
                - (altitude / MagicNumbersInt.mie.getNumar()
                * MagicNumbersDouble.half.getNumar());
        double score = oxygenFactor
                * MagicNumbersInt.doi.getNumar()
                + getHumidity() * MagicNumbersDouble.waterRetFor2.getNumar();
        double normalizeScore = Math.max(0,
                Math.min(MagicNumbersInt.suta.getNumar(), score));
        double result = Math.round(normalizeScore
                * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        return result;
    }
    @Override
    public double airToxicity() {
        double toxicityAQ = MagicNumbersInt.suta.getNumar()
                * (MagicNumbersInt.unu.getNumar() - airQuality()
                / MagicNumbersDouble.sapteopt.getNumar());
        double result = Math.round(toxicityAQ
                * MagicNumbersDouble.normalize.getNumar())
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
                / MagicNumbersDouble.sapteopt.getNumar());
        if (toxicityAQ > (MagicNumbersDouble.oxygenMoss.getNumar()
                * MagicNumbersDouble.sapteopt.getNumar())) {
            return "toxic";
        } else {
            return "not toxic";
        }
    }
    @Override
    public String resultEvent() {
        return "peopleHiking";
    }
    @Override
    public double updateAirQuality() {
        double result = getAirQuality() - (numberOfHikers
                * MagicNumbersDouble.zerounu.getNumar());
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
