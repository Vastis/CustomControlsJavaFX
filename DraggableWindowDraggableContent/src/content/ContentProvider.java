package content;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class ContentProvider {

    private static final int
            maxCols = 4,
            contentItems = 39,
            delta = 3;

    private AnchorPane anchorPane;

    private int rows;
    private double xOffset, yOffset, itemWidth, itemHeight;

    private ArrayList<ContentItem> items;

    public ContentProvider(AnchorPane anchorPane){
        this.anchorPane = anchorPane;
        computeAssets();
        initContent();
    }

    private void computeAssets() {
        //assumption : contentItems are squares.
        this.rows = 1 + (contentItems - 1) / maxCols;
        this.xOffset = this.anchorPane.getWidth() / maxCols;
        this.itemWidth = this.xOffset - 2 * delta;
        this.itemHeight = this.itemWidth;
        this.yOffset = this.xOffset;
        this.anchorPane.setPrefHeight(this.rows * this.yOffset);
    }

    private void initContent() {
        this.items = new ArrayList<>();
        for(int row = 0; row < this.rows; row++){
            for(int col = 0; col < maxCols; col++){
                addItem(new ContentItem(this.xOffset * col + delta, this.yOffset * row + delta, this.itemWidth, this.itemHeight), row, col);
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
}
