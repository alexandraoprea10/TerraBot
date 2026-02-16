package main.createTerritory;

import fileio.PairInput;
import fileio.SoilInput;
import fileio.TerritorySectionParamsInput;
import main.Entity;
import main.soil.DesertSoil;
import main.soil.ForestSoil;
import main.soil.Soil;
import main.soil.SwampSoil;
import main.soil.GrasslandSoil;
import main.soil.TundraSoil;

import java.util.List;

public class AddSoil {
    /**
     * Adaug pe patratica sol.
     * @param territory
     * @param mat
     */
    public void addSoil(final TerritorySectionParamsInput territory,
                        final List<Entity>[][] mat) {
        for (int i = 0; i < territory.getSoil().size(); i++) {
            SoilInput sol = territory.getSoil().get(i);
            for (int k = 0; k < sol.getSections().size(); k++) {
                PairInput loc = sol.getSections().get(k);
                Soil solul = null;
                if (sol.getType().equals("ForestSoil")) {
                    solul = new ForestSoil(sol.getName(), sol.getMass(),
                            sol.getNitrogen(), sol.getWaterRetention(),
                            sol.getSoilpH(), sol.getOrganicMatter(),
                            sol.getLeafLitter(), sol.getType());
                } else if (sol.getType().equals("SwampSoil")) {
                    solul = new SwampSoil(sol.getName(), sol.getMass(),
                            sol.getNitrogen(), sol.getWaterRetention(),
                            sol.getSoilpH(), sol.getOrganicMatter(),
                            sol.getWaterLogging(), sol.getType());
                } else if (sol.getType().equals("DesertSoil")) {
                    solul = new DesertSoil(sol.getName(), sol.getMass(),
                            sol.getNitrogen(), sol.getWaterRetention(),
                            sol.getSoilpH(), sol.getOrganicMatter(),
                            sol.getSalinity(), sol.getType());
                } else if (sol.getType().equals("GrasslandSoil")) {
                    solul = new GrasslandSoil(sol.getName(), sol.getMass(),
                            sol.getNitrogen(), sol.getWaterRetention(),
                            sol.getSoilpH(), sol.getOrganicMatter(),
                            sol.getRootDensity(), sol.getType());
                } else if (sol.getType().equals("TundraSoil")) {
                    solul = new TundraSoil(sol.getName(), sol.getMass(),
                            sol.getNitrogen(), sol.getWaterRetention(),
                            sol.getSoilpH(), sol.getOrganicMatter(),
                            sol.getPermafrostDepth(), sol.getType());
                }
                mat[loc.getX()][loc.getY()].add(solul);
            }
        }
    }
}
