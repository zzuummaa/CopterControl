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

public class CameraController implements Controlable {

    @FXML
    private ListView<String> lvCamers;

    @FXML
    private MediaView mediaView;

    private Map<String, String> camerasURLs;

    private Map<String, MediaPlayer> mediaPlayers;

    public void initialize() {
        System.out.println("Camera init");

        mediaPlayers = new HashMap<>();

        camerasURLs = new HashMap<>();
        camerasURLs.put("Oracle example", "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
        camerasURLs.put("Some strange source", "https://www.rmp-streaming.com/media/bbb-360p.mp4");

        camerasURLs.put("Oracle example1", "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
        camerasURLs.put("Some strange source1", "https://www.rmp-streaming.com/media/bbb-360p.mp4");
        camerasURLs.put("Oracle example2", "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
        camerasURLs.put("Some strange source2", "https://www.rmp-streaming.com/media/bbb-360p.mp4");
        camerasURLs.put("Oracle example3", "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
        camerasURLs.put("Some strange source3", "https://www.rmp-streaming.com/media/bbb-360p.mp4");
        camerasURLs.put("Oracle example4", "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
        camerasURLs.put("Some strange source4", "https://www.rmp-streaming.com/media/bbb-360p.mp4");
        camerasURLs.put("Oracle example5", "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
        camerasURLs.put("Some strange source5", "https://www.rmp-streaming.com/media/bbb-360p.mp4");
        camerasURLs.put("Oracle example6", "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
        camerasURLs.put("Some strange source6", "https://www.rmp-streaming.com/media/bbb-360p.mp4");

        ObservableList<String> items = FXCollections.observableArrayList (camerasURLs.keySet());
        lvCamers.setItems(items);

        lvCamers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("List view item selected: " + newValue);

                MediaPlayer mediaPlayer;
                if (mediaPlayers.containsKey(newValue)) {
                    mediaPlayer = mediaPlayers.get(newValue);
                } else {
                    String streamURL = camerasURLs.get(newValue);
                    mediaPlayer = new MediaPlayer(new Media(streamURL));
                    mediaPlayers.put(newValue, mediaPlayer);
                }

                mediaPlayer.play();
                mediaView.getMediaPlayer().stop();
                mediaView.setMediaPlayer(mediaPlayer);
            }
        });
    }

    @Override
    public void add() {

    }

    @Override
    public void remove() {
        mediaView.getMediaPlayer().stop();
    }
}
