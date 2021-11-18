package lab2_closed_key;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import static lab2_closed_key.utils.TextArraysConverter.*;

public class CypherOFB {

    private final CypherGOST cypher;

    // вектор инициализации (64 бит)
    private final byte[] initialVector;

    public CypherOFB(CypherGOST cypher) {
        this.cypher = cypher;
        // генерация случайных 8 байт
        initialVector = new SecureRandom().generateSeed(8);
    }

    public byte[] encrypt(String inputText, String key) {
        // получим массив байтов из входящей строки
        byte[][] originalTextBlocks = splitTextIntoBlocks(inputText);
        // создадим массив для результата
        byte[][] encryptedTextBlocks = originalTextBlocks.clone();
        // получим ключи для шагов шифрования
        int[] keys = createStepsKeys(key);
        // первым будем шифровать блок инициализации
        byte[] nextBlockToEncrypt = initialVector;

        // шифруем блоки
        for (int i = 0; i < originalTextBlocks.length; i++) {
            // шифруем блок
            nextBlockToEncrypt = cypher.encrypt(nextBlockToEncrypt, keys);
            // складываем результат шифрования по модулю 2 с оригинальным текстом
            encryptedTextBlocks[i] = xor(nextBlockToEncrypt, originalTextBlocks[i]);
        }

        // собираем двумерный массив в одномерный
        return mergeDoubleArray(encryptedTextBlocks);
    }

    public String decrypt(byte[] encryptedText, String key) {
        // получим массив байтов из зашифрованной строки
        byte[][] encryptedTextBlocks = splitArrayIntoDoubleArray(encryptedText);
        // получим ключи для шагов шифрования
        byte[][] decryptedTextBlocks = encryptedTextBlocks.clone();
        // получим ключи для шагов шифрования
        int[] keys = createStepsKeys(key);
        // первым будем шифровать блок инициализации
        byte[] nextBlockToDecrypt = initialVector;

        // дешифруем блоки
        for (int i = 0; i < encryptedTextBlocks.length; i++) {
            // шифруем блок
            nextBlockToDecrypt = cypher.encrypt(nextBlockToDecrypt, keys);
            // складываем результат шифрования по модулю 2 с зашифрованным текстом
            decryptedTextBlocks[i] = xor(nextBlockToDecrypt, encryptedTextBlocks[i]);
        }

        // возвращаем строку с результатом дешифрации
        return new String(mergeDoubleArray(decryptedTextBlocks));
    }

    // функция для сложения по модулю 2 двух массивов байтов
    private byte[] xor(byte[] bytes1, byte[] bytes2) {
        byte[] result = new byte[bytes1.length];

        int i = 0;
        for (byte b : bytes1) {
            result[i] = (byte) (b ^ bytes2[i++]);
        }
        return result;
    }

    // разделяем строку ключей на шаги
    private int[] createStepsKeys(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        // ключ должен быть длиной 256 бита
        if (keyBytes.length != 32) {
            throw new IllegalArgumentException(String.format(
                    "Incorrect length of key. Key must be 256 bits long, but its current length is %d."
                    , keyBytes.length * 8));
        }
        // получаем из массива байт массив целых чисел
        return convertBytesToInts(keyBytes);
    }
}
