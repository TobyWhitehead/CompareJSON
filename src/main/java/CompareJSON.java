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

    public HashMap<String, String[]> checkValues() {
        HashMap<String, String[]> myMap = new HashMap<>();
        for (String key : jsonObject1.keySet()) {
            if (jsonObject2.has(key) && !jsonObject1.getString(key).equals(jsonObject2.getString(key))) {
                String[] differenceArray = {jsonObject1.getString(key), jsonObject2.getString(key)};
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
        HashMap<String, String[]> differentValues = checkValues();
        for (String key : differentValues.keySet()) {
            System.out.println("\nDifference in \"" + key + "\"\nExpected: \"" + differentValues.get(key)[0]
                    + "\" But was: \"" + differentValues.get(key)[1] + "\"");
        }
    }
}
