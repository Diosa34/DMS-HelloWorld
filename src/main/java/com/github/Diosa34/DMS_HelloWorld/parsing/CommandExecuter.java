package com.github.Diosa34.DMS_HelloWorld.parsing;

import com.github.Diosa34.DMS_HelloWorld.Application;
import com.github.Diosa34.DMS_HelloWorld.ApplicationPart;
import com.github.Diosa34.DMS_HelloWorld.commands.Command;
import com.github.Diosa34.DMS_HelloWorld.enums.InstanceCreator;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;


public class CommandExecuter implements ApplicationPart {
    private final AbstractParser parser;
    private final Application applicationInstance;

    public CommandExecuter(String filepath, AbstractParser parser){
        this.applicationInstance = new Application(filepath);
        this.parser = parser;
    }

    public void execute(int attempts, InstanceCreator creator){
        for(Pair<? extends Command, ? extends String[]> i: parser) {
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
