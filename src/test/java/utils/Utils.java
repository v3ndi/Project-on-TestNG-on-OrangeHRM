package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void saveUsers(String firstName, String lastName, String username, String password) throws IOException, ParseException {
        String fileLocation= "./src/test/resources/employee.json";
        JSONParser parser= new JSONParser();
        JSONArray empArray= (JSONArray) parser.parse(new FileReader(fileLocation));

        JSONObject empObj = new JSONObject();
        empObj.put("firstName",firstName);
        empObj.put("lastName",lastName);
        empObj.put("username",username);
        empObj.put("password",password);

        empArray.add(empObj);
        FileWriter writer=new FileWriter(fileLocation);
        writer.write(empArray.toJSONString());
        writer.flush();
        writer.close();

    }
    public static JSONObject getUserFromJsonArray () throws IOException, ParseException {
        String fileLocation= "./src/test/resources/employee.json";
        JSONParser parser= new JSONParser();
        JSONArray empArray= (JSONArray) parser.parse(new FileReader(fileLocation));

        return (JSONObject) empArray.get(empArray.size()-1);

    }
}
