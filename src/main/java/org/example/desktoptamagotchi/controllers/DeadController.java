package org.example.desktoptamagotchi.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.desktoptamagotchi.MainApplication;
import org.example.desktoptamagotchi.models.TamagotchisHolder;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class DeadController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private ImageView imageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get and set name of Tamagotchi
        String name = TamagotchisHolder.getInstance().getCurrentTamagotchi().getName();
        label.setText(name + " has died");

        // Get and set image of dead Tamagotchi
        String imagePath = "images/tamagotchi_dead.png";
        InputStream inputStream = MainApplication.class.getResourceAsStream(imagePath);
        Image image = new Image(inputStream);
        imageView.setImage(image);
    }
}
