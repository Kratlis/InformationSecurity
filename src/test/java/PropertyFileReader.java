import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertyFileReader {

    private static Properties properties;

    public static HashMap<Character, Integer> readProperty(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            properties = new Properties();

            // load a properties file
            properties.load(fileReader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        HashMap<Character, Integer> res = new HashMap<>();
        for (Entry<Object, Object> pr : properties.entrySet()) {
            res.put((Character) pr.getKey(), (Integer) pr.getValue());
        }
        return res;
    }
}
