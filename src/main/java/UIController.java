import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Observable;

public class UIController {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane contentPane;
    private Parent camera;
    private Parent weather;

    private Parent state;

    @FXML
    private Label lbExit;

    @FXML
    private Label lbCamers;

    @FXML
    private Label lbWeather;

    public void initialize() {

        changeContext("Weather.fxml");
        //contentPane.getChildren().addAll(weather.getChildrenUnmodifiable());
        makeStageDrageable();

        lbExit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });

        lbWeather.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                changeContext("Weather.fxml");
            }
        });

        lbCamers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                changeContext("Camera.fxml");
            }
        });
    }

    private void changeContext(String resource) {
        try {
            Parent context = FXMLLoader.load(getClass().getResource(resource));

            ObservableList<Node> child = contentPane.getChildren();
            child.remove(0, child.size());
            contentPane.getChildren().addAll(context.getChildrenUnmodifiable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeStageDrageable() {
        contentPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        contentPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Launch.stage.setX(event.getScreenX() - xOffset);
                Launch.stage.setY(event.getScreenY() - yOffset);
                Launch.stage.setOpacity(0.7f);
            }
        });
        contentPane.setOnDragDone((e) -> {
            Launch.stage.setOpacity(1.0f);
        });
        contentPane.setOnMouseReleased((e) -> {
            Launch.stage.setOpacity(1.0f);
        });

    }
}
