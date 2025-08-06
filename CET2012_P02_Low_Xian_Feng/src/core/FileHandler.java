package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileHandler {
    private ArrayList<String> dataStore;
    private static final String dataStorePath =
            System.getProperty("user.dir") + "/src/dataStore.txt";

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

    public void storeToFile() {
        Path data = Paths.get(dataStorePath);

        try {
            Files.write(data, dataStore);
        } catch (IOException e) {
            System.out.println("Error: Cannot write to datastore");
        }
    }

    ArrayList<String> getDataStore() {
        return (this.dataStore);
    }
}