package content;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class ContentProvider {

    private static final int
            maxCols = 4,
            contentItems = 17,
            delta = 5;

    private AnchorPane anchorPane;

    private int rows;
    private double xOffset, yOffset, itemWidth, itemHeight, currentWindowHeight;

    private ArrayList<ContentItem> items;

    public ContentProvider(AnchorPane anchorPane, double currentWindowHeight){
        this.anchorPane = anchorPane;
        this.currentWindowHeight = currentWindowHeight;
        computeAssets();
        initContent();
    }

    private void computeAssets() {
        //assumption : contentItems are squares.
        this.rows = 1 + (contentItems - 1) / maxCols;
        this.xOffset = (this.anchorPane.getWidth() - 18) / maxCols;
        this.itemWidth = this.xOffset - 2 * delta;
        this.itemHeight = this.itemWidth;
        this.yOffset = this.xOffset;

        if(this.currentWindowHeight < this.rows * this.yOffset)
            this.currentWindowHeight = this.rows * this.yOffset;
        this.anchorPane.setPrefHeight(this.currentWindowHeight);
    }

    private void initContent() {
        this.items = new ArrayList<>();
        int counter = 0;
        for(int row = 0; row < this.rows; row++){
            for(int col = 0; col < maxCols; col++){
                if(counter < contentItems)
                    addItem(new ContentItem(this.xOffset * col + delta, this.yOffset * row + delta, this.itemWidth, this.itemHeight), row, col);
                counter++;
            }
        }
    }

    private void addItem(ContentItem item, int row, int col){
        this.items.add(item);
        item.addItem(this.anchorPane);
    }

    public void removeContent(){
        for(ContentItem item : this.items)
            this.anchorPane.getChildren().remove(item.getRectangle());
    }

    public ContentItem getItemAt(double posX, double posY) {
        for(ContentItem item : this.items){
            if(item.isPointingAtItem(posX, posY))
                return item;
        }
        return null;
    }
}
