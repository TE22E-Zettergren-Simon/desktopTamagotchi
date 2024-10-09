package org.example.desktoptamagotchi.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import org.example.desktoptamagotchi.MainApplication;
import org.example.desktoptamagotchi.models.NameHolder;

import java.io.IOException;

public class IndexController {
    @FXML
    private TextField textField;


    // User actions

    @FXML
    private void onTextFieldEnter() {
        handleSubmit();
    }

    @FXML
    private void onSubmitButtonClick() {
        handleSubmit();
    }


    // Internal helpers

    private void handleSubmit() {
        if (textField.getText().isEmpty()) {
            return;
        }

        // Set the singleton
        NameHolder.getInstance().setName(textField.getText());

        // Switch scene
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/tamagotchi-view.fxml"));
            textField.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
