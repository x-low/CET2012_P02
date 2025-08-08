package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Handles reading from, writing to and storing contents of datastore file
 */
public class FileHandler {
    /**
     * Content of datastore file
     */
    private ArrayList<String> dataStore;
    /**
     * Path to datastore file
     */
    private static final String dataStorePath =
            System.getProperty("user.dir") + "/src/dataStore.txt";

    /**
     * Constructs a {@code FileHandler}, reading and storing datastore
     * file contents or creating an empty datastore if file does not
     * exist or cannot be read
     */
    public FileHandler() {
        Path data = Paths.get(dataStorePath);

        if (!Files.exists(data))
            dataStore = new ArrayList<>();
        else {
            try {
                dataStore = (ArrayList<String>) Files.readAllLines(data);
            } catch (IOException e) {
                System.out.println("Error: Cannot read existing datastore");
                dataStore = new ArrayList<>();
            }
        }
    }

    /**
     * Saves contents of datastore back into the file after
     * modifying commands have been executed
     */
    public void storeToFile() {
        Path data = Paths.get(dataStorePath);

        try {
            Files.write(data, dataStore);
        } catch (IOException e) {
            System.out.println("Error: Cannot write to datastore");
        }
    }

    /**
     * Returns contents of datastore
     * @return @{code ArrayList} datastore
     */
    ArrayList<String> getDataStore() {
        return (this.dataStore);
    }
}