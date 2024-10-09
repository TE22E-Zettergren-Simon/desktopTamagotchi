package org.example.desktoptamagotchi.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import org.example.desktoptamagotchi.MainApplication;
import org.example.desktoptamagotchi.models.Tamagotchi;
import org.example.desktoptamagotchi.models.TamagotchiHolder;

import java.io.IOException;

public class IndexController {
    @FXML
    private TextField textField;


    // User actions

    @FXML
    private void onSubmit() {
        String name = textField.getText();
        if (name.isEmpty()) {
            return;
        }

        // Set the singleton
        TamagotchiHolder.getInstance().setTamagotchi(new Tamagotchi(name));

        // Switch scene
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/tamagotchi-view.fxml"));
            textField.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
