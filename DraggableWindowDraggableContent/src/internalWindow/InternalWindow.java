package internalWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InternalWindow extends AnchorPane {
    public InternalWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("internalWindow.fxml"));
        this.getStylesheets().add("/internalWindow/internalWindowStyle.css");
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e){
            System.err.println("Couldn't load the content...");
        }
    }
}
