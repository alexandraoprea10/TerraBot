package main.helpers;

import main.Entity;
import main.Robot;
import main.Water;
import main.air.Air;
import main.animal.Animal;
import main.magicNumbers.MagicNumbersDouble;
import main.plant.Plant;
import main.soil.Soil;

import java.util.List;

import static main.helpers.ExistingHelper.verificaMoarteaPlantuta;


public final class InteractionsHelper {
    private InteractionsHelper() {

    }
    /**
     * Se fac interactiunile automate la cate o iteratie.
     * @param entities lista de entitati
     * @param okInterAnimal daca se vor realiza interactiunile animal-soil
     */
    public static void interactiuniEveryIteration(final List<Entity> entities,
                                                  final int okInterAnimal,
                                                  final int okSchimbariMeteo) {
        Plant planta =  ReturnHelper.returnPlant(entities);
        Animal animal =  ReturnHelper.returnAnimal(entities);
        Soil soil = ReturnHelper.returnSoil(entities);
        Water water =  ReturnHelper.returnWater(entities);
        Air air =  ReturnHelper.returnAir(entities);
        if (air != null
                && animal != null
                && air.toxicity().equals("toxic")) {
            animal.setState("sick");
        }
        if (soil != null && planta != null && planta.getisScanned()) {
            planta.setNivelCrestere(planta.getNivelCrestere()
                    + MagicNumbersDouble.zerodoi.getNumar());
        }
        if (water != null && planta != null && planta.getisScanned()) {
            planta.setNivelCrestere(planta.getNivelCrestere()
                    + MagicNumbersDouble.zerodoi.getNumar());
        }
        if (planta != null && air != null && planta.getisScanned()) {
            if (planta.getNivelCrestere() >= 1) {
                if (planta.getMaturityLevel().equals("young")) {
                    planta.setMaturityLevel("mature");
                } else if (planta.getMaturityLevel().equals("mature")) {
                    planta.setMaturityLevel("old");
                } else if (planta.getMaturityLevel().equals("old")) {
                    planta.setMaturityLevel("dead");
                }
                planta.setNivelCrestere(0);
            }
            air.setOxygenLevel(Math.round((air.getOxygenLevel()
                    + planta.oxigenFromPlant()
                    + planta.maturityOxigenLevel())
                    * MagicNumbersDouble.normalize.getNumar())
                    / MagicNumbersDouble.normalize.getNumar());
            if (okSchimbariMeteo == 0) {
                air.setAirQuality(Math.round(air.airQuality()
                        * MagicNumbersDouble.normalize.getNumar())
                        / MagicNumbersDouble.normalize.getNumar());
            }
            if (planta.getMaturityLevel().equals("dead")
                    || planta.getMaturityLevel().equals("out")) {
                air.setOxygenLevel(air.getOxygenLevel() - planta.oxigenFromPlant());
            }
        }
        if (okInterAnimal == 1) {
            if (planta != null && water != null && !planta.getMaturityLevel().equals("out")) {
                if (soil != null) {
                    soil.setOrganicMatter(soil.getOrganicMatter()
                            + MagicNumbersDouble.oxygenMoss.getNumar());
                }
            } else if ((planta != null
                    && !planta.getMaturityLevel().equals("out")) || water != null) {
                if (soil != null) {
                    soil.setOrganicMatter(soil.getOrganicMatter()
                            + MagicNumbersDouble.half.getNumar());
                }
            }
        }
        if (animal != null
                && water != null && animal.getisScanned()) {
            animal.setMass(animal.getMass() + water.getMass());
        }
    }

    /**
     * Se fac interactiunile automate la 2 iteratii.
     * @param entities lista de entitati
     */
    public static void interactiuniEveryTwoIterations(final List<Entity> entities) {
        Soil soil = ReturnHelper.returnSoil(entities);
        Water water = ReturnHelper.returnWater(entities);
        Air air = ReturnHelper.returnAir(entities);
        if (water != null && air != null && water.getisScanned()) {
            double result = Math.round((air.getHumidity()
                    + MagicNumbersDouble.zerounu.getNumar())
                    * MagicNumbersDouble.normalize.getNumar())
                    / MagicNumbersDouble.normalize.getNumar();
            air.setHumidity(result);
            air.setAirQuality(Math.round(air.airQuality()
                    * MagicNumbersDouble.normalize.getNumar())
                    / MagicNumbersDouble.normalize.getNumar());
        }
        if (water != null && soil != null && water.getisScanned()) {
            double result = Math.round((soil.getWaterRetention()
                    + MagicNumbersDouble.zerounu.getNumar())
                    * MagicNumbersDouble.normalize.getNumar())
                    / MagicNumbersDouble.normalize.getNumar();
            soil.setWaterRetention(result);
        }
    }

    /**
     * Calculeaza interactiunile pentru fiecare patratica.
     * @param dimension dimensiunea matricei
     * @param mat matricea
     * @param iteratii timestampul
     * @param fostaIteratie fostul timestamp
     * @param robotel robotul
     * @param incepeIteratie unde incepe iteratia
     * @param okInterAnimal daca se vor realiza interactiunile animal-soil
     */
    public static void doInteractions(final int dimension, final List<Entity>[][] mat,
                                      final int iteratii, final int fostaIteratie,
                                      final Robot robotel, final int incepeIteratie,
                                      final int okInterAnimal, final int okSchimbariMeteo) {
        for (int a = 0; a < dimension; a++) {
            for (int b = 0; b < dimension; b++) {
                List<Entity> entities = mat[a][b];
                if (iteratii - fostaIteratie > 1) {
                    for (int p = fostaIteratie; p <= iteratii; p++) {
                        List<Entity> enti = mat[robotel.getPozX()][robotel.getPozY()];
                        verificaMoarteaPlantuta(enti);
                        interactiuniEveryIteration(entities, okInterAnimal, okSchimbariMeteo);
                        if (iteratii > incepeIteratie
                                && (iteratii - incepeIteratie) % 2 == 0) {
                            interactiuniEveryTwoIterations(entities);
                        }
                    }
                } else {
                    interactiuniEveryIteration(entities, okInterAnimal, okSchimbariMeteo);
                    if (iteratii > incepeIteratie
                            && (iteratii - incepeIteratie) % 2 == 0) {
                        interactiuniEveryTwoIterations(entities);
                    }
                }
            }
        }
    }
}
