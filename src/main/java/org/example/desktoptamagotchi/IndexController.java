package org.example.desktoptamagotchi;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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


    // Helpers

    private void handleSubmit() {
        System.out.println(textField.getText());
    }
}
