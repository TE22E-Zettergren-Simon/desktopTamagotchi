package org.example.desktoptamagotchi;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TamagotchiController implements Initializable {
    @FXML
    private Label nameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get the name from the singleton
        nameLabel.setText(NameHolder.getInstance().getName());
    }
}
