package parsing;

import commands.Command;
import enums.InstanceCreator;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class RequestsScanner {
    private final Scanner scanner;

    public RequestsScanner(InputStream input) throws FileNotFoundException {
        this.scanner = new Scanner(input);
    }


    public void makeRequest(int t, InstanceCreator creator, RequestsScanner scanner) {
        while (this.scanner.hasNextLine()) {
            String[] request = this.scanner.nextLine().split(" ");
            if (Command.registry.containsKey(request[0].trim().toLowerCase())) {
                Command.registry.get(request[0].trim().toLowerCase()).execute(request, t, creator, this.scanner);
            } else {
                System.out.println("Введена несуществующая команда, информация о командах доступна по команде 'help'");
                if (t == 1) {
                    break;
                }
            }
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

//        this.request[0].executeCall();


    // Command.commandsMap[...](...)
}
