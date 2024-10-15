package org.example.desktoptamagotchi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Util {

    // Tries to switch the shown scene to the provided path
    // Throws a RuntimeException if it fails
    public static void switchView(String fxmlPath, Scene scene) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlPath));
            scene.setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createNewWindow(String fxmlPath, String title, int width, int height) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlPath));
            Scene scene = new Scene(fxmlLoader.load(), width, height);

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
