package core;

import command.Command;
import java.util.Stack;

/**
 * For storage and execution of {@code Command} instances
 */
public class Invoker {
	/**
	 * Array of {@code Command}s to be executed
	 */
	private Command[] cmdToExecute;

	/**
	 * Constructs a default {@code Invoker}
	 */
	public Invoker() {}

	/**
	 * Accepts and stores commands to be executed
	 * @param cmds {@code Command[]} commands to be executed
	 */
	public void setCommandsForExecution(Command[] cmds) {
		this.cmdToExecute = cmds;
	}

	/**
	 * Loops through array of preloaded commands invoking
	 * their {@code execute()} methods
	 * @param history {@code Stack<Command>} to store
	 *                                      successfully executed commands
	 */
	public void executeCommand(Stack<Command> history) {
		for (Command cmd: cmdToExecute) {
			try {
				cmd.execute();
				if (cmd.canUndo())
					history.push(cmd);
			} catch (CustomException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}