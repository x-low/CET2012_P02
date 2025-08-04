import command.*;
import core.*;

public class Main {
    public static void main(String[] args) {
        EmployeeRegistry registry;
        try {
            registry = new EmployeeRegistry();
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            return ;
        }
        registry.add(new String[]{"first", "last", "email"});
        registry.add(new String[]{"second", "last", "email"});

        try {
            registry.storeToFile();
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }
}
