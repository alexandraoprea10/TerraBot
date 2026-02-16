package main.air;

import main.Entity;
import main.magicNumbers.MagicNumbersDouble;
import main.magicNumbers.MagicNumbersInt;

public abstract class Air extends Entity {
    private double humidity;
    private double temperature;
    private double oxygenLevel;
    private String event;
    private double airQuality;
    // constructori
    public Air(final String name, final double mass) {
        super(name, mass, 0, " ");
        this.humidity = 0.0;
        this.temperature = 0.0;
        this.oxygenLevel = 0.0;
    }
    public Air(final String name, final double mass, final double humidity,
               final double temperature, final double oxygenLevel, final String typ) {
        super(name, mass, 0, typ);
        this.humidity = humidity;
        this.temperature = temperature;
        this.oxygenLevel = oxygenLevel;
    }
    // getteri

    /**
     * Returneaza umiditatea.
     * @return
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * Returneaza temperatura.
     * @return
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Returneaza nivelul de oxigen al plantei.
     * @return
     */
    public double getOxygenLevel() {
        return oxygenLevel;
    }

    /**
     * Returneaza evenimentul care se petrece.
     * @return
     */
    public String getEvent() {
        return event;
    }
    /**
     * Returneaza calitatea aerului.
     * @return
     */
    public double getAirQuality() {
        return airQuality;
    }
    // setteri

    /**
     * Seteaza umiditatea.
     * @param humidity
     */
    public void setHumidity(final double humidity) {
        this.humidity = humidity;
    }

    /**
     * Seteaza temperatura.
     * @param temperature
     */
    public void setTemperature(final double temperature) {
        this.temperature = temperature;
    }

    /**
     * Seteaza oxigenul.
     * @param oxygenLevel
     */
    public void setOxygenLevel(final double oxygenLevel) {
        this.oxygenLevel = oxygenLevel;
    }

    /**
     * Seteaza evenimentul.
     * @param event
     */
    public void setEvent(final String event) {
        this.event = event;
    }

    /**
     * Seteaza calitatea aerului
     * @param airQual
     */
    public void setAirQuality(final double airQual) {
        if (airQual > MagicNumbersDouble.normalize.getNumar()) {
            this.airQuality = MagicNumbersDouble.normalize.getNumar();
        }
        this.airQuality = airQual;
    }

    /**
     * Calculeaza calitatea aerului pentru fiecare tip de aer.
     * @return
     */
    public abstract double airQuality();

    /**
     * Calculeaza toxicitatea aerului pentru fiecare tip de aer.
     * @return
     */
    public abstract double airToxicity();

    /**
     * Returneaza ce tip de aer este.
     * @param quality
     * @return
     */
    public String resultAir(final double quality) {
        if (quality < MagicNumbersInt.patruzero.getNumar()) {
            return "poor";
        } else if ((quality >= MagicNumbersInt.patruzero.getNumar())
                && (quality <= MagicNumbersInt.saptezero.getNumar())) {
            return "moderate";
        }
        return "good";
    }

    /**
     * Returneaza ce tip de eveniment se intampla pentru fiecare tip de aer.
     * @return
     */
    public abstract String resultEvent();

    /**
     * Updateaza calitatea aerului daca se fac modificari.
     * @return
     */
    public abstract double updateAirQuality();

    /**
     * Returneaza ce tip de toxiicitate este in aer.
     * @return
     */
    public abstract String toxicity();

    /**
     * Returneaza false ca e aer.
     * @return
     */
    @Override
    public final boolean isPlant() {
        return false;
    }

    /**
     * Returneaza false ca e aer.
     * @return
     */
    @Override
    public final boolean isAnimal() {
        return false;
    }

    /**
     * Returneaza false ca e aer.
     * @return
     */
    @Override
    public final boolean isWater() {
        return false;
    }

    /**
     * Returneaza false ca e aer.
     * @return
     */
    @Override
    public final boolean isSoil() {
        return false;
    }

    /**
     * Returneaza true ca e aer.
     * @return
     */
    @Override
    public final boolean isAir() {
        return true;
    }

    /**
     * Verifica daca aerul e tropical.
     * @return
     */
    public abstract boolean isTropical();

    /**
     * Verifica daca aerul e polar.
     * @return
     */
    public abstract boolean isPolar();

    /**
     * Verifica daca aerul e temperat.
     * @return
     */
    public abstract boolean isTemperate();

    /**
     * Verifica daca aerul e de desert.
     * @return
     */
    public abstract boolean isDesert();

    /**
     * Verifica daca aerul e montan.
     * @return
     */
    public abstract boolean isMountain();
}
