package com.github.Diosa34.DMS_HelloWorld;

import com.github.Diosa34.DMS_HelloWorld.classes.Vehicle;
import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles;
import com.github.Diosa34.DMS_HelloWorld.enums.InstanceCreator;
import com.github.Diosa34.DMS_HelloWorld.parsing.*;

import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] argv) throws FileNotFoundException {
        new CollectionOfVehicles();

        String arguments = null;
        if (argv.length != 0 && argv[0].trim().length() != 0){
            if (FileVerification.fullVerification(argv[0])){
                Vehicle.initializeVehicleForXMLToJava();
                (new XMLToJava(Vehicle.getVehicleForXMLToJava())).decoder(Paths.get(argv[0]).toString());
            }
            arguments = argv[0];
        } else {
            System.out.println("Название файла, содержащего значения для коллекции не было введено");
            System.out.println("Коллекция пуста");
        }

        System.out.println("Информация о командах доступна по команде 'help'");

        CommandExecutor executor = new CommandExecutor(arguments, new ScannerStringReader(System.in));
        executor.execute(3, InstanceCreator.CREATE_WITH_INPUT);
    }
}
