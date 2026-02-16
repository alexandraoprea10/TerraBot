package main.helpers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CommandInput;
import main.Entity;
import main.Robot;
import main.air.Air;
import main.air.DesertAir;
import main.air.MountainAir;
import main.air.TemperateAir;
import main.air.TropicalAir;
import main.air.Polar;
import java.util.List;

public final class WeatherUpdateHelper {
    private WeatherUpdateHelper() {

    }
    /**
     * Seteaza noua calitate a aerului.
     * @param entities lista de entitati
     * @param command comanda
     * @return daca s-a efectuat cu succes sau nu
     */
    public static int changeWeather(final List<Entity> entities,
                                    final CommandInput command) {
        int merge = 0;
        for (int k = 0; k < entities.size(); k++) {
            Entity entity = entities.get(k);
            if (entity.isAir()) {
                Air aer = (Air) entity;
                if (aer.isTropical()) {
                    TropicalAir trop = (TropicalAir) aer;
                    String eveniment = command.getType();
                    double rainfall = command.getRainfall();
                    trop.setEvent(eveniment);
                    trop.setRainfall(rainfall);
                    trop.setAirQuality(trop.updateAirQuality());
                    if (eveniment != null && eveniment.equals("rainfall")) {
                        merge = 1;
                    }
                } else if (aer.isPolar()) {
                    Polar polar = (Polar) aer;
                    String eveniment = command.getType();
                    double wind = command.getWindSpeed();
                    polar.setEvent(eveniment);
                    polar.setWindSpeed(wind);
                    polar.setAirQuality(polar.updateAirQuality());
                    if (eveniment != null && eveniment.equals("polarStorm")) {
                        merge = 1;
                    }
                } else if (aer.isTemperate()) {
                    TemperateAir temperate = (TemperateAir) aer;
                    String eveniment = command.getType();
                    String seas = command.getSeason();
                    temperate.setEvent(eveniment);
                    temperate.setSeason(seas);
                    temperate.setAirQuality(temperate.updateAirQuality());
                    if (eveniment != null && eveniment.equals("newSeason")) {
                        merge = 1;
                    }
                } else if (aer.isDesert()) {
                    DesertAir desert = (DesertAir) aer;
                    String eveniment = command.getType();
                    boolean deser = command.isDesertStorm();
                    desert.setEvent(eveniment);
                    desert.setDesertStorm(deser);
                    desert.setAirQuality(desert.updateAirQuality());
                    if (eveniment != null && eveniment.equals("desertStorm")) {
                        merge = 1;
                    }
                } else if (aer.isMountain()) {
                    MountainAir mountain = (MountainAir) aer;
                    String eveniment = command.getType();
                    int number = command.getNumberOfHikers();
                    mountain.setEvent(eveniment);
                    mountain.setNumberOfHikers(number);
                    mountain.setAirQuality(mountain.updateAirQuality());
                    if (eveniment != null && eveniment.equals("peopleHiking")) {
                        merge = 1;
                    }
                }
            }
        }
        return merge;
    }

    /**
     * Vezi daca gasesti factul si daca nu, returneaza 0. Altfel, merge = 1.
     * @param dimension dimensiunea matricei
     * @param name numele
     * @param mat matricea
     * @param improvment ce fel de improve
     * @param robotel robotul
     * @param command comanda
     * @param node nodul pentru printare
     * @return daca s-a efectuat cu succes sau nu
     */
    public static int improveEnv(final int dimension, final String name, final List<Entity>[][] mat,
                                 final String improvment, final Robot robotel,
                                 final CommandInput command, final ObjectNode node) {
        int merge = 0;
        for (int a = 0; a < dimension; a++) {
            for (int b = 0; b < dimension; b++) {
                List<Entity> entities = mat[a][b];
                for (int p = 0; p < entities.size(); p++) {
                    Entity entity = entities.get(p);
                    if (entity.getisScanned() && entity.getName().equals(name)
                            && !entity.getSubject().isEmpty()) {
                        for (int k = 0; k < entity.getSubject().size(); k++) {
                            String subject = entity.getSubject().get(k);
                            String[] comanda = subject.split(" ");
                            String ceModific = comanda[comanda.length - 1];
                            // node.put("campul", ceModific);
                            if (improvment.equals("plantVegetation")
                                    && entity.isPlant()
                                    && entity.getisScanned()) {
                                int ok = UpdateHelper.updateOxygen(
                                        mat[robotel.getPozX()]
                                                [robotel.getPozY()]);
                                if (ok == 1) {
                                    merge = 1;
                                    node.put("message",
                                            "The " + ceModific
                                                    + " was planted "
                                                    + "successfully.");
                                }
                            } else if (improvment.equals("fertilizeSoil")) {
                                int ok = UpdateHelper.updateFertilizer(
                                        mat[robotel.getPozX()]
                                                [robotel.getPozY()]);
                                if (ok == 1) {
                                    merge = 1;
                                    node.put("message",
                                            "The soil was "
                                                    + "successfully fertilized "
                                                    + "using "
                                                    + ceModific);
                                }
                            } else if (improvment.equals("increaseHumidity")) {
                                int ok = UpdateHelper.updateHumidity(
                                        mat[robotel.getPozX()]
                                                [robotel.getPozY()]);
                                if (ok == 1) {
                                    merge = 1;
                                    node.put("message",
                                            "The humidity was "
                                                    + "successfully increased "
                                                    + "using "
                                                    + command.getName());
                                }
                            } else if (improvment.equals("increaseMoisture")
                                    && entity.isWater()
                                    && entity.getisScanned()) {
                                int ok = UpdateHelper.updateWaterRet(entities);
                                if (ok == 1) {
                                    merge = 1;
                                    node.put("message",
                                            "The moisture was "
                                                    + "successfully increased "
                                                    + "using "
                                                    + command.getName());
                                }
                            }
                        }
                    }
                }
            }
        }
        return merge;
    }
}
