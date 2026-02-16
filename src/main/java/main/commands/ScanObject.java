package main.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import main.Entity;
import main.Water;
import main.animal.Animal;
import main.helpers.ExistingHelper;
import main.helpers.ReturnHelper;
import main.plant.Plant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public final class ScanObject {
    private ScanObject() {

    }
    /**
     * Scaneaza obiectele si returneaza daca e succes
     * @param entities lista de entitati
     * @param color culoarea
     * @param smell miros
     * @param sound sunet
     * @param iteratii timestamp
     * @param factsAndSubjects lista de facts
     * @param scanObject lista de facts dar care e DOAR pt scanobj
     * @param node nodul de printare
     * @return daca e succes sau nu
     */
    public static int scanObjects(final List<Entity> entities, final String color,
                                  final String smell, final String sound,
                                  final int iteratii, final LinkedHashMap<String,
                    List<String>> factsAndSubjects, final LinkedHashMap<String,
                    List<String>> scanObject, final ObjectNode node) {
        int sePoate = 0;
        if (ExistingHelper.existWater(entities)
                && (color.equals("none"))
                && (smell.equals("none")
                && (sound.equals("none")))) {
            sePoate = 1;
            Water apa = ReturnHelper.returnWater(entities);
            if (apa != null) {
                apa.setIsScanned(true);
                scanObject.put(apa.getName(), new ArrayList<>());
                apa.setMomentScanare(iteratii);
            }
            node.put("message", "The scanned object is water.");
        } else if (ExistingHelper.existPlant(entities)
                && (!color.equals("none"))
                && (!smell.equals("none")
                && (sound.equals("none")))) {
            sePoate = 1;
            Plant planta = ReturnHelper.returnPlant(entities);
            if (planta != null) {
                factsAndSubjects.put(planta.getName(), new ArrayList<>());
                scanObject.put(planta.getName(), new ArrayList<>());
                planta.setMomentScanare(iteratii);
            }
            node.put("message", "The scanned object is a plant.");
        } else if (ExistingHelper.existAnimal(entities)
                && (!color.equals("none"))
                && (!smell.equals("none")
                && (!sound.equals("none")))) {
            sePoate = 1;
            Animal animalul = ReturnHelper.returnAnimal(entities);
            if (animalul != null) {
                animalul.setIsScanned(true);
            }
            node.put("message",
                    "The scanned object is an animal.");
        } else {
            node.put("message",
                    "ERROR: Object not found. Cannot perform action");
        }
        return sePoate;
    }
}
