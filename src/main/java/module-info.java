module org.example.desktoptamagotchi {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.desktoptamagotchi to javafx.fxml;
    exports org.example.desktoptamagotchi;
}