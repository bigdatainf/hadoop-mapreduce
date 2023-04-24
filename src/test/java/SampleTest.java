import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SampleTest {

    public static void main(String[] args) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("/USers/cbadenes/Downloads/data.json"));
            String line = reader.readLine();

            while (line != null) {
                System.out.println(line);
                JSONObject jsonObject = new JSONObject(line);
                if (jsonObject.has("genres")){
                    JSONArray genres = jsonObject.getJSONArray("genres");

                    for (int i=0, size = genres.length(); i < size; i++){
                        JSONObject genreObject = genres.getJSONObject(i);
                        String genre = genreObject.getString("name");
                        System.out.println(genre);
                    }
                }

                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
