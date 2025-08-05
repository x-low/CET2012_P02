package core;

import command.Command;
import java.util.Stack;

public class Invoker {
	private Command[] cmdToExecute;

	public void setCommandsForExecution(Command[] cmds) {
		this.cmdToExecute = cmds;
	}

	public void executeCommand(Stack<Command> history) {
		for (Command cmd: cmdToExecute) {
			try {
				cmd.execute(history);
			} catch (IndexOutOfBoundsException | NullPointerException |
					CustomException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}