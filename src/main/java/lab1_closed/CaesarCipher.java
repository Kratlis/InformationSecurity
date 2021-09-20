package lab1_closed;

import lab1_closed.utils.Alphabet;

public class CaesarCipher {

    // получаем строку, которую надо зашифровать, и на сколько символов надо сдвигать
    String cipher(String message, int offset) {
        // объект для шифрованной строки
        StringBuilder result = new StringBuilder();
        // проходим по символу в строке
        for (char letter : message.toCharArray()) {
            // если это буква, то изменяем её
            if (Alphabet.isLetter(letter)) {
                // получаем номер буквы в алфавите
                int originalAlphabetPosition = Alphabet.getAlphabetPosition(letter);
                // считаем новую позицию буквы со сдвигом относительно начала алфавита
                int newAlphabetPosition = (originalAlphabetPosition + offset) % Alphabet.alphabet.length;
                // получаем букву, находящуюся на новой позиции
                char newCharacter = Alphabet.getLetterOnPosition(newAlphabetPosition);
                // добавляем букву в шифрованную строку
                result.append(newCharacter);
            } else {
                // добавляем символ в шифрованную строку
                result.append(letter);
            }
        }
        // возвращаем полученную шифрованную строку
        return result.toString();
    }
}
