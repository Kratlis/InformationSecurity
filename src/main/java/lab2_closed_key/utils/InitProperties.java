package lab2_closed_key.utils;

import utils.TextReader;

public class InitProperties {
    public static String TEXT_FILE_PATH = "src/test/resources/lab2_closed_key/text.txt";
    public static String KEY_FILE_PATH = "src/test/resources/lab2_closed_key/key.txt";

    public static String ORIGINAL_TEXT = TextReader.readText(TEXT_FILE_PATH);
    public static String KEY = TextReader.readText(KEY_FILE_PATH);
}
