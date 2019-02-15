package draggableContent;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class DraggableContentPane extends ScrollPane {

    public DraggableContentPane() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("content.fxml"));
        this.getStylesheets().add("/draggableContent/contentStyle.css");
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e){
            System.err.println("Couldn't load the content...");
        }
    }
}
