import collection.CollectionOfVehicles;
import enums.InstanceCreator;
import parsing.RequestsScanner;
import parsing.XMLToJava;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> vehicleForXMLToJava = new HashMap<>();
        vehicleForXMLToJava.put("name", "");
        vehicleForXMLToJava.put("coordinates", "");
        vehicleForXMLToJava.put("enginePower", "");
        vehicleForXMLToJava.put("vehicleType", "");
        vehicleForXMLToJava.put("fuelType", "");

        new CollectionOfVehicles();

        (new XMLToJava(vehicleForXMLToJava)).decoder(args[0]);

        try {
            RequestsScanner scanner = new RequestsScanner(System.in);

            scanner.makeRequest(3, InstanceCreator.CREATE_WITH_INPUT, scanner);
        } catch(FileNotFoundException ex) {
            System.out.println("Файл не найден");
        }


        System.out.println("Информация о командах доступна по команде 'help'");
    }
}
