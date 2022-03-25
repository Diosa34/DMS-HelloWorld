import collection.CollectionOfVehicles;
import enums.InstanceCreator;
import parsing.FileVerification;
import parsing.RequestsScanner;
import parsing.XMLToJava;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, String> vehicleForXMLToJava = new HashMap<>();
        vehicleForXMLToJava.put("id", "");
        vehicleForXMLToJava.put("name", "");
        vehicleForXMLToJava.put("coordinates", "");
        vehicleForXMLToJava.put("creationDate", "");
        vehicleForXMLToJava.put("enginePower", "");
        vehicleForXMLToJava.put("vehicleType", "");
        vehicleForXMLToJava.put("fuelType", "");

        new CollectionOfVehicles();

        if (args.length != 0){
            if (FileVerification.pathCheck(args[0])) {
                (new XMLToJava(vehicleForXMLToJava)).decoder(Paths.get(args[0]).toString());
            }
        } else {
            System.out.println("Название файла, содержащего значения для коллекции не было введено");
            System.out.println("Коллекция пуста");
        }

        System.out.println("Информация о командах доступна по команде 'help'");

        RequestsScanner scanner = new RequestsScanner(System.in);
        scanner.makeRequest(3, InstanceCreator.CREATE_WITH_INPUT);
    }
}
