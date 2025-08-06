package command;

import core.CustomException;
import core.EmployeeManager;
import java.util.Stack;

public class UndoCommand implements Command {
    private final EmployeeManager manager;
    private final Stack<Command> history;

    public UndoCommand(EmployeeManager employeeManager, Stack<Command> commandHistory) {
        this.manager = employeeManager;
        this.history = commandHistory;
    }

    @Override
    public void execute() throws CustomException {
        if (history.isEmpty())
            throw new CustomException("Error: Nothing to undo");
        Command prevCommand = history.pop();
        prevCommand.undo();
    }

    @Override
    public void undo() throws  CustomException {
        throw new CustomException("Error: Cannot undo");
    }

    @Override
    public boolean canUndo() {
        return false;
    }
}