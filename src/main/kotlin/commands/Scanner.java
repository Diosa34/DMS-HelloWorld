package commands;

import commands.Command;

import java.io.InputStream;

@SuppressWarnings("unused")
public class Scanner {
    private String[] request;
    private final java.util.Scanner scanner;

    public Scanner(InputStream input){
        this.scanner = new java.util.Scanner(input);
    }

    public void makeRequest(){
        while (scanner.hasNextLine()) {
            this.request = scanner.nextLine().split(" ");
            callCommand();
        }
    }

    public void callCommand(){
//        this.request[0].executeCall();
        Command.registry.get(this.request[0]).execute(this.request);

        // Command.commandsMap[...](...)
    }
}
