package command;

import core.CustomException;

public interface Command {
    void execute() throws CustomException;
}