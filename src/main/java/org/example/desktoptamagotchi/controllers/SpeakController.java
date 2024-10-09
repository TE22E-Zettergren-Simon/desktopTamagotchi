package org.example.desktoptamagotchi.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.desktoptamagotchi.models.Tamagotchi;
import org.example.desktoptamagotchi.models.TamagotchiHolder;

import java.net.URL;
import java.util.ResourceBundle;

public class SpeakController implements Initializable {
    @FXML
    private Label label;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tamagotchi tamagotchi = TamagotchiHolder.getInstance().getTamagotchi();
        label.setText(tamagotchi.getName() + " says \"" + tamagotchi.speak() + "\"");
    }


    // User actions

    @FXML
    private void onCloseButtonClick() {
        ((Stage) label.getScene().getWindow()).close(); // Closes the alert window
    }
}
