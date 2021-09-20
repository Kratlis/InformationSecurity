package lab1_closed.utils;

import java.util.Arrays;
import lab1_closed.UnknownLetter;

// класс для работы с алфавитом
public class Alphabet {

    // алфавит
    public static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    // получаем позицию буквы в алфавите
    public static int getAlphabetPosition(char letter) {
        // проходим по буквам алфавита
        for (int i = 0; i < alphabet.length; i++) {
            // если была передана эта буква, возвращаем её номер
            if (String.valueOf(letter).equalsIgnoreCase(String.valueOf(alphabet[i]))) {
                return i;
            }
        }
        // если буква не была найдена, выбрасываем исключение
        throw new UnknownLetter(
            String.format("Alphabet %s doesn't contain input letter %c.", Arrays.toString(alphabet), letter));
    }

    // получаем букву на данной позиции
    public static char getLetterOnPosition(int position) {
        return alphabet[position];
    }

    // проверяем, что алфавит содержит букву
    public static boolean isLetter(char letter) {
        for (char c : alphabet) {
            if (c == letter) {
                return true;
            }
        }
        return false;
    }
}
