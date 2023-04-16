import dto.MeasurementDTO;
import dto.MeasurementsResponse;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ClientGet {
    protected static List<Double> getTemperaturesFromServer() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measurements";

        MeasurementsResponse jsonResponse = restTemplate.getForObject(url, MeasurementsResponse.class);

        if(jsonResponse == null || jsonResponse.getMeasurements() == null) {
            return Collections.emptyList();
        }

        return jsonResponse.getMeasurements().stream().map(MeasurementDTO::getValue)
                .collect(Collectors.toList());
    }

    protected static String getSensorsFromServer() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/sensors";

        String jsonResponse = restTemplate.getForObject(url, String.class);

        return jsonResponse;
    }

    protected static Integer getRainyDaysCountFromServer() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measurements/rainyDaysCount";

        Integer jsonResponse = restTemplate.getForObject(url, Integer.class);
        return jsonResponse;
    }
}


















