package draggableContent;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ContentPaneController {

    private boolean mousePressed = false;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void initialize(){
        addListeners();
        new ContentProvider(this.anchorPane);
    }

    private void addListeners() {
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> this.mousePressed = true);
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> this.mousePressed = false);
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            if (!this.mousePressed) e.consume();
            else getInfo(e);
        });
    }

    private void getInfo(MouseEvent e) {
        double posX = e.getX();
        double posY = e.getY();

        System.out.println("Cords: " + posX + " " + posY);
        System.out.println();

    }
}
