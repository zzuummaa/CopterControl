import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class WeatherController implements Controlable {
    @FXML
    private Label lbWhether;

    @FXML
    private Label lbWind;

    @FXML
    private Label lbStatus;

    @FXML
    private AnchorPane apWeather;

    public void initialize() {
        System.out.println("Wether init");
        WeatherData weatherData = requestWhether();
        if (weatherData != null) {
            lbWhether.setText(String.valueOf(weatherData.tempC) + "°");
            lbWind.setText(String.valueOf(weatherData.windKPH) + "км/ч");
            lbStatus.setText(weatherData.windKPH > 36 ? "Взлет запрещен" : "Взлет разрешен");
        } else {
            lbStatus.setText("Нет связи с сервисом погоды");
        }
    }

    static class WeatherData {
        int tempC;
        int windKPH;
    }

    protected WeatherData requestWhether() {
        OkHttpClient client = new OkHttpClient();

        String url = "http://api.apixu.com/v1/current.json?key=caf69d49731b4e5f904234420181005&q=Moscow";
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        WeatherData data = new WeatherData();
        try {
            response = client.newCall(request).execute();
            String strResp = response.body().string();
            Map<String, Any> json = JsonIterator.deserialize(strResp).asMap();
            Map<String, Any> current = json.get("current").asMap();

            data.tempC = current.get("temp_c").toInt();
            data.windKPH = current.get("wind_kph").toInt();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return data;
    }

    @Override
    public void add() {

    }

    @Override
    public void remove() {

    }
}
