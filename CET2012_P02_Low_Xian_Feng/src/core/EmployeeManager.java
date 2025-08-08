package core;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The main receiver class handling business logic for execution
 * of {@code Command} concrete classes, containing the implementation
 * of their {@code execute()} and {@code undo()} methods by
 * interfacing with the datastore file through {@code FileHandler}
 */
public class EmployeeManager {
    /**
     * Contains the datastore and methods for reading and writing to datastore
     */
    private final FileHandler fileHandler;

    /**
     * Constructs a {@code EmployeeManager} with a {@code FileHandler} instance
     */
    public EmployeeManager() {
        this.fileHandler = new FileHandler();
    }

    /**
     * Invokes the {@code FileHandler}'s {@code storeToFile()} method to
     * save changes made by execution of commands to datastore file
     */
    public void storeToFile() {
        fileHandler.storeToFile();
    }

    /**
     * Creates and adds a new entry to the datastore
     * @param data new entry's data fields consisting of
     *             first_name, last_name and email
     * @throws CustomException if email provided is invalid
     */
    public void add(String[] data) throws CustomException {
        if (ManagerTools.isLatinEmail(data[2]))
            data[2] = ManagerTools.toTitleCase(data[2]);
        ArrayList<String> dataStore = this.fileHandler.getDataStore();
        String line = String.format("%02d", dataStore.size() + 1) +
                ". " + data[0] + " " + data[1];
        line = ManagerTools.toTitleCase(line) + " " + data[2];
        dataStore.add(line);
    }

    /**
     * Deletes the most recently added entry from the datastore
     * @throws CustomException if there are no entries to be removed
     */
    public void deleteLastEntry() throws CustomException {
        ArrayList<String> dataStore = this.fileHandler.getDataStore();
        try {
            dataStore.remove(dataStore.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new CustomException("Error: Nothing to delete");
        }
    }

    /**
     * Deletes the selected entry from the datastore
     * @param idx index of the entry to be deleted
     * @return the deleted entry
     * @throws CustomException if entry corresponding with index
     * does not exist in the datastore
     */
    public String delete(int idx) throws CustomException {
        ArrayList<String> dataStore = this.fileHandler.getDataStore();
        String deletedEntry;
        try {
            deletedEntry = dataStore.get(idx - 1);
            dataStore.remove(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new CustomException("Error: Index entry does not exist");
        }
        for (int i = idx - 1; i < dataStore.size(); i++) {
            String entry = dataStore.get(i);
            String[] split = entry.split("^[^.]+");
            String newEntry = String.format("%02d", i + 1) + split[1];
            dataStore.set(i, newEntry);
        }
        return (deletedEntry);
    }

    /**
     * Creates and adds an entry to a specific position in the datastore
     * @param idx where the new entry will be placed
     * @param data new entry's data fields consisting of
     *             first_name, last_name and email
     * @throws CustomException if index is not within range of existing entries
     */
    public void insert(int idx, String data) throws CustomException {
        ArrayList<String> dataStore = this.fileHandler.getDataStore();
        try {
            dataStore.add(idx - 1, data);
        } catch (IndexOutOfBoundsException e) {
                throw new CustomException("Error: Cannot insert at index");
        }
        for (int i = idx; i < dataStore.size(); i++) {
            String entry = dataStore.get(i);
            String[] split = entry.split("^[^.]+");
            String newEntry = String.format("%02d", i + 1) + split[1];
            dataStore.set(i, newEntry);
        }
    }

    /**
     * Prints to stdout the contents of datastore
     * @throws CustomException if datastore is empty
     */
    public void list() throws CustomException {
        ArrayList<String> dataStore = this.fileHandler.getDataStore();
        if (dataStore.isEmpty())
            throw new CustomException("Error: Nothing to list");
        System.out.println("list");
        dataStore.forEach(System.out::println);
    }

    /**
     * Changes the content of a currently existing entry in datastore
     * @param idx index of entry to be modified
     * @param data new data to replace existing content in entry
     * @return the original entry before modification
     * @throws CustomException if entry to be changed does not exist
     */
    public String update(int idx, String[] data) throws CustomException {
        ArrayList<String> dataStore = this.fileHandler.getDataStore();
        String entry, newEntry;
        String[] split;
        try {
            entry = dataStore.get(idx - 1);
            split = entry.split(" ");
            newEntry = String.format("%02d. ", idx) + data[1];
        } catch (IndexOutOfBoundsException e) {
            throw new CustomException("Error: Index entry does not exist");
        }
        if (data.length > 2)
            newEntry += " " + data[2];
        else
            newEntry += " " + split[2];
        newEntry = ManagerTools.toTitleCase(newEntry);
        if (data.length > 3) {
            if (ManagerTools.isLatinEmail(data[3]))
                data[3] = ManagerTools.toTitleCase(data[3]);
            newEntry += " " + data[3];
        }
        else
            newEntry += " " + split[3];
        this.delete(idx);
        this.insert(idx,  newEntry);
        return (entry);
    }
}