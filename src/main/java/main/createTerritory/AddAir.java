package main.createTerritory;

import fileio.AirInput;
import fileio.PairInput;
import fileio.TerritorySectionParamsInput;
import main.Entity;
import main.air.Air;
import main.air.DesertAir;
import main.air.MountainAir;
import main.air.TemperateAir;
import main.air.TropicalAir;
import main.air.Polar;
import java.util.List;

public class AddAir {
    /**
     * Adaug pe patratica aer.
     * @param territory
     * @param mat
     */
    public void addAir(final TerritorySectionParamsInput territory,
                       final List<Entity>[][] mat) {
        for (int i = 0; i < territory.getAir().size(); i++) {
            AirInput aer = territory.getAir().get(i);
            for (int k = 0; k < aer.getSections().size(); k++) {
                PairInput loc = aer.getSections().get(k);
                Air aerut = null;
                if (aer.getType().equals("TropicalAir")) {
                    aerut = new TropicalAir(aer.getName(),
                            aer.getMass(), aer.getHumidity(),
                            aer.getTemperature(), aer.getOxygenLevel(),
                            aer.getCo2Level(), aer.getType());
                } else if (aer.getType().equals("PolarAir")) {
                    aerut = new Polar(aer.getName(), aer.getMass(),
                            aer.getHumidity(), aer.getTemperature(),
                            aer.getOxygenLevel(),
                            aer.getIceCrystalConcentration(), aer.getType());
                } else if (aer.getType().equals("TemperateAir")) {
                    aerut = new TemperateAir(aer.getName(), aer.getMass(),
                            aer.getHumidity(), aer.getTemperature(),
                            aer.getOxygenLevel(), aer.getPollenLevel(), aer.getType());
                } else if (aer.getType().equals("DesertAir")) {
                    aerut = new DesertAir(aer.getName(),
                            aer.getMass(), aer.getHumidity(),
                            aer.getTemperature(), aer.getOxygenLevel(),
                            aer.getDustParticles(), aer.getType());
                } else if (aer.getType().equals("MountainAir")) {
                    aerut = new MountainAir(aer.getName(),
                            aer.getMass(), aer.getHumidity(),
                            aer.getTemperature(), aer.getOxygenLevel(),
                            aer.getAltitude(), aer.getType());
                }
                if (aerut != null) {
                    aerut.setAirQuality(aerut.airQuality());
                    mat[loc.getX()][loc.getY()].add(aerut);
                }
            }
        }

    }
}
