package main.helpers;

import main.Entity;
import main.plant.Plant;

import java.util.List;

public final class ExistingHelper {
    private ExistingHelper() {

    }
    /**
     * Verific daca exista planta
     * @param entities lista de entitati
     * @return daca exista sau nu planta
     */
    public static boolean existPlant(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isPlant()) {
                Plant pl = (Plant) entities.get(i);
                if (!pl.getMaturityLevel().equals("out")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verific daca exista animal.
     * @param entities lista de entitati
     * @return daca exista sau nu animal
     */
    public static boolean existAnimal(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isAnimal()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verific daca exista apa.
     * @param entities lista de entitati
     * @return daca exista sau nu apa
     */
    public static boolean existWater(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isWater()) {
                return true;
            }
        }
        return false;
    }
    /**
     * Elimin de pe patratica planta care e moarta.
     * @param entities lista de entitati
     */
    public static void verificaMoarteaPlantuta(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isPlant()) {
                Plant plantuta = (Plant) entity;
                if (plantuta.getMaturityLevel().equals("dead")) {
                    //entities.remove(i);
                    plantuta.setMaturityLevel("out");
                }
            }
        }
    }
}
