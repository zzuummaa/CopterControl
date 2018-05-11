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
    private AnchorPane parent;

    @FXML
    private AnchorPane contentPane;

    private Parent camera;
    private Parent weather;

    private Controlable weatherController;
    private Controlable cameraController;

    private Controlable lastController;

    @FXML
    private Label lbExit;

    @FXML
    private Label lbCamers;

    @FXML
    private Label lbWeather;

    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Camera.fxml"));
            camera = loader.load();
            cameraController = loader.getController();

            loader = new FXMLLoader(getClass().getResource("Weather.fxml"));
            weather = loader.load();
            weatherController = loader.getController();

            weatherController.add();
            changeContext(weather);
            lastController = weatherController;
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                lastController.remove();
                weatherController.add();
                lastController = weatherController;
                changeContext(weather);
            }
        });

        lbCamers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lastController.remove();
                cameraController.add();
                lastController = cameraController;
                changeContext(camera);
            }
        });
    }

    private void changeContext(Parent context) {
        ObservableList<Node> child = contentPane.getChildren();
        child.remove(0, child.size());
        context.setDisable(false);
        child.addAll(context);
    }

    private void makeStageDrageable() {
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Launch.stage.setX(event.getScreenX() - xOffset);
                Launch.stage.setY(event.getScreenY() - yOffset);
                Launch.stage.setOpacity(0.7f);
            }
        });
        parent.setOnDragDone((e) -> {
            Launch.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((e) -> {
            Launch.stage.setOpacity(1.0f);
        });

    }
}
