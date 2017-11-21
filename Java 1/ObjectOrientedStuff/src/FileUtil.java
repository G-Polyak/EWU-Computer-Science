//George Polyak
//FileUtil class

import java.io.*;
import java.util.*;

public class FileUtil {

    public static Scanner openInputFile(String fileName) {

        Scanner fileScanner = null;
        File fileHandle;

        try {

            fileHandle = new File(fileName);
            fileScanner = new Scanner(fileHandle);

        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " was not found");
        }
        return fileScanner;

    }

}