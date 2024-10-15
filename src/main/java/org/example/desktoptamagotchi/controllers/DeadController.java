package org.example.desktoptamagotchi.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.desktoptamagotchi.MainApplication;
import org.example.desktoptamagotchi.models.TamagotchisHolder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class DeadController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private ImageView imageView;
    @FXML
    private Button button;

    private String name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get and set name of Tamagotchi
        name = TamagotchisHolder.getInstance().getCurrentTamagotchi().getName();
        label.setText(name + " has died");
        button.setText("Leave " + name);

        // Get and set image of dead Tamagotchi
        String imagePath = "images/tamagotchi_dead.png";
        InputStream inputStream = MainApplication.class.getResourceAsStream(imagePath);
        Image image = new Image(inputStream);
        imageView.setImage(image);
    }

    @FXML
    private void onRemoveTamagotchiClick() {
        TamagotchisHolder.getInstance().removeTamagotchi(name);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/tamagotchis-list-view.fxml"));
            label.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
