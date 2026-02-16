package main.createTerritory;

import fileio.AnimalInput;
import fileio.PairInput;
import fileio.TerritorySectionParamsInput;
import main.Entity;
import main.animal.Animal;
import main.animal.Carnivores;
import main.animal.Herbivores;
import main.animal.Omnivores;
import main.animal.Detritivores;
import main.animal.Parasites;

import java.util.List;

public class AddAnimals {
    /**
     * Adauga pe patratica animalul.
     * @param territory
     * @param mat
     */
    public void addAnimals(final TerritorySectionParamsInput territory,
                           final List<Entity>[][] mat) {
        for (int i = 0; i < territory.getAnimals().size(); i++) {
            AnimalInput animalul = territory.getAnimals().get(i);
            for (int k = 0; k < animalul.getSections().size(); k++) {
                PairInput loc = animalul.getSections().get(k);
                Animal animalut = null;
                if (animalul.getType().equals("Herbivores")) {
                    animalut = new Herbivores(animalul.getName(),
                            animalul.getMass(), "hungry", "Herbivores");
                } else if (animalul.getType().equals("Carnivores")) {
                    animalut = new Carnivores(animalul.getName(),
                            animalul.getMass(), "hungry", "Carnivores");
                } else if (animalul.getType().equals("Omnivores")) {
                    animalut = new Omnivores(animalul.getName(),
                            animalul.getMass(), "hungry", "Omnivores");
                } else if (animalul.getType().equals("Detritivores")) {
                    animalut = new Detritivores(animalul.getName(),
                            animalul.getMass(), "hungry", "Detritivores");
                } else if (animalul.getType().equals("Parasites")) {
                    animalut = new Parasites(animalul.getName(),
                            animalul.getMass(), "hungry",  "Parasites");
                }
                mat[loc.getX()][loc.getY()].add(animalut);
            }
        }
    }
}
