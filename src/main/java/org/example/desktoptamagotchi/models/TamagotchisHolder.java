package org.example.desktoptamagotchi.models;

import java.util.ArrayList;
import java.util.HashMap;

// A singleton used for sharing the Tamagotchi between controllers
public final class TamagotchisHolder {
    private final HashMap<String, Tamagotchi> tamagotchis = new HashMap<>();
    private String currentTamagotchiName;
    private static final TamagotchisHolder instance = new TamagotchisHolder();

    // Private so that there only exists one instance of this class
    private TamagotchisHolder() {}

    public static TamagotchisHolder getInstance() {
        return instance;
    }

    public ArrayList<Tamagotchi> getTamagotchis() {
        return new ArrayList<>(tamagotchis.values());
    }

    public Tamagotchi getCurrentTamagotchi() {
        return tamagotchis.get(currentTamagotchiName);
    }

    public void addTamagotchi(Tamagotchi tamagotchi) {
        currentTamagotchiName = tamagotchi.getName();
        tamagotchis.put(currentTamagotchiName, tamagotchi);
    }

    public void setCurrentTamagotchiName(String newName) {
        currentTamagotchiName = newName;
    }

    public void removeTamagotchi(String name) {
        tamagotchis.remove(name);
    }
}
