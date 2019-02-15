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
            swapPosX,
            swapPosY,
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
            this.contentProvider.resetContent(newValue.doubleValue());
        else
            this.contentProvider = new ContentProvider(this.anchorPane, newValue.doubleValue());
    }

    private void addMouseListeners() {
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> this.selectedItem = getHoveredItem(e));
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> deselectItem(e));
        this.anchorPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            if (this.selectedItem == null) e.consume();
            else dragSelectedItem(e);
        });
    }

    private void dragSelectedItem(MouseEvent e) {
        if(mouseWithinBounds(e)) {
            this.selectedItem.setPosX(e.getScreenX() + xOffset);
            this.selectedItem.setPosY(e.getScreenY() + yOffset);
            swap(e);
        }
    }

    private boolean mouseWithinBounds(MouseEvent e) {
        return
                e.getX() < this.anchorPane.getWidth()
                && e.getX() >= 0
                && e.getY() >= 0
                && e.getY() < this.anchorPane.getHeight();
    }

    private void swap(MouseEvent e) {
        ContentItem itemHovered = this.contentProvider.getItemAt(e.getX(), e.getY(), this.selectedItem);
        if(itemHovered != null){
            double tmpX = itemHovered.getPosX();
            double tmpY = itemHovered.getPosY();
            itemHovered.setPosX(this.swapPosX);
            itemHovered.setPosY(this.swapPosY);
            this.swapPosX = tmpX;
            this.swapPosY = tmpY;
        }
    }

    private void deselectItem(MouseEvent e) {
        if(this.selectedItem != null) {
            this.selectedItem.setPosX(this.swapPosX);
            this.selectedItem.setPosY(this.swapPosY);
            this.selectedItem = null;
        }
    }

    private ContentItem getHoveredItem(MouseEvent e) {
        ContentItem selectedItem = this.contentProvider.getItemAt(e.getX(), e.getY());
        if(selectedItem != null){
            this.swapPosX = selectedItem.getPosX();
            this.swapPosY = selectedItem.getPosY();
            this.xOffset = this.swapPosX - e.getScreenX();
            this.yOffset = this.swapPosY - e.getScreenY();
        }
        return selectedItem;
    }
}
