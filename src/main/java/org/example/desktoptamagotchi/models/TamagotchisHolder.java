package org.example.desktoptamagotchi.models;

import java.util.ArrayList;

// A singleton used for sharing the Tamagotchi between controllers
public final class TamagotchisHolder {
    private final ArrayList<Tamagotchi> tamagotchis = new ArrayList<>();
    private int currentTamagotchi;
    private static final TamagotchisHolder instance = new TamagotchisHolder();

    // Private so that there only exists one instance of this class
    private TamagotchisHolder() {}

    public static TamagotchisHolder getInstance() {
        return instance;
    }


    // Usage actions

    public ArrayList<Tamagotchi> getTamagotchis() {
        return tamagotchis;
    }

    public Tamagotchi getCurrentTamagotchi() {
        return tamagotchis.get(currentTamagotchi);
    }

    public void setCurrentTamagotchiName(String newName) {
        currentTamagotchi = indexFromName(newName);
    }

    // Adds a Tamagotchi to the singleton
    // If the Tamagotchis name already is taken an IllegalArgumentException is thrown
    public void addTamagotchi(Tamagotchi newTamagotchi) {
        for (Tamagotchi tamagotchi : tamagotchis) {
            if (tamagotchi.getName().equals(newTamagotchi.getName())) {
                throw new IllegalArgumentException("Tamagotchi with that name already exists");
            }
        }

        tamagotchis.add(newTamagotchi);
        setCurrentTamagotchiName(newTamagotchi.getName());
    }

    public void removeTamagotchi(String name) {
        tamagotchis.remove(indexFromName(name));
    }


    // Internal helpers

    // Gets the index belonging to the tamagotchi with the provided name
    // If none exists an IllegalArgumentException is thrown
    private int indexFromName(String name) {
        for (int i = 0; i < tamagotchis.size(); i++) {
            if (tamagotchis.get(i).getName().equals(name)) {
                return i;
            }
        }

        throw new IllegalArgumentException("There must be a tamagotchi with the provided name");
    }
}
