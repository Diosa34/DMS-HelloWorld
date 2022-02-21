package commands;
import classes.Vehicle;
import org.jetbrains.annotations.NotNull;

public class Add extends Command {
    public Add(String[] request) {
        super(request);
        execute();
    }

    @Override
    public void execute() {
    }

    @NotNull
    @Override
    public String getHelp() {
        return "Добавляет новое средство передвижения в список";
    }
}
