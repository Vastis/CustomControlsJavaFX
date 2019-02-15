package content;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ContentItem {

    private Rectangle rectangle;

    public ContentItem(double posX, double posY, double width, double height){
        this.rectangle = new Rectangle(posX, posY, width, height);
        this.rectangle.setFill(Color.RED);
    }

    public void addItem(AnchorPane anchorPane){
        anchorPane.getChildren().add(this.rectangle);
    }

    public double getPosX() {
        return this.rectangle.getX();
    }
    public double getPosY() {
        return this.rectangle.getY();
    }
    public double getWidth() {
        return this.rectangle.getWidth();
    }
    public double getHeight() {
        return this.rectangle.getHeight();
    }
    public void setPosX(double posX) {
        this.rectangle.setX(posX);
    }
    public void setPosY(double posY) {
        this.rectangle.setY(posY);
    }
    public void setWidth(double width) {
        this.rectangle.setWidth(width);
    }
    public void setHeight(double height) {
        this.rectangle.setHeight(height);
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
}
