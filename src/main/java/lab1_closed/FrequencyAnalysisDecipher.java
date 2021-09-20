package lab1_closed;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import lab1_closed.utils.Alphabet;

// класс для проведения частотного анализа
public class FrequencyAnalysisDecipher {

    // сохраняем для каждой буквы её частоту
    HashMap<Character, Double> lettersFrequency;

    // инициализация и подсчёт частоты букв в строке
    public FrequencyAnalysisDecipher(String string) {
        lettersFrequency = calculateLettersFrequencyInString(string);
    }

    public HashMap<Character, Double> getLettersFrequency() {
        return lettersFrequency;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nFrequency analysis.\n" +
                "Letter \t: \tletter's frequency\n");
        for (Map.Entry<Character, Double> entry : lettersFrequency.entrySet()) {
            stringBuilder.append(String.format("%c \t: \t%.2f%n", entry.getKey(), entry.getValue() * 100));
        }
        return stringBuilder.toString();
    }

    // считаем частоты букв в строке
    private HashMap<Character, Double> calculateLettersFrequencyInString(String string) {
        // количество букв в строке
        int num = 0;
        // объект для сохранения частот букв
        HashMap<Character, Double> lettersFrequency = new HashMap<>();
        // преобразуем строку в нижний регистр
        string = string.toLowerCase(Locale.ROOT);
        // проходим по символам в строке
        for (char letter : string.toCharArray()) {
            // если это буква
            if (Alphabet.isLetter(letter)) {
                // увеличиваем на 1 счётчик буквы
                lettersFrequency.merge(letter, 1.0, Double::sum);
                // увеличиваем на 1 количество букв в строке
                num++;
            }
        }
        // для каждого символа считаем долю его встречи в строке
        for (Map.Entry<Character, Double> letter : lettersFrequency.entrySet()) {
            // обновляем значения частоты у буквы
            letter.setValue(letter.getValue() / num);
        }
        return lettersFrequency;
    }
}
