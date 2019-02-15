package draggableContent;

import content.ContentProvider;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleDoubleProperty;
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
        bindProperties();
    }

    private void addMouseListeners() {
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> this.mousePressed = true);
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> this.mousePressed = false);
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            if (!this.mousePressed) e.consume();
            else getInfo(e);
        });
    }

    public void bindProperties(){
        this.anchorPane.widthProperty().addListener((obs, oldV, newV) -> resetContent(newV));
    }

    private void resetContent(Number newValue) {
        if(this.contentProvider != null)
            this.contentProvider.removeContent();
        this.contentProvider = new ContentProvider(this.anchorPane, newValue.doubleValue());
    }

    private void getInfo(MouseEvent e) {
        double posX = e.getX();
        double posY = e.getY();

        System.out.println("Cords: " + posX + " " + posY);
        System.out.println();

    }
}
