package main.helpers;

import main.Entity;
import main.air.Air;
import main.magicNumbers.MagicNumbersDouble;
import main.soil.Soil;

import java.util.List;

public final class UpdateHelper {
    private UpdateHelper() {

    }
    /**
     * Se actualizeaza orgmatter, e pentru testul cu improve.
     * @param entities lista de entitati
     * @return daca s-a realizat cu succes sau nu
     */
    public static int updateFertilizer(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isSoil()) {
                Soil sol = (Soil) entity;
                sol.setOrganicMatter(sol.getOrganicMatter()
                        + MagicNumbersDouble.zerodoi.getNumar());
                return 1;
            }
        }
        return 0;
    }
    /**
     * Se actualizeaza humidity, e pentru testul cu improve.
     * @param entities lista de entitati
     * @return daca s-a realizat cu succes sau nu
     */
    public static int updateHumidity(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isAir()) {
                Air aer = (Air) entity;
                aer.setHumidity(aer.getHumidity()
                        + MagicNumbersDouble.zerodoi.getNumar());
                aer.setAirQuality(aer.airQuality());
                return 1;
            }
        }
        return 0;
    }
    /**
     * Se va actualiza oxigenul.
     * @param entities lista de entitati
     * @return daca s-a actualizat sau nu
     */
    public static int updateOxygen(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isAir()) {
                Air aer = (Air) entity;
                aer.setOxygenLevel(aer.getOxygenLevel()
                        + MagicNumbersDouble.zerotrei.getNumar());
                aer.setAirQuality(aer.airQuality());
                return 1;
            }
        }
        return 0;
    }
    /**
     * Se actualizeaza waterRet, e pentru testul cu improve.
     * @param entities lista de entitati
     * @return daca s-a efectuat cu succes sau nu
     */
    public static int updateWaterRet(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isSoil()) {
                Soil sol = (Soil) entity;
                sol.setWaterRetention(sol.getWaterRetention()
                        + MagicNumbersDouble.zerodoi.getNumar());
                return 1;
            }
        }
        return 0;
    }
}
