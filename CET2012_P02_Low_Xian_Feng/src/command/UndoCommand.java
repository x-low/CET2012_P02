package command;

import core.CustomException;
import core.EmployeeManager;
import java.util.Stack;

/**
 * Represents a request to undo the previously executed command
 */
public class UndoCommand implements Command {
    /**
     * Receiver responsible for handling and storing data
     */
    private final EmployeeManager manager;
    /**
     * Stores previously executed commands that support undo
     */
    private final Stack<Command> history;

    /**
     * Constructs a {@code UndoCommand} with the receiver and command history
     * @param employeeManager data storage handler
     * @param commandHistory {@code Stack} containing undoable previously
     *                       executed commands
     */
    public UndoCommand(EmployeeManager employeeManager, Stack<Command> commandHistory) {
        this.manager = employeeManager;
        this.history = commandHistory;
    }

    /**
     * Calls the undo function of the previously executed
     * command, if any
     * @throws CustomException if there are no previously executed commands
     * or if {@code undo()} of previously executed command throws
     */
    @Override
    public void execute() throws CustomException {
        if (history.isEmpty())
            throw new CustomException("Error: Nothing to undo");
        Command prevCommand = history.pop();
        prevCommand.undo();
        System.out.println("undo");
    }

    /**
     * Undo is not supported for the undo command
     * @throws CustomException as undo functionality is not supported
     */
    @Override
    public void undo() throws CustomException {
        throw new CustomException("Error: Cannot undo");
    }

    /**
     * Returns {@code false} indicating undo functionality is not supported
     * @return {@code false}
     */
    @Override
    public boolean canUndo() {
        return false;
    }
}