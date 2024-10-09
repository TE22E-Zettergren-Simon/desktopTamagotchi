package org.example.desktoptamagotchi.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.desktoptamagotchi.models.Tamagotchi;
import org.example.desktoptamagotchi.models.TamagotchiHolder;

import java.net.URL;
import java.util.ResourceBundle;

public class TeachController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private TextField textField;

    Tamagotchi tamagotchi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tamagotchi = TamagotchiHolder.getInstance().getTamagotchi();

        label.setText("What will you teach " + tamagotchi.getName() + "?");
    }

    @FXML
    private void onSubmit() {
        String phrase = textField.getText();
        if (phrase.isEmpty()) return;

        tamagotchi.teachPhrase(phrase);
        ((Stage) label.getScene().getWindow()).close(); // Closes the alert window
    }
}
