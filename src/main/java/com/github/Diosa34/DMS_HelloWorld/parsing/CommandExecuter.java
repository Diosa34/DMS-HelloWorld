package com.github.Diosa34.DMS_HelloWorld.parsing;

import com.github.Diosa34.DMS_HelloWorld.Application;
import com.github.Diosa34.DMS_HelloWorld.ApplicationPart;
import com.github.Diosa34.DMS_HelloWorld.commands.Command;
import com.github.Diosa34.DMS_HelloWorld.enums.InstanceCreator;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;


public class CommandExecuter implements ApplicationPart {
    private AbstractParser parser;
    private Application applicationInstance;

    public CommandExecuter(String filepath, AbstractParser parser){
        this.applicationInstance = new Application(filepath);
        this.parser = parser;
    }

    public void execute(int attempts, InstanceCreator creator){
        for(Pair<? extends Command, ? extends String[]> i: parser) {
            i.getFirst().execute(i.getSecond(), attempts, creator, parser, applicationInstance);
        }
    }

    @NotNull
    @Override
    public Application getApplicationInstance() {
        return applicationInstance;
    }
}
