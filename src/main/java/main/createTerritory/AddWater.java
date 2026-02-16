package main.createTerritory;

import fileio.PairInput;
import fileio.TerritorySectionParamsInput;
import fileio.WaterInput;
import main.Entity;
import main.Water;

import java.util.List;

public class AddWater {
    /**
     * Adaug pe patratica Apa.
     * @param territory
     * @param mat
     */
    public void addWater(final TerritorySectionParamsInput territory,
                         final List<Entity>[][] mat) {
        for (int i = 0; i < territory.getWater().size(); i++) {
            WaterInput apa = territory.getWater().get(i);
            for (int k = 0; k < apa.getSections().size(); k++) {
                PairInput loc = apa.getSections().get(k);
                Water apita = new Water(apa.getName(), apa.getMass(),
                        apa.getType(), apa.getSalinity(), apa.getPH(),
                        apa.getPurity(), apa.getTurbidity(),
                        apa.getContaminantIndex(), apa.isFrozen(), apa.getType());
                mat[loc.getX()][loc.getY()].add(apita);
            }
        }
    }
}
