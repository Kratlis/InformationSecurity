package lab2_closed_key;

import lab2_closed_key.utils.CipherParams;

import java.util.Arrays;

public class CypherGOST {
    private final byte[][] sBlock;

    // Задаётся таблица замен по умолчанию (из класса CipherParams)
    public CypherGOST() {
        sBlock = CipherParams.block1;
    }

    public byte[] encrypt(byte[] dataToEncrypt, int[] keys) {
        // создаём массив для результата
        byte[] output = Arrays.copyOf(dataToEncrypt, dataToEncrypt.length);

        // проходим по байтам блока текста
        for (int i = 0; i <= dataToEncrypt.length - 8; i += 8) {
            // шифруем первые 3 цикла с элементами ключа в прямом порядке
            for (int q = 0; q < 3; q++) {
                // шифруем с каждым элементом ключа
                for (int w = 0; w < 8; w++) {
                    int key = keys[w];
                    step(output, key);
                }
            }
            // шифруем поседний цикл с элементами ключа в обратном порядке
            for (int w = 7; w >= 0; w--) {
                int key = keys[w];
                step(output, key);
                if (w == 0) {
                    byte[] lastArray2 = new byte[8];
                    System.arraycopy(output, output.length - i - 8, lastArray2, 4, 4);
                    System.arraycopy(output, output.length - i - 4, lastArray2, 0, 4);
                    System.arraycopy(lastArray2, 0, output, output.length - i - 8, 8);
                }
            }
        }

        return output;
    }

    public void step(byte[] block, int key) {
        // левая половина (32 бита) блока (64 бит)
        int L = block[block.length  - 1] << 24 |
                (block[block.length  - 2] & 0xFF) << 16 |
                (block[block.length  - 3] & 0xFF) << 8 |
                (block[block.length  - 4] & 0xFF);

        // правая половина (32 бита) блока (64 бит)
        int R = block[block.length - 5] << 24 |
                (block[block.length  - 6] & 0xFF) << 16 |
                (block[block.length  - 7] & 0xFF) << 8 |
                (block[block.length  - 8] & 0xFF);

        // сложение по модулю 2^32 правого блока с элементом ключа
        int tempRes = R + key;

        // замена результата в соответствии с таблицей замен
        tempRes = replace(tempRes);

        // циклический сдвиг на 11 бит влево
        tempRes = shiftLeft11(tempRes);

        // сложение по модулю 2 результата с левой половиной блока
        tempRes = tempRes ^ L;

        // получение целого блока
        byte[] resBytes = join(R, tempRes);

        // запись в переданный массив
        for (int j = 7; j >= 0; j--) {
            block[block.length - 1 - (j)] = resBytes[j];
        }
    }

    private byte[] join(int R, int shiftedBlock) {
        byte[] block = new byte[8];

        // замена левой части блока на правую
        for (int j = 0; j < 4; j++) {
            block[j] = (byte) ((R >> 24 - (j * 8)) & 0xFF);
        }
        // добавление остальной части
        for (int j = 4; j < 8; j++) {
            block[j] = (byte) ((shiftedBlock >> 24 - (j * 8)) & 0xFF);
        }
        return block;
    }

    // функция замены
    private int replace(int n) {
        int xTest = 0;
        // получение элемента из таблицы замен
        for (int i = 0, j = 0; i <= 28; i += 4, j++) {
            xTest += (sBlock[j][(byte) ((n >> i) & 0xF)]) << (i);
        }
        return xTest;
    }

    private int shiftLeft11(int a) {
        int shift = 11;
        a = (a >>> (32 - shift)) | a << shift;
        return a;
    }
}
