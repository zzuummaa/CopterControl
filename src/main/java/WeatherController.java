import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class WeatherController {
    @FXML
    private Label lbWhether;

    @FXML
    private Label lbWind;

    public void initialize() {
        System.out.println("Wether init");
        WhetherData whetherData = requestWhether();
        if (whetherData != null) {
            lbWhether.setText(String.valueOf(whetherData.tempC) + "°");
        }
    }

    static class WhetherData {
        int tempC;
        int windKPH;
    }

    protected WhetherData requestWhether() {
        OkHttpClient client = new OkHttpClient();

        String url = "http://api.apixu.com/v1/current.json?key=caf69d49731b4e5f904234420181005&q=Moscow";
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        WhetherData data = new WhetherData();
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
}
