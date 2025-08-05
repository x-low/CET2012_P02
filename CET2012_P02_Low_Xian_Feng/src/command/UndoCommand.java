package command;

import core.CustomException;
import core.EmployeeManager;


import java.util.Stack;

public class UndoCommand implements Command {
    private final EmployeeManager manager;

    public UndoCommand(EmployeeManager employeeManager) {
        this.manager = employeeManager;
    }

    @Override
    public void execute(Stack<Command> history) throws CustomException {
        manager.undo(history);
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Error: Cannot undo");
    }
}