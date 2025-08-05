package command;

import core.CustomException;
import core.EmployeeManager;
import core.EmployeeRegistry;

import java.util.Stack;

public class DeleteCommand implements Command {
    private final EmployeeManager manager;
    private final String index;
    private String deletedEntry;

    public DeleteCommand(EmployeeManager employeeManager, String idx) {
        this.manager = employeeManager;
        this.index = idx;
    }

    @Override
    public void execute(Stack<Command> history) throws CustomException {
        this.deletedEntry = manager.delete(index);
        history.push(this);
    }

    public void undo() {
        manager.insert(index, this.deletedEntry);
    }
}