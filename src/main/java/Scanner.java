import commands.Add;
import commands.Command;

import java.util.HashMap;

public class Scanner {
    private String[] request;
    private java.util.Scanner scanner;

    public Scanner(java.util.Scanner scanner){
        this.scanner = scanner;
        java.util.Scanner in = new java.util.Scanner(System.in);
        this.request = in.nextLine().split(" ");
    }

    public void callCommand(){
//        this.request[0].executeCall();
        Command.registry.get(this.request[0]).execute(this.request);

        // Command.commandsMap[...](...)
    }
}
