package org.example.desktoptamagotchi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.example.desktoptamagotchi.Util;
import org.example.desktoptamagotchi.models.Tamagotchi;
import org.example.desktoptamagotchi.models.TamagotchisHolder;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TamagotchisListController implements Initializable {
    @FXML
    private VBox list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Tamagotchi> tamagotchis = TamagotchisHolder.getInstance().getTamagotchis();

        // Loops through all tamagotchis and creates a button for each one
        for (Tamagotchi tamagotchi : tamagotchis) {
            Button button = new Button(tamagotchi.getName());
            button.setOnAction(this::onTamagotchiClick);

            list.getChildren().add(button);
        }
    }


    // User actions

    private void onTamagotchiClick(ActionEvent event) {
        String tamagotchiName = ((Button) event.getSource()).getText();
        TamagotchisHolder.getInstance().setCurrentTamagotchiName(tamagotchiName);

        Util.switchView("fxml/tamagotchi-view.fxml", list.getScene());
    }


    @FXML
    private void onAddNewTamagotchiClick() {
        Util.switchView("fxml/new-tamagotchi-view.fxml", list.getScene());
    }
}
