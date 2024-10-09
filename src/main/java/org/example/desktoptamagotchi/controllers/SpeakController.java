package org.example.desktoptamagotchi.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
}
