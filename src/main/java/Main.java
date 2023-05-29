import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        String json = readString("data.json");
        List<Employee> employees = jsonToList(json);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public static String readString(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Employee> jsonToList(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(json);

        List<Employee> employees = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            Employee employee = gson.fromJson(jsonObject.toString(), Employee.class);
            employees.add(employee);
        }

        return employees;
    }
}
