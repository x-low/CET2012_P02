package command;

import core.CustomException;

import java.util.Stack;

public interface Command {
    void execute(Stack<Command> history) throws CustomException;
    void undo() throws CustomException;
}