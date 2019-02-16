package internalWindow;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InternalWindowController {

    private Stage stage;
    private boolean
            collapsed = false,
            minimized = false;
    private double
            heightBeforeCollapse,
            xOffset,
            yOffset,
            beforeMinimizingTranslateX,
            beforeMinimizingTranslateY;

    @FXML
    private AnchorPane
            mainPane,
            menuBarPane,
            contentPane;
    @FXML
    private Button
            closeButton,
            hideButton,
            collapseButton;

    @FXML
    private void initialize(){
        this.menuBarPane.setOnMousePressed(e -> setMouseCoordinates(e));
        this.menuBarPane.setOnMouseDragged(e -> dragWindow(e));
    }

    private void setMouseCoordinates(MouseEvent e) {
        if(this.stage == null)
            this.stage = (Stage)this.mainPane.getScene().getWindow();
        this.xOffset = e.getScreenX() - this.mainPane.getTranslateX();
        this.yOffset = e.getScreenY() - this.mainPane.getTranslateY();
    }

    private void dragWindow(MouseEvent e) {
        if(!this.minimized) {
            this.mainPane.setTranslateX(e.getScreenX() - this.xOffset);
            this.mainPane.setTranslateY(e.getScreenY() - this.yOffset);
        }
    }

    @FXML
    private void close(){
        this.mainPane.setDisable(true);
        this.mainPane.setVisible(false);
    }

    @FXML
    private void hide(){
        if(!collapsed) {
            if (!this.minimized) {
                this.heightBeforeCollapse = this.mainPane.getHeight();
                this.mainPane.setPrefHeight(30);
                this.beforeMinimizingTranslateX = this.mainPane.getTranslateX();
                this.beforeMinimizingTranslateY = this.mainPane.getTranslateY();
                this.mainPane.setTranslateX(0);
                this.mainPane.setTranslateY(0);
                this.collapseButton.setDisable(true);
            } else {
                this.mainPane.setPrefHeight(this.heightBeforeCollapse);
                this.mainPane.setTranslateX(this.beforeMinimizingTranslateX);
                this.mainPane.setTranslateY(this.beforeMinimizingTranslateY);
                this.collapseButton.setDisable(false);
            }
            this.minimized = !this.minimized;
        }
    }

    @FXML
    private void collapse(){
        if(!this.minimized) {
            if (!this.collapsed) {
                this.heightBeforeCollapse = this.mainPane.getHeight();
                this.mainPane.setPrefHeight(30);
                this.hideButton.setDisable(true);
            } else {
                this.mainPane.setPrefHeight(this.heightBeforeCollapse);
                this.hideButton.setDisable(false);
            }
            this.collapsed = !this.collapsed;
        }
    }
}
