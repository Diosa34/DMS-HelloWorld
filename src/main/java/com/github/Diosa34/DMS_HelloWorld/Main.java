package com.github.Diosa34.DMS_HelloWorld;

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles;
import com.github.Diosa34.DMS_HelloWorld.enums.InstanceCreator;
import com.github.Diosa34.DMS_HelloWorld.parsing.*;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {
    public static void main(String[] argv) throws FileNotFoundException {
        HashMap<String, String> vehicleForXMLToJava = new HashMap<>();
        vehicleForXMLToJava.put("id", "");
        vehicleForXMLToJava.put("name", "");
        vehicleForXMLToJava.put("coordinates", "");
        vehicleForXMLToJava.put("creationDate", "");
        vehicleForXMLToJava.put("enginePower", "");
        vehicleForXMLToJava.put("vehicleType", "");
        vehicleForXMLToJava.put("fuelType", "");

        new CollectionOfVehicles();


        if (argv.length != 0){
            new Application(argv[0]);
            if (FileVerification.fullVerification(argv[0])){
                (new XMLToJava(vehicleForXMLToJava)).decoder(Paths.get(argv[0]).toString());
            }
        } else {
            System.out.println("Название файла, содержащего значения для коллекции не было введено");
            System.out.println("Коллекция пуста");
        }

        System.out.println("Информация о командах доступна по команде 'help'");

        CommandExecuter executer = new CommandExecuter(argv[0], new ScannerParser(System.in));
        executer.execute(3, InstanceCreator.CREATE_WITH_INPUT);
    }
}
