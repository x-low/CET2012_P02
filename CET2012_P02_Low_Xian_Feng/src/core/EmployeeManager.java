package core;

import java.util.ArrayList;

public class EmployeeManager {
    private final EmployeeRegistry registry;

    public EmployeeManager(EmployeeRegistry employeeRegistry) {
        this.registry = employeeRegistry;
    }

    public void add(String[] data) {
        ArrayList<String> dataStore = this.registry.getDataStore();
        String line = String.format("%02d", dataStore.size() + 1) +
                ". " + data[0] + " " + data[1] + " " + data[2];
        dataStore.add(line);
    }

    public void deleteLastEntry() {
        ArrayList<String> dataStore = this.registry.getDataStore();
        dataStore.removeLast();
    }

    public String delete(String index) {
        ArrayList<String> dataStore = this.registry.getDataStore();
        try {
            int idx = Integer.parseInt(index);
            String deletedEntry = dataStore.get(idx - 1);
            dataStore.remove(idx - 1);
            for (int i = idx - 1; i < dataStore.size(); i++) {
                String entry = dataStore.get(i);
                String[] split = entry.split("\\.");
                String newEntry = String.format("%02d.", idx) + split[1];
                dataStore.set(i, newEntry);
            }
            return (deletedEntry);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void insert(String index, String data) {
        ArrayList<String> dataStore = this.registry.getDataStore();
        try {
            int idx = Integer.parseInt(index);
            dataStore.add(idx - 1, data);
            for (int i = idx; i < dataStore.size(); i++) {
                String entry = dataStore.get(i);
                String[] split = entry.split("\\.");
                String newEntry = String.format("%02d.", idx) + split[1];
                dataStore.set(i, newEntry);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
