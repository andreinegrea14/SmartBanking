package smart.banking.services;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {
    private static final String APPLICATION_FOLDER = ".smart-banking";
    private static final String USER_FOLDER = System.getProperty("user.home");
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    public static final Path APPLICATION_HOME_PATH2 = Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    public static final Path APPLICATION_HOME_PATH3 = Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    public static Path getPathToFile(String... path) {

        return APPLICATION_HOME_PATH.resolve(Paths.get(".", path));
    }
    public static Path getPathToFile2(String... path) {
        return APPLICATION_HOME_PATH2.resolve(Paths.get(".", path));
    }
    public static Path getPathToFile3(String... path) {
        return APPLICATION_HOME_PATH3.resolve(Paths.get(".", path));
    }
}

