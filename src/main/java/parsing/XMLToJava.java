package parsing;

import classes.Coordinates;
import classes.FuelType;
import classes.Vehicle;
import classes.VehicleType;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

// можно оформить как Utility-класс
public class XMLToJava {
    private HashMap<String, String> vehicle;
    private final Map<Integer, VehicleType> vehicleTypeMap;
    private final Map<Integer, FuelType> fuelTypeMap;

    public XMLToJava(){
        this.vehicle = new HashMap<>();
        vehicle.put("name", "");
        vehicle.put("coordinates", "");
        vehicle.put("enginePower", "");
        vehicle.put("vehicleType", "");
        vehicle.put("fuelType", "");
        this.vehicleTypeMap = Map.of(VehicleType.CAR.getIndex(), VehicleType.CAR,
                VehicleType.SUBMARINE.getIndex(), VehicleType.SUBMARINE,
                VehicleType.SHIP.getIndex(), VehicleType.SHIP);
        this.fuelTypeMap = Map.of(FuelType.GASOLINE.getIndex(), FuelType.GASOLINE,
                FuelType.DIESEL.getIndex(), FuelType.DIESEL,
                FuelType.NUCLEAR.getIndex(), FuelType.NUCLEAR);
    }

    private void decoder(String fileName){
        try {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(new BufferedReader(new FileReader(fileName)));
            String tag = "";
            while (xmlr.hasNext()) {
                xmlr.next();
                if (xmlr.isStartElement()) {
                    tag = xmlr.getLocalName(); // открывающийся тег
                } else if (xmlr.hasText() && xmlr.getText().trim().length() > 0) {
                    function(tag, xmlr.getText()); // содержание тега
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
    }

    private void function(String key, String value){
        if(vehicle.containsKey(key)){
            vehicle.put(key, value);
        } else{
            System.out.println(String.format("Ошибка в имени поля, поле %s не найдено", key));
        }
    }

    public Vehicle parser(String filename){
        decoder(filename);

        return new Vehicle(vehicle.get("name"),
                new Coordinates(Float.parseFloat(vehicle.get("coordinates").split(" ")[0]), Integer.parseInt(vehicle.get("coordinates").split(" ")[1])),
                Float.parseFloat(vehicle.get("enginePower")),
                vehicleTypeMap.get(Integer.parseInt(vehicle.get("vehicleType"))),
                fuelTypeMap.get(Integer.parseInt(vehicle.get("fuelType"))));
    }
}

