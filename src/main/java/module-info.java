module com.grupinix.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.logging;
    requires java.desktop;
    requires javafx.base;

    opens com.grupinix.client to javafx.fxml;
    exports com.grupinix.client;
    exports com.grupinix.client.controllers;
    opens com.grupinix.client.controllers to javafx.fxml;
}