package org.example.desktoptamagotchi;

// A singleton used for sending the name of the first tamagotchi from the start screen to the tamagotchi screen
public final class NameHolder {
    private String name;
    private static final NameHolder instance = new NameHolder();

    // Private so that there only exists one instance of this class
    private NameHolder() {}

    public static NameHolder getInstance() {
        return instance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
