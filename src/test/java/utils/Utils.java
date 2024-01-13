package utils;

import config.EmpModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void saveUsers(EmpModel model) throws IOException, ParseException {
        String fileLocation= "./src/test/resources/employee.json";
        JSONParser parser= new JSONParser();
        JSONArray empArray= (JSONArray) parser.parse(new FileReader(fileLocation));

        JSONObject empObj = new JSONObject();
        empObj.put("firstName",model.getFirstName());
        empObj.put("lastName",model.getLastName());
        empObj.put("username",model.getUsername());
        empObj.put("password",model.getPassword());
        empObj.put("employeeId",model.getEmployeeId());

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
