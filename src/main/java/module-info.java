module com.example.testsample {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.example.menu to javafx.fxml;
    exports com.example.menu;
}