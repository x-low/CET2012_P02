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
                new AddCommand(manager, "one one latincase"),
                new AddCommand(manager, "two two 0numberfirst"),
                new AddCommand(manager, "three three ____"),
                new UpdateCommand(manager, "1 first last .invalid@gmail.com"),
                new UpdateCommand(manager, "2 another try validonethistime"),
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
