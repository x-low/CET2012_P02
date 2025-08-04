package command;
import core.CustomException;
import core.EmployeeRegistry;

import java.util.Stack;

public class AddCommand implements Command {
    private final EmployeeRegistry registry;
    private final String[] data;

    public AddCommand(EmployeeRegistry registry,
                      String data1, String data2, String data3) {
        this.registry = registry;
        this.data = new String[]{data1, data2, data3};
    }

    @Override
    public void execute(Stack<Command> history) throws CustomException {
        registry.add(data);
        history.push(this);
    }

    public void undo() {
        registry.deleteLastEntry();
    }
}