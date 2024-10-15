package org.example.desktoptamagotchi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.example.desktoptamagotchi.MainApplication;
import org.example.desktoptamagotchi.models.Tamagotchi;
import org.example.desktoptamagotchi.models.TamagotchisHolder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TamagotchisListController implements Initializable {
    @FXML
    private VBox list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Tamagotchi> tamagotchis = TamagotchisHolder.getInstance().getTamagotchis();

        for (Tamagotchi tamagotchi : tamagotchis) {
            Button button = new Button(tamagotchi.getName());
            button.setOnAction(this::onTamagotchiClicked);

            list.getChildren().add(button);
        }
    }

    private void onTamagotchiClicked(ActionEvent event) {
        String tamagotchiName = ((Button) event.getSource()).getText();
        TamagotchisHolder.getInstance().setCurrentTamagotchiName(tamagotchiName);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/tamagotchi-view.fxml"));
            list.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}
