module com.ua.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.ua.app to javafx.fxml;
    exports com.ua.app;
}