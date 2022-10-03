package Events;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField nameField;

    @FXML
    private Button helloButton;

    @FXML
    private Button byeButton;

    @FXML
    private CheckBox ourCheckBox;

    @FXML
    private Label ourLabel;

    @FXML
    public void initialize(){
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }

    public void onButtonClicked(ActionEvent e){
//        System.out.println("Hello, " + nameField.getText());
//        System.out.println("The following button was pressed: " + e.getSource());

        if(e.getSource().equals(helloButton)){
            System.out.println("Hello, " + nameField.getText());
        } else if (e.getSource().equals(byeButton)) {
            System.out.println("Bye, " + nameField.getText());
        }

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    String s = Platform.isFxApplicationThread() ? "UI thread" : "Background thread";
                    System.out.println("I'm going to sleep on the: " + s);

                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String s = Platform.isFxApplicationThread() ? "UI thread" : "Background thread";
                            System.out.println("I'm updating the label on: " + s);
                            ourLabel.setText("We did something!");
                        }
                    });
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };

        new Thread(task).start();

    }

    @FXML
    public void handleKeyReleased(){
        String text = nameField.getText();
        boolean disabledButtons = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisable(disabledButtons);
        byeButton.setDisable(disabledButtons);
    }

    @FXML
    public void handleChange(){
        System.out.println("Checkbox is " + (ourCheckBox.isSelected() ? "checked" : "not checked"));
    }

}
