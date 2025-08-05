package command;

import core.CustomException;
import core.EmployeeManager;

import java.util.Stack;

public class UpdateCommand implements Command {
    private final EmployeeManager manager;
    private final String[] data;
    private String prevEntry;

    public UpdateCommand(EmployeeManager manager, String idx, String data1) {
        this.manager = manager;
        this.data = new String[]{idx,data1};
    }

    public UpdateCommand(EmployeeManager manager, String idx,
                         String data1, String data2) {
        this.manager = manager;
        this.data = new String[]{idx,data1,data2};
    }

    public UpdateCommand(EmployeeManager manager, String idx,
                         String data1, String data2, String data3) {
        this.manager = manager;
        this.data = new String[]{idx,data1,data2,data3};
    }

    @Override
    public void execute(Stack<Command> history) throws CustomException {
        prevEntry = manager.update(data);
        history.push(this);
    }

    @Override
    public void undo() throws CustomException {
        manager.delete(data[0]);
        manager.insert(data[0], prevEntry);
    }
}