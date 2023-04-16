import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

public class MainMenu {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int enter = 99;

        while (enter != 0) {

            System.out.println("Main menu: ");
            System.out.println("1. Register sensor");
            System.out.println("2. Send 10 measurement to the server");
            System.out.println("3. Get all sensors from the server");
            System.out.println("4. Get all measurements from the server");
            System.out.println("5. Get count of rainy days");
            System.out.println("0. Exit");

            System.out.println("Chose: ");

            try {
                enter = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                System.out.println("Incorrect value");
            }

            switch (enter) {
                case (1) :
                    ClientPost.registerSensor();
                    break;

                case(2) :
                   ClientPost.sendMeasurement();
                    break;

                case(3) :
                    String listOfSensors = ClientGet.getSensorsFromServer();
                    System.out.println(listOfSensors);
                    break;

                case(4) :
                    List<Double> temperatures = ClientGet.getTemperaturesFromServer();
                    System.out.println(temperatures);
                    break;

                case(5) :
                    Integer rainyDaysCount = ClientGet.getRainyDaysCountFromServer();
                    System.out.println("Rainy days: " + rainyDaysCount);
                    break;
            }
        }
    }
}
