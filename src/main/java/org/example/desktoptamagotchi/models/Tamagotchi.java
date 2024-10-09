package org.example.desktoptamagotchi.models;

import java.util.ArrayList;
import java.util.Random;

public class Tamagotchi {
    private final String name;
    private int hunger = 3;
    private int boredom = 3;
    private final ArrayList<String> vocabulary = new ArrayList<>();
    private boolean isAlive = true;

    private static final Random generator = new Random();

    public Tamagotchi(String name) {
        this.name = name;
        vocabulary.add("Hi!");
    }


    // Computer controlled actions

    // Increase hunger and boredom and check if the Tamagotchi is still alive
    public void tick() {
        hunger++;
        boredom++;

        isAlive = hunger < 10 && boredom < 10;
    }


    // Player controlled actions

    // Reduce the Tamagotchi's hunger
    public void feed() {
        reduceHunger();
    }

    // Get a random word from the Tamagotchi and reduce its boredom
    public String speak() {
        reduceBoredom();

        int index = generator.nextInt(vocabulary.size());
        return vocabulary.get(index);
    }

    // Add a new phrase to the Tamagotchi's vocabulary and reduce its boredom
    public void teachPhrase(String newPhrase) {
        reduceBoredom();

        vocabulary.add(newPhrase);
    }


    // Internal helpers

    // Reduce hunger with a random amount between 1 and 4
    private void reduceHunger() {
        int amount = generator.nextInt(1, 5);
        hunger -= amount;
        if (hunger < 0) hunger = 0;
    }

    // Reduce boredom with a random amount between 1 and 4
    private void reduceBoredom() {
        int amount = generator.nextInt(1, 5);
        boredom -= amount;
        if (boredom < 0) boredom = 0;
    }


    // Getters

    public String getName() {
        return name;
    }

    public int getHunger() {
        return hunger;
    }

    public int getBoredom() {
        return boredom;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
