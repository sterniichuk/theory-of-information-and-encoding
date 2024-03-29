package utils.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class FileGetter {
    private final String fileName;
    private final String pathToDataFolder = "src/resources/";
    private final File file;

    public FileGetter(String fileName) {
        this.fileName = fileName;
        file = new File(pathToDataFolder + fileName);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getFileAsString() {
        return getFileAsString(pathToDataFolder + fileName);
    }

    public static String getFileAsString(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFileAsString(File file) {
        return getFileAsString(file.getPath());
    }

    public static byte[] getFileAsByteArray(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] getFileAsByteArray(File file) {
        return getFileAsByteArray(file.getPath());
    }

    public static String getBinaryFileAsString(String path) {
        var array = getFileAsByteArray(path);
        return Base64.getEncoder().encodeToString(array);
    }

    public static File[] getFiles(String pathToFolder){
        var folder = new File(pathToFolder);
        if(!folder.isDirectory()){
            throw new IllegalArgumentException("It is not a directory");
        }
        return getFiles(folder);
    }

    public static File[] getFiles(File folder){
        return folder.listFiles();
    }

    @SuppressWarnings("unused")
    public static String getBinaryFileAsString(File file) {
        return getBinaryFileAsString(file.getPath());
    }

    public File getFile() {
        return file;
    }
}
