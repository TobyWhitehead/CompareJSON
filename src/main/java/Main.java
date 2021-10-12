import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
        String json1 = "{\n\t\"red\":\"2\",\n\t\"green\":\"3\",\n\t\"orange\":\"4\"\n}";
        String json2 = "{\n\t\"red\":\"2\",\n\t\"green\":\"5\",\n\t\"blue\":\"4\"\n}";
        JSONObject JSON1 = new JSONObject(json1);
        JSONObject JSON2 = new JSONObject(json2);
        CompareJSON myCompareJSON = new CompareJSON(JSON1, JSON2);
        myCompareJSON.printResults();
    }
}
