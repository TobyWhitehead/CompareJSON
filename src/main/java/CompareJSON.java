import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public record CompareJSON(JSONObject jsonObject1, JSONObject jsonObject2) {

    public HashMap<String, List<String>> checkKeys() {
        HashMap<String, List<String>> myMap = new HashMap<>();
        List<String> missingKeys1 = new ArrayList<>();
        List<String> missingKeys2 = new ArrayList<>();
        for (String key : jsonObject1.keySet()) {
            if (!jsonObject2.has(key)) {
                missingKeys1.add(key);
            }
        }
        for (String key : jsonObject2.keySet()) {
            if (!jsonObject1.has(key)) {
                missingKeys2.add(key);
            }
        }
        myMap.put("json1 missing keys", missingKeys1);
        myMap.put("json2 missing keys", missingKeys2);
        return myMap;
    }

    public HashMap<String, Object[]> checkValues() {
        HashMap<String, Object[]> myMap = new HashMap<>();
        for (String key : jsonObject1.keySet()) {
            if (jsonObject2.has(key) && !jsonObject1.get(key).equals(jsonObject2.get(key))) {
                Object[] differenceArray = {jsonObject1.get(key), jsonObject2.get(key)};
                myMap.put(key, differenceArray);
            }
        }
        return myMap;
    }

    public void printResults() {
        HashMap<String, List<String>> missingKeys = checkKeys();
        for (String key : missingKeys.keySet()) {
            System.out.println("\n" + key + ":");
            for (String value : missingKeys.get(key)) {
                System.out.println("\u2022" + value);
            }
        }
        HashMap<String, Object[]> differentValues = checkValues();
        for (String key : differentValues.keySet()) {
            System.out.println("\nDifference in \"" + key + "\"\nExpected: \"" + differentValues.get(key)[0]
                    + "\" But was: \"" + differentValues.get(key)[1] + "\"");
        }
    }
}
