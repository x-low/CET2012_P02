package command;

import core.CustomException;
import core.EmployeeManager;
import core.EmployeeRegistry;
import java.util.Stack;

public class AddCommand implements Command {
    private final EmployeeManager manager;
    private final String[] data;

    public AddCommand(EmployeeManager employeeManager,
                      String data1, String data2, String data3) {
        this.manager = employeeManager;
        this.data = new String[]{data1, data2, data3};
    }

    @Override
    public void execute() throws CustomException {
        manager.add(data);
    }

    @Override
    public void undo() {
        manager.deleteLastEntry();
    }

    @Override
    public boolean canUndo() {
        return true;
    }
}