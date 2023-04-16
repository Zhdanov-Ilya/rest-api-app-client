import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ClientPost {
	protected static void registerSensor() {
		final String url = "http://localhost:8080/sensors/registration";

		System.out.println("Enter sensor name: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String sensorName = "";

		try {
			sensorName = reader.readLine();
		} catch (IOException e) {
			System.out.println("Incorrect sensor name");
		}

		Map<String, Object> jsonData = new HashMap<>();
		jsonData.put("name", sensorName);

		makePostRequestWithJSONData(url, jsonData);
	}

	protected static void sendMeasurement() {
		final String url = "http://localhost:8080/measurements/add";

		System.out.println("Enter sensor name: ");

		BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
		String sensorName = "";

		try {
			sensorName = reader1.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		Random random = new Random();
		double maxTemperature = 45.0;

		for (int i = 0; i < 10; i++) {
			System.out.println(i+1);
			Map<String, Object> jsonData = new HashMap<>();

			double value = random.nextDouble() * maxTemperature;
			boolean raining = random.nextBoolean();

			jsonData.put("value", value);
			jsonData.put("raining", raining);
			jsonData.put("sensor", Map.of("name", sensorName));

			makePostRequestWithJSONData(url, jsonData);
		}
	}

	private static void makePostRequestWithJSONData(String url, Map<String, Object> jsonData) {
		final RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

		try{
			restTemplate.postForObject(url, request, String.class);

			System.out.println("Measurement sent to the server!");
		} catch (HttpClientErrorException e) {
			System.out.println("Error!");
			System.out.println(e.getMessage());
		}
	}
}