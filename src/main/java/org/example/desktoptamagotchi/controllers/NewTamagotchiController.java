package org.example.desktoptamagotchi.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.desktoptamagotchi.Util;
import org.example.desktoptamagotchi.models.Tamagotchi;
import org.example.desktoptamagotchi.models.TamagotchisHolder;

import java.net.URL;
import java.util.ResourceBundle;

public class NewTamagotchiController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private TextField textField;

    private static boolean isFirstTime = true;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isFirstTime) {
            label.setText("Name your first tamagotchi!");
            isFirstTime = false;
        } else {
            label.setText("Name your new tamagotchi!");
        }
    }


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

        Util.switchView("fxml/tamagotchi-view.fxml", textField.getScene());
    }
}
