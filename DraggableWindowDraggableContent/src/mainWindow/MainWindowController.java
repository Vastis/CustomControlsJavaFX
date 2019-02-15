package mainWindow;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainWindowController {

    private Stage root;
    private boolean collapsed = false;
    private double
            heightBeforeCollapse,
            xOffset,
            yOffset;

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
        this.xOffset = this.root.getX() - e.getScreenX();
        this.yOffset = this.root.getY() - e.getScreenY();
    }

    private void dragWindow(MouseEvent e) {
        this.root.setX(e.getScreenX() + this.xOffset);
        this.root.setY(e.getScreenY() + this.yOffset);
    }

    @FXML
    private void close(){
        Platform.exit();
    }

    @FXML
    private void hide(){
        setRoot();
        this.root.setIconified(true);
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
        if(this.root == null)
            this.root = (Stage)this.mainPane.getScene().getWindow();
    }
}
