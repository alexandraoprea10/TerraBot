package main.createTerritory;

import fileio.PairInput;
import fileio.PlantInput;
import fileio.TerritorySectionParamsInput;
import main.Entity;
import main.plant.Plant;
import main.plant.Algae;
import main.plant.FloweringPlants;
import main.plant.GymnospermsPlants;
import main.plant.Mosses;
import main.plant.Ferns;

import java.util.List;

public class AddPlants {
    /**
     * Adaug pe patratica planta.
     * @param territory
     * @param mat
     */
    public void addPlants(final TerritorySectionParamsInput territory,
                          final List<Entity>[][] mat) {
        for (int i = 0; i < territory.getPlants().size(); i++) {
            PlantInput planta = territory.getPlants().get(i);
            for (int k = 0; k < planta.getSections().size(); k++) {
                PairInput loc = planta.getSections().get(k);
                Plant plantuta = null;
                if (planta.getType().equals("FloweringPlants")) {
                    plantuta = new FloweringPlants(planta.getName(),
                            planta.getMass(), 0, "young", planta.getType());
                } else if (planta.getType().equals("GymnospermsPlants")) {
                    plantuta = new GymnospermsPlants(planta.getName(),
                            planta.getMass(), 0, "young", planta.getType());
                } else if (planta.getType().equals("Ferns")) {
                    plantuta = new Ferns(planta.getName(),
                            planta.getMass(), 0, "young", planta.getType());
                } else if (planta.getType().equals("Mosses")) {
                    plantuta = new Mosses(planta.getName(),
                            planta.getMass(), 0, "young", planta.getType());
                } else if (planta.getType().equals("Algae")) {
                    plantuta = new Algae(planta.getName(),
                            planta.getMass(), 0, "young", planta.getType());
                }
                mat[loc.getX()][loc.getY()].add(plantuta);
            }

        }
    }
}
