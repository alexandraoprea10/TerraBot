package main.helpers;

import main.Entity;
import main.Water;
import main.air.Air;
import main.animal.Animal;
import main.plant.Plant;
import main.soil.Soil;

import java.util.List;

public final class ReturnHelper {
    private ReturnHelper() {

    }
    /**
     * Caut planta in patratica de entitati.
     * @param entities lista de entitati
     * @return daca exista sau nu planta
     */
    public static Plant returnPlant(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isPlant()) {
                Plant pl = (Plant) entity;
                if (!pl.getMaturityLevel().equals("dead")) {
                    return (Plant) entity;
                }
            }
        }
        return null;
    }

    /**
     * Caut animal in patratica de entitati.
     * @param entities lista de entitati
     * @return daca exista sau nu animal
     */
    public static Animal returnAnimal(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isAnimal()) {
                Animal animal =  (Animal) entity;
                if (!animal.getType().equals("out")) {
                    return (Animal) entity;
                }
            }
        }
        return null;
    }

    /**
     * Caut sol in patratica de entitati.
     * @param entities lista de entitati
     * @return daca exista sau nu sol
     */
    public static Soil returnSoil(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isSoil()) {
                return (Soil) entity;
            }
        }
        return null;
    }

    /**
     * Caut apa in patratica de entitati
     * @param entities lista de entitati
     * @return daca exista sau nu apa
     */
    public static Water returnWater(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isWater()) {
                return (Water) entity;
            }
        }
        return null;
    }
    /**
     * Cauta aer in patratica de entitati.
     * @param entities lista de entitati
     * @return returneaza aerul de pe patratica
     */
    public static Air returnAir(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isAir()) {
                return (Air) entity;
            }
        }
        return null;
    }
}
