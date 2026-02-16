package main.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CommandInput;
import main.Entity;
import main.Robot;
import main.air.Air;
import main.helpers.CalculateHelper;
import main.soil.Soil;

import java.util.LinkedHashMap;
import java.util.List;

public class PrintMap implements Command {
    /**
     * Execute command.
     * @param okSimulation
     * @param node
     * @param opresteSimulare
     * @param contorComenzi
     * @param nextSimulare
     * @param i
     * @param robotel
     * @param mat
     * @param mapper
     * @param dimension
     * @param command
     * @param factsAndSubjects
     */
    @Override
    public void execute(final int okSimulation, final ObjectNode node,
                        final int opresteSimulare, final int contorComenzi,
                        final int nextSimulare, final int i,
                        final Robot robotel, final List<Entity>[][] mat,
                        final ObjectMapper mapper, final int dimension,
                        final CommandInput command,
                        final LinkedHashMap<String, List<String>> factsAndSubjects) {
        if (okSimulation == 0) {
            node.put("message",
                    "ERROR: Simulation not started. Cannot perform action");
        } else if (robotel.getBattery() < 0) {
            node.put("message",
                    "ERROR: Not enough battery left. Cannot perform action");
        } else if (robotel.isCharging()) {
            node.put("message",
                    "ERROR: Robot still charging. Cannot perform action");
        } else {
            ArrayNode mapNode = mapper.createArrayNode();
            for (int a = 0; a < dimension; a++) {
                for (int b = 0; b < dimension; b++) {
                    List<Entity> entities = mat[b][a];
                    ObjectNode entityNode = mapper.createObjectNode();
                    ArrayNode entitiesNode = mapper.createArrayNode();
                    entitiesNode.add(b);
                    entitiesNode.add(a);
                    entityNode.set("section", entitiesNode);
                    // calculeaza numarul de obiecte
                    int contor = CalculateHelper.calculateObject(entities);
                    entityNode.put("totalNrOfObjects", contor);
                    String airquality = null;
                    String soilQuality = null;
                    for (int k = 0; k < entities.size(); k++) {
                        Entity entity = entities.get(k);
                        if (entity.isAir()) {
                            Air aer = (Air) entity;
                            double valoareAer = aer.getAirQuality();
                            airquality = aer.resultAir(valoareAer);
                        } else if (entity.isSoil()) {
                            Soil sol = (Soil) entity;
                            double valoareSol = sol.soilQuality();
                            soilQuality = sol.resultSoil(valoareSol);
                        }
                    }
                    entityNode.put("airQuality", airquality);
                    entityNode.put("soilQuality", soilQuality);
                    mapNode.add(entityNode);
                }
            }
            node.set("output", mapNode);
        }
    }
}
