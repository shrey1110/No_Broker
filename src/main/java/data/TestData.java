package Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {

    List<String> locality = new ArrayList<String>();
    List<String> apartment = new ArrayList<String>();
    String city;


    public TestData() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\Data\\Data.json");
        Object obj = jsonParser.parse(reader);
        {
            JSONObject details = (JSONObject) obj;
            System.out.println(details);
            JSONArray localities = (JSONArray) details.get("locality");
            JSONArray flat = (JSONArray) details.get("apartment");
            for(int i=0;i<localities.size();i++){
                locality.add(localities.get(i).toString());
            }
            city =  details.get("city").toString();
            for(int i=0;i<flat.size();i++){
                apartment.add(flat.get(i).toString());
            }
        }
    }

    public  String[] getLocality(){
        Object ob[]= locality.toArray();
        String[] str = Arrays
                .copyOf(ob, ob
                                .length,
                        String[].class);
        return str;
    }

    public String getCity(){
        return city;
    }

    public String[] getApartment(){

        Object ob[]= apartment.toArray();
        String[] str = Arrays
                .copyOf(ob, ob
                                .length,
                        String[].class);

        return str;
    }

}
