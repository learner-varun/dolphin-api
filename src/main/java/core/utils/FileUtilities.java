package core.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtilities {

    public static void deleteFolder(String pathOfTheFolder) {
        try {
            File f = new File(pathOfTheFolder);
            if (f.exists() && f.isDirectory()) {
                FileUtils.forceDelete(new File(pathOfTheFolder));
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void moveFolder(String sourceFolder, String destinationLocation) {
        if (Files.isDirectory(Paths.get(sourceFolder))) {
            try {
                Files.move(new File(sourceFolder).toPath(), new File(destinationLocation).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readDataFromFileToString(String filePath) {
        File f = new File(filePath);
        if (f.exists()) {
            InputStream is = null;
            try {
                is = new FileInputStream(filePath);
            } catch (FileNotFoundException e) {
                return "The Requested File is not found";
            }
            try {
                return IOUtils.toString(is, "UTF-8");
            } catch (IOException e) {
                return "Sorry something happened wrong";
            }
        }
        return "Sorry something happened wrong";
    }
}
