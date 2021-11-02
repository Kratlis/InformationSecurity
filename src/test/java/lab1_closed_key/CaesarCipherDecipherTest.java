package lab1_closed_key;

import lab1_closed_key.utils.InitProperties;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Locale;

public class CaesarCipherDecipherTest {

    private final int offset = InitProperties.OFFSET;

    private final String originalText = InitProperties.ORIGINAL_TEXT;

    private final String expectedText = InitProperties.EXPECTED_TEXT;

    private String cipheredText;

    @Test
    public void testCipher() {
        cipheredText = new CaesarCipher().cipher(originalText.toLowerCase(Locale.ROOT), offset);

        System.out.println("Original: \n" + originalText);
        System.out.println("Ciphered: \n" + cipheredText);

        Assertions.assertThat(cipheredText)
                .isEqualToIgnoringCase(expectedText);
    }

    @Test
    public void testDecipher() {
        System.out.println(new FrequencyAnalysisDecipher(cipheredText));

        System.out.println("Ciphered: \n" + cipheredText);

        String decipheredText = new CaesarDecipher().decipher(cipheredText, offset);
        System.out.println("Deciphered: \n" + decipheredText);

        Assertions.assertThat(decipheredText)
                .isEqualToIgnoringCase(originalText);
    }
}
