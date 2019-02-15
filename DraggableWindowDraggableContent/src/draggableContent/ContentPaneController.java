package draggableContent;

import content.ContentItem;
import content.ContentProvider;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class ContentPaneController {

    private ContentProvider contentProvider;
    private ContentItem selectedItem = null;
    private double
            selectedItemPosX,
            selectedItemPosY,
            xOffset,
            yOffset;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void initialize(){
        addMouseListeners();
        this.anchorPane.widthProperty().addListener((obs, oldV, newV) -> resetContent(newV));
    }

    private void resetContent(Number newValue) {
        if(this.contentProvider != null)
            this.contentProvider.removeContent();
        this.contentProvider = new ContentProvider(this.anchorPane, newValue.doubleValue());
    }

    private void addMouseListeners() {
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> this.selectedItem = getSelectedItem(e));
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> this.selectedItem = null);
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            if (this.selectedItem == null) e.consume();
            else dragSelectedItem(e);
        });
    }

    private void dragSelectedItem(MouseEvent e) {
        this.selectedItem.setPosX(e.getScreenX() + xOffset);
        this.selectedItem.setPosY(e.getScreenY() + yOffset);
    }

    private ContentItem getSelectedItem(MouseEvent e) {
        ContentItem selectedItem = this.contentProvider.getItemAt(e.getX(), e.getY());
        if(selectedItem != null){
            this.selectedItemPosX = selectedItem.getPosX();
            this.selectedItemPosY = selectedItem.getPosY();
            this.xOffset = this.selectedItemPosX - e.getScreenX();
            this.yOffset = this.selectedItemPosY - e.getScreenY();
        }
        return selectedItem;
    }
}
