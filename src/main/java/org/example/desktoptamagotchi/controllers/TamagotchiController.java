package org.example.desktoptamagotchi.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.desktoptamagotchi.MainApplication;
import org.example.desktoptamagotchi.models.TamagotchisHolder;
import org.example.desktoptamagotchi.models.Tamagotchi;
import org.example.desktoptamagotchi.models.TamagotchiState;

import java.io.IOException;
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


    // Called when a fxml tag connects to this controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get the tamagotchi from the singleton
        tamagotchi = TamagotchisHolder.getInstance().getCurrentTamagotchi();

        nameLabel.setText(tamagotchi.getName());
        updateLabels();

        new Thread(this).start();
    }


    @Override
    public void run() {
        while (tamagotchi.getState() != TamagotchiState.DEAD) {
            // Wait some time before updating
            try {
                //FIXME: Ticks faster for testing purposes
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                break;
            }

            // Update
            tamagotchi.tick();
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
        // Create a new window and open it
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/speak-alert.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 120);

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
        // Create a new window and open it
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/teach-alert.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 120);

            Stage stage = new Stage();
            stage.setTitle("Teach");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        updateLabels();
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/dead-view.fxml"));
            nameLabel.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
