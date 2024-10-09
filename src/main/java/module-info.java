module org.example.desktoptamagotchi {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.desktoptamagotchi to javafx.fxml;
    exports org.example.desktoptamagotchi;
    exports org.example.desktoptamagotchi.controllers;
    opens org.example.desktoptamagotchi.controllers to javafx.fxml;
    exports org.example.desktoptamagotchi.models;
    opens org.example.desktoptamagotchi.models to javafx.fxml;
}