package internalWindow;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InternalWindowController {

    private Stage stage;
    private boolean collapsed = false;
    private double
            heightBeforeCollapse,
            xOffset,
            yOffset,
            translateX,
            translateY;

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
        menuBarPane.setOnMousePressed(e -> setMouseCoordinates(e));
        menuBarPane.setOnMouseDragged(e -> dragWindow(e));
    }

    private void setMouseCoordinates(MouseEvent e) {
        setRoot();
        this.xOffset = e.getScreenX() - this.translateX;
        this.yOffset = e.getScreenY() - this.translateY;
    }

    private void dragWindow(MouseEvent e) {
        this.translateX = e.getScreenX() - this.xOffset;
        this.translateY = e.getScreenY() - this.yOffset;
        this.mainPane.setTranslateX(this.translateX);
        this.mainPane.setTranslateY(this.translateY);
    }

    @FXML
    private void close(){
        this.mainPane.setDisable(true);
        this.mainPane.setVisible(false);
    }

    @FXML
    private void hide(){
        setRoot();
        this.stage.setIconified(true);
    }

    @FXML
    private void collapse(){
        if(!this.collapsed) {
            this.heightBeforeCollapse = this.mainPane.getHeight();
            this.mainPane.resize(this.mainPane.getWidth(), 30);
            this.collapsed = true;
        }
        else {
            this.mainPane.resize(this.mainPane.getWidth(), this.heightBeforeCollapse);
            this.collapsed = false;
        }
    }

    public void setRoot() {
        if(this.stage == null)
            this.stage = (Stage)this.mainPane.getScene().getWindow();
    }
}
