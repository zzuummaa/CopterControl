import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.HashMap;
import java.util.Map;

public class CameraController {

    @FXML
    private ListView<String> lvCamers;

    @FXML
    private MediaView mediaView;

    private Map<String, String> camerasURLs;

    public void initialize() {
        System.out.println("Camera init");

        camerasURLs = new HashMap<>();
        camerasURLs.put("Oracle example", "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
        camerasURLs.put("Some strange source", "https://www.rmp-streaming.com/media/bbb-360p.mp4");

        ObservableList<String> items = FXCollections.observableArrayList (camerasURLs.keySet());
        lvCamers.setItems(items);

        lvCamers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("List view item selected: " + newValue);
                String streamURL = camerasURLs.get(newValue);
                MediaPlayer mediaPlayer = new MediaPlayer(new Media(streamURL));
                mediaPlayer.setAutoPlay(true);
                mediaView.setMediaPlayer(mediaPlayer);
            }
        });
    }
}
