module Proyecto2p {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens Proyecto2p to javafx.fxml;
    exports Proyecto2p;
}
