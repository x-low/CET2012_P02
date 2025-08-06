import command.*;
import core.*;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        EmployeeRegistry registry;
        EmployeeManager manager;
        Invoker invoker = new Invoker();
        Stack<Command> history = new Stack<Command>();
        try {
            registry = new EmployeeRegistry();
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            return ;
        }
        manager = new EmployeeManager(registry);
        Command[] payload = new Command[]{
                new AddCommand(manager, "first_name last_name valid@gmail.com"),
                new AddCommand(manager, "john Doe with-dash@email.sg"),
                new AddCommand(manager, "hanna moon wh_ats.th-at@gmail.com"),
                new AddCommand(manager, "ah boon .doesntwork@gmail.com"),
                new ListCommand(manager),
                new UpdateCommand(manager, "3 adam"),
                new ListCommand(manager),
                new UpdateCommand(manager, "1 blue bell ice-cream"),
                new ListCommand(manager),
                new DeleteCommand(manager, "1"),
                new ListCommand(manager),
                new UndoCommand(manager, history),
                new ListCommand(manager)
        };

        invoker.setCommandsForExecution(payload);
        invoker.executeCommand(history);
        System.out.println(history.toString());

        try {
            registry.storeToFile();
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }
}
