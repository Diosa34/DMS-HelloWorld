package com.github.Diosa34.DMS_HelloWorld.parsing;

import com.github.Diosa34.DMS_HelloWorld.Application;
import com.github.Diosa34.DMS_HelloWorld.ApplicationPart;
import com.github.Diosa34.DMS_HelloWorld.commands.BoundCommand;
import com.github.Diosa34.DMS_HelloWorld.enums.InstanceCreator;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

/**
 * This class invokes commands
 */
public class CommandExecutor implements ApplicationPart {
    private final AbstractParser parser;
    private final Application applicationInstance;

    /**
     *
     * @param filepath is initial to save the collection
     * @param parser scanner for client or tests
     */
    public CommandExecutor(String filepath, AbstractParser parser){
        this.applicationInstance = new Application(filepath);
        this.parser = parser;
    }

    /**
     *
     * @param attempts for data input
     * @param creator for instance creating from file or interactive
     */
    public void execute(int attempts, InstanceCreator creator){
        for(Pair<? extends BoundCommand, ? extends String[]> i: parser) {
            if (i != null) {
                i.getFirst().execute(i.getSecond(), attempts, creator, parser, applicationInstance);
            } else {
                System.out.println("Введена несуществующая команда, информация о командах доступна по команде 'help'");
            }
        }
    }

    @NotNull
    @Override
    public Application getApplicationInstance() {
        return applicationInstance;
    }
}
