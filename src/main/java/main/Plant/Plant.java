package main.Plant;

import main.Entity;
import main.MagicNumbersDouble;

public abstract class Plant extends Entity {
    private String maturityLevel;
    private double nivelCrestere;
    private double oxygenCurent;
    private int momentScanare;
    // constructor
    public Plant(final String name, final double mass,
                 final String maturityLevel, final double oxygenCurent, final String typ) {
        super(name, mass, 0, typ);
        this.maturityLevel = maturityLevel;
        this.oxygenCurent = oxygenCurent;
        this.nivelCrestere = 0;
        this.momentScanare = -1;
    }
    // getter
    public final String getMaturityLevel() {
        return maturityLevel;
    }
    public final double getOxygenCurent() {
        return oxygenCurent;
    }
    public final double getNivelCrestere() {
        return nivelCrestere;
    }
    public final int getMomentScanare() {
        return momentScanare;
    }
    // setter
    public final void setMaturityLevel(final String maturityLevel) {
        this.maturityLevel = maturityLevel;
    }
    public final void setOxygenCurent(final double oxygenCurent) {
        this.oxygenCurent = oxygenCurent;
    }
    public final void setNivelCrestere(final double nivelCrestere) {
        this.nivelCrestere = nivelCrestere;
    }
    public final void setMomentScanare(final int momentScanare) {
        this.momentScanare = momentScanare;
    }

    /**
     * Calculeaza oxigenul in functie de maturitate.
     * @return
     */
    public double maturityOxigenLevel() {
        double result = 0.0;
        if (maturityLevel.equals("young")) {
            result = MagicNumbersDouble.zerodoi.getNumar();
        } else if (maturityLevel.equals("mature")) {
            result = MagicNumbersDouble.zerosapte.getNumar();
        } else if (maturityLevel.equals("old")) {
            result = MagicNumbersDouble.zeropatru.getNumar();
        }
        return Math.round(result * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
    }

    /**
     * Calculeaza oxigenul din planta.
     * @return
     */
    public abstract double oxigenFromPlant();

    /**
     * Calculeaza probabilitatea de a se agata TerraBot pe planta.
     * @return
     */
    public abstract double probabilityAttack();
    /**
     * Verifica ce fel de entitate este.
     */
    @Override
    public final boolean isPlant() {
        return true;
    }
    @Override
    public final boolean isAnimal() {
        return false;
    }
    @Override
    public final boolean isWater() {
        return false;
    }
    @Override
    public final boolean isSoil() {
        return false;
    }
    @Override
    public final boolean isAir() {
        return false;
    }
}
