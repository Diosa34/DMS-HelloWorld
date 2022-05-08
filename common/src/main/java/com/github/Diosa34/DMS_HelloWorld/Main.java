package com.github.Diosa34.DMS_HelloWorld;

import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] argv) throws FileNotFoundException, UnexpectedCommandException, ParseException {
        new CollectionOfVehicles();
        Logger logger = ConsoleLogger.INSTANCE;

        String arguments = null;
        if (argv.length != 0 && argv[0].trim().length() != 0){
            try {
                if (FileVerification.fullVerification(argv[0])) {
                    Vehicle.initializeVehicleForXMLToJava();
                    (new XMLToJava(Vehicle.getVehicleForXMLToJava())).decoder(Paths.get(argv[0]).toString());
                }
            } catch(NullPointerException | SecurityException e) {
                logger.print(e.getMessage());
            }
            arguments = argv[0];
        } else {
            logger.print("Название файла, содержащего значения для коллекции не было введено");
            logger.print("Коллекция пуста");
        }

        logger.print("Информация о командах доступна по команде 'help'");

        RequestManager requestManager = new RequestManager(arguments);
        RequestManager.read(logger, 3, InstanceCreator.CREATE_WITH_INPUT, ConsoleStringReader.INSTANCE);
    }
}
