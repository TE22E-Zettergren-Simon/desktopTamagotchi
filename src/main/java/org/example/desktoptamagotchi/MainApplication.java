package org.example.desktoptamagotchi;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        Util.createNewWindow("fxml/new-tamagotchi-view.fxml", "Tamagotchi Game", 320, 540);
    }

    public static void main(String[] args) {
        launch();
    }
}
