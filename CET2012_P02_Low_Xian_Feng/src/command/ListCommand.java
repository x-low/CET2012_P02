package command;

import core.CustomException;
import core.EmployeeManager;

public class ListCommand implements Command {
    private final EmployeeManager manager;

    public ListCommand(EmployeeManager employeeManager) {
        this.manager = employeeManager;
    }

    @Override
    public void execute() throws CustomException {
        manager.list();
        System.out.println("list");
    }

    @Override
    public void undo() throws CustomException {
        throw new CustomException("Error: Cannot undo");
    }

    @Override
    public boolean canUndo() {
        return false;
    }
}