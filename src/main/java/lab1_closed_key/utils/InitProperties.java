package lab1_closed_key.utils;

import utils.TextReader;

public class InitProperties {
    public static int OFFSET = 3;
    public static String TEXT_FILE_PATH = "src/test/resources/lab1_closed_key/text.txt";
    public static String EXPECTED_TEXT_FILE_PATH = "src/test/resources/lab1_closed_key/expectedText.txt";

    public static String ORIGINAL_TEXT = TextReader.readText(TEXT_FILE_PATH);
    public static String EXPECTED_TEXT = TextReader.readText(EXPECTED_TEXT_FILE_PATH);
}
