package smart.banking.services;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {
    public static String APPLICATION_FOLDER = ".smart-banking";
    private static final String USER_FOLDER = System.getProperty("user.home");

    public static Path getPathToFile2(String... path) {
        return getApplicationHomeFolder().resolve(Paths.get(".", path));
    }
    public static Path getPathToFile3(String... path) {
        return getApplicationHomeFolder().resolve(Paths.get(".", path));
    }
    public static Path getPathToFile(String... path) {
        return getApplicationHomeFolder().resolve(Paths.get(".", path));
    }

    public static Path getApplicationHomeFolder() {
        return Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    }

    static void initDirectory() {
        Path applicationHomePath = getApplicationHomeFolder();
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }
}