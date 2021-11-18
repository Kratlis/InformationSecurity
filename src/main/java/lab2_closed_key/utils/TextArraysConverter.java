package lab2_closed_key.utils;

import java.nio.charset.StandardCharsets;

public class TextArraysConverter {

    public static byte[][] splitTextIntoBlocks(String text) {
        int remainder = text.length() % 16;
        if (remainder != 0) {
            for (int i = 0; i < 16 - remainder; i++) {
                text = text.concat(" ");
            }
        }

        byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);

        return splitArrayIntoDoubleArray(textBytes);
    }

    public static byte[][] splitArrayIntoDoubleArray(byte[] bytes) {
        byte[][] textRes = new byte[bytes.length / 8][8];

        for (int i = 0; i < textRes.length; i++) {
            System.arraycopy(bytes, i * 8, textRes[i], 0, textRes[i].length);
        }

        return textRes;
    }

    public static byte[] mergeDoubleArray(byte[][] array) {
        byte[] bytes = new byte[array.length * array[0].length];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = array[i / 8][i % 8];
        }
        return bytes;
    }

    public static int[] convertBytesToInts(byte[] bytes) {
        int[] ints = new int[bytes.length / 4];

        for (int i = 0; i < ints.length; i++) {
            ints[i] = bytes[i * 4 + 3] << 24 |
                    (bytes[i * 4 + 2] & 0xFF) << 16 |
                    (bytes[i * 4 + 1] & 0xFF) << 8 |
                    (bytes[i * 4] & 0xFF);
        }

        return ints;
    }
}
