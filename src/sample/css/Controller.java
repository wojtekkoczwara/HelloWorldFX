package css;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Controller {

    @FXML
    private Label label;

    @FXML
    private Button button4;

    @FXML
    private GridPane gridPane;

//    @FXML
//    private WebView webView;

    public void initialize(){
        button4.setEffect(new DropShadow());
    }

    @FXML
    public void handleMouseEnter(){
        label.setScaleX(2.0);
        label.setScaleY(2.0);
    }

    @FXML
    public void handleMouseExit(){
        label.setScaleX(1.0);
        label.setScaleY(1.0);
    }

    @FXML
    public void handleClick(){
        FileChooser chooser = new FileChooser();
//        DirectoryChooser directoryChooser = new DirectoryChooser();
//        chooser.showOpenDialog(gridPane.getScene().getWindow());
//        directoryChooser.showDialog(gridPane.getScene().getWindow());
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Images Files", "*.jpg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("All files", "*.*"));
        chooser.setTitle("Save app file");
        chooser.showOpenMultipleDialog(gridPane.getScene().getWindow());

//        File file = chooser.showSaveDialog(gridPane.getScene().getWindow());

//        File file2 = chooser.showOpenDialog(gridPane.getScene().getWindow());
//        if (file != null) {
//            System.out.println(file.getPath());
//        } else {
//            System.out.println("chooser was cancelled");
//        }
//        int a = 5;

    }

    @FXML
    public void handleLinkClick() throws IOException {
//        System.out.println("Link was clicked");
//        try{
//            Desktop.getDesktop().browser(new URI("http://www.javafx.com"));
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        } catch (IOException ex){
//            throw new IOException(ex);
//        }
//        BrowserOpener.open("http://google.com");
//        WebEngine engine = webView.getEngine();
//        Engine.load("http://www.javafx.com");
    }

}
