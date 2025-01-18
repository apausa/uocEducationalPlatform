/**
 * Module of the program (based on Java 9's modules)
 */
open module edu.uoc.eduocation{
    requires  javafx.controls;
    requires  javafx.fxml;
    requires com.google.gson;
    exports edu.uoc.eduocation.view;
}