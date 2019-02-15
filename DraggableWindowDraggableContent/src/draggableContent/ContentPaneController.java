package draggableContent;

import content.ContentProvider;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class ContentPaneController {

    private boolean mousePressed = false;
    private ContentProvider contentProvider;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void initialize(){
        addMouseListeners();
        addPropertiesListeners();
    }

    private void addMouseListeners() {
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> this.mousePressed = true);
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> this.mousePressed = false);
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            if (!this.mousePressed) e.consume();
            else getInfo(e);
        });
    }

    public void addPropertiesListeners(){
        this.anchorPane.widthProperty().addListener((obs, oldV, newV) -> resetContent());
    }

    private void resetContent() {
        if(this.contentProvider != null)
            this.contentProvider.removeContent();
        this.contentProvider = new ContentProvider(this.anchorPane);
    }

    private void getInfo(MouseEvent e) {
        double posX = e.getX();
        double posY = e.getY();

        System.out.println("Cords: " + posX + " " + posY);
        System.out.println();

    }
}
