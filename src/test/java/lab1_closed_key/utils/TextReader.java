package lab1_closed_key.utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TextReader {

    public static String readText(String filePath) {
        StringBuilder res = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()){
                res.append(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toString();
    }
}
