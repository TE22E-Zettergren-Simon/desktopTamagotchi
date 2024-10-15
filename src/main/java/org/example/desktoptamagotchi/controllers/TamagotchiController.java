package org.example.desktoptamagotchi.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.desktoptamagotchi.MainApplication;
import org.example.desktoptamagotchi.Util;
import org.example.desktoptamagotchi.models.TamagotchisHolder;
import org.example.desktoptamagotchi.models.Tamagotchi;
import org.example.desktoptamagotchi.models.TamagotchiState;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class TamagotchiController implements Initializable, Runnable {
    @FXML
    private Label nameLabel;
    @FXML
    private Label hungerLabel;
    @FXML
    private Label boredomLabel;
    @FXML
    private ImageView imageView;

    Tamagotchi tamagotchi;
    Thread updateThread;


    // Almost a constructor
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get the tamagotchi from the singleton
        tamagotchi = TamagotchisHolder.getInstance().getCurrentTamagotchi();

        nameLabel.setText(tamagotchi.getName());
        updateLabels();

        updateThread = new Thread(this);
        updateThread.start();
    }


    @Override
    public void run() {
        while (tamagotchi.getState() != TamagotchiState.DEAD) {
            // Wait some time before updating
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }

            // Update
            Platform.runLater(this::updateLabels);

            // Platform.runLater schedules a method to run later on the application thread
            // This is needed as JavaFX doesn't allow other threads to update scenes
            // this::updateLabels is a method reference
        }

        switchToDeadScene();
    }


    // User actions

    @FXML
    private void onFeedButtonClick() {
        tamagotchi.feed();

        updateLabels();
    }

    @FXML
    private void onSpeakButtonClick() {
        Util.createNewWindow("fxml/speak-alert.fxml", "Speak", 280, 120);

        updateLabels();
    }

    @FXML
    private void onTeachButtonClick() {
        Util.createNewWindow("fxml/teach-alert.fxml", "Teach", 280, 120);

        updateLabels();
    }

    @FXML
    private void onToTamagotchiListClick() {
        close();
        Util.switchView("fxml/tamagotchis-list-view.fxml", nameLabel.getScene());
    }


    // Internal helpers

    private void updateLabels() {
        hungerLabel.setText("Hunger: " + tamagotchi.getHunger());
        boredomLabel.setText("Boredom: " + tamagotchi.getBoredom());

        // Get and set the appropriate image
        String imagePath = "images/tamagotchi_" + tamagotchi.getState().toString() + ".png";
        InputStream inputStream = MainApplication.class.getResourceAsStream(imagePath);
        Image image = new Image(inputStream);
        imageView.setImage(image);
    }

    private void switchToDeadScene() {
        close();
        Util.switchView("fxml/dead-view.fxml", nameLabel.getScene());
    }

    // Stops the updating
    public void close() {
        updateThread.interrupt();
    }
}
