package org.example.desktoptamagotchi.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import org.example.desktoptamagotchi.MainApplication;
import org.example.desktoptamagotchi.models.Tamagotchi;
import org.example.desktoptamagotchi.models.TamagotchisHolder;

import java.io.IOException;

public class NewTamagotchiController {
    @FXML
    private TextField textField;


    // User actions

    @FXML
    private void onSubmit() {
        String name = textField.getText();
        if (name.isEmpty()) {
            return;
        }

        try {
            // Set the singleton
            TamagotchisHolder.getInstance().addTamagotchi(new Tamagotchi(name));
        } catch (IllegalArgumentException e) {
            textField.setText("That name is taken");
            textField.requestFocus();
            textField.selectAll();
            return;
        }

        // Switch scene
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/tamagotchi-view.fxml"));
            textField.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
