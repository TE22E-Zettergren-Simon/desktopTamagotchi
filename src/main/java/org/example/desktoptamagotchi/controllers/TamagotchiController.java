package org.example.desktoptamagotchi.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.example.desktoptamagotchi.models.NameHolder;
import org.example.desktoptamagotchi.models.Tamagotchi;

import java.net.URL;
import java.util.ResourceBundle;

public class TamagotchiController implements Initializable, Runnable {
    @FXML
    private Label nameLabel;
    @FXML
    private Label hungerLabel;
    @FXML
    private Label boredomLabel;

    Tamagotchi tamagotchi;

    // Basically a constructor
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get the name from the singleton
        String name = NameHolder.getInstance().getName();

        tamagotchi = new Tamagotchi(name);

        nameLabel.setText(name);
        updateLabels();

        new Thread(this).start();
    }


    @Override
    public void run() {
        while (tamagotchi.isAlive()) {
            // Wait some time before updating
            try {
                //FIXME: Ticks faster for testing purposes
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                break;
            }

            // Update
            tamagotchi.tick();
            Platform.runLater(this::updateLabels);

            // Platform.runLater schedules updateLabels to run later on the application thread
            // this::updateLabels is a method reference
        }

        System.out.println("Tamagotchi stopped");
    }


    // User actions

    @FXML
    private void onFeedButtonClick() {
        tamagotchi.feed();

        updateLabels();
    }

    @FXML
    private void onSpeakButtonClick() {
        //FIXME: Temporary
        System.out.println(tamagotchi.speak());

        updateLabels();
    }

    @FXML
    private void onTeachButtonClick() {
        //FIXME: Temporary
        tamagotchi.teachPhrase("Hello!");

        updateLabels();
    }


    // Internal helpers

    private void updateLabels() {
        hungerLabel.setText("Hunger: " + tamagotchi.getHunger());
        boredomLabel.setText("Boredom: " + tamagotchi.getBoredom());
    }
}
