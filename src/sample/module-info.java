module HelloWorldFX {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.xml;

    opens sample;
    opens Events;
    opens TodoList;
    opens css;
    opens SceneBuilder;
    opens Challenge;
    opens Challenge.datamodel;
}