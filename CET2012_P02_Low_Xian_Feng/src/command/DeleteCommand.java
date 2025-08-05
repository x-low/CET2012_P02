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
        manager = employeeManager;
        index = idx;
    }

    @Override
    public void execute() throws CustomException {
        deletedEntry = manager.delete(index);
    }

    public void undo() {
        manager.insert(index, deletedEntry);
    }

    @Override
    public boolean canUndo() {
        return true;
    }
}