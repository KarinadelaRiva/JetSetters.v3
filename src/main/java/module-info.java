module org.jetsettersv2.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires javafaker;

    opens org.jetsettersv2 to javafx.fxml;
    exports org.jetsettersv2;
    opens org.jetsettersv2.models.abstracts to com.fasterxml.jackson.databind;
    opens org.jetsettersv2.models.concrete to com.fasterxml.jackson.databind;
    opens org.jetsettersv2.menus to com.fasterxml.jackson.databind;
    opens org.jetsettersv2.collections to com.fasterxml.jackson.databind;
    opens org.jetsettersv2.enums to com.fasterxml.jackson.databind;
    opens org.jetsettersv2.exceptions to com.fasterxml.jackson.databind;
    opens org.jetsettersv2.utilities to com.fasterxml.jackson.databind;
}
