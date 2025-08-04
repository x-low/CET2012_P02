package command;
import command.Command;
import core.CustomException;
import core.EmployeeRegistry;

import java.util.Stack;

public class UndoCommand implements Command {
    private final EmployeeRegistry registry;

    public UndoCommand(EmployeeRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void execute(Stack<Command> history) throws CustomException {
        if (history.isEmpty())
            throw new CustomException("Error: Nothing to undo");

        Command prevCommand = history.pop();
        prevCommand.undo();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Error: Cannot undo");
    }
}