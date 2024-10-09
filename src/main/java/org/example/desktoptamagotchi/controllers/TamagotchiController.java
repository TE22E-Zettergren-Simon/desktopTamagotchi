package org.example.desktoptamagotchi.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.desktoptamagotchi.MainApplication;
import org.example.desktoptamagotchi.models.TamagotchiHolder;
import org.example.desktoptamagotchi.models.Tamagotchi;

import java.io.IOException;
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
        tamagotchi = TamagotchiHolder.getInstance().getTamagotchi();

        nameLabel.setText(tamagotchi.getName());
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
        // Create a new window and open it
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/speak-alert.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 200, 80);

            Stage stage = new Stage();
            stage.setTitle("Speak");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
