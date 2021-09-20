package lab1_closed;

import lab1_closed.utils.Alphabet;

public class CaesarDecipher {

    // получаем зашифрованную строку и количество символов, на которое был произведён сдвиг
    String decipher(String cipheredMessage, int offset) {
        // считаем сдвиг относительно длины алфавита
        offset = offset % Alphabet.alphabet.length;
        // объект для дешифрованной строки
        StringBuilder result = new StringBuilder();
        // проходим по символу в зашифрованной строке
        for (char letter : cipheredMessage.toCharArray()) {
            // если это буква, то расшифровываем её
            if (Alphabet.isLetter(letter)) {
                // получаем позицию буквы
                int shiftedAlphabetPosition = Alphabet.getAlphabetPosition(letter);
                // считаем позицию исходной буквы относительно начала алфавита
                int newAlphabetPosition =
                    (Alphabet.alphabet.length + shiftedAlphabetPosition - offset)
                        % Alphabet.alphabet.length;
                // получаем букву, находящаяся на вычисленной позиции
                char newCharacter = Alphabet.getLetterOnPosition(newAlphabetPosition);
                // добавляем букву в дешифрованную строку
                result.append(newCharacter);
            } else {
                // добавляем символ в дешифрованную строку
                result.append(letter);
            }
        }
        // возвращаем дешифрованную строку
        return result.toString();
    }
}
