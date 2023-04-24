package org.bigdatainf;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;
import org.json.JSONObject;
import org.json.JSONArray;
public class TMDB_Mapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String jsonString = value.toString();
        JSONObject jsonObject = new JSONObject(jsonString);
        if (jsonObject.has("genres")){
            JSONArray genres = jsonObject.getJSONArray("genres");

            for (int i=0, size = genres.length(); i < size; i++){
                JSONObject genreObject = genres.getJSONObject(i);
                word.set(genreObject.getString("name"));
                context.write(word, one);
            }
        }
    }
}