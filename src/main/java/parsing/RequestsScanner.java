package parsing;

import commands.Command;
import enums.InstanceCreator;

import java.io.InputStream;
import java.util.Scanner;


public class RequestsScanner {
    private final Scanner scanner;

    /**
     * Depending on the InputStream, scans a file or console
     * @param input - InputStream - console or file
     */
    public RequestsScanner(InputStream input) {
        this.scanner = new Scanner(input);
    }

    /**
     * Reads and executes a request
     * @param t - available number of attempts to enter
     * @param creator - link to constructor of Vehicle
     */
    public void makeRequest(int t, InstanceCreator creator) {
        while (this.scanner.hasNextLine()) {
            String[] request = this.scanner.nextLine().trim().split("\\s");
            if (Command.registry.containsKey(request[0].trim())) {
                Command.registry.get(request[0].trim()).execute(request, t, creator, this.scanner);
            } else {
                System.out.println("Введена несуществующая команда, информация о командах доступна по команде 'help'");
                if (t == 1) {
                    break;
                }
            }
        }
    }
}
