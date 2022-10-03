package SceneBuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.IOException;

public class Controller {

    @FXML
    private Label label;

    @FXML
    public void handleAction(){
        label.setText("OK Button pressed");
    }

}
