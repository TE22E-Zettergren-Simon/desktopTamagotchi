package org.example.desktoptamagotchi.models;

// A singleton used for sharing the Tamagotchi between controllers
public final class TamagotchiHolder {
    private Tamagotchi tamagotchi;
    private static final TamagotchiHolder instance = new TamagotchiHolder();

    // Private so that there only exists one instance of this class
    private TamagotchiHolder() {}

    public static TamagotchiHolder getInstance() {
        return instance;
    }

    public void setTamagotchi(Tamagotchi tamagotchi) {
        this.tamagotchi = tamagotchi;
    }

    public Tamagotchi getTamagotchi() {
        return tamagotchi;
    }
}
