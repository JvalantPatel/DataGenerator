import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DataCombinerMapper extends Mapper<LongWritable, Text, Text, Text> {

	Map<String, String> map = new HashMap<String,String>();
	   public void setup(Context context) throws IOException, InterruptedException{

		   Path[] uris = DistributedCache.getLocalCacheFiles(context.getConfiguration());

		   BufferedReader bufferedReader = new BufferedReader(new FileReader(uris[0].toString()));
		   String line = null;
		   while((line = bufferedReader.readLine()) != null) {
			   String[] input = line.split(",");
			   if(input.length==3){
				   String value = new StringBuffer().append(input[1]).append("|").append(input[2]).toString();
				   map.put(input[0],value);
			   }
			   else if (input.length>3){
				   String movieId = input[0];
				   String year = input[1];
				   String movieName = "";
				   StringBuffer sb = new StringBuffer();
				   for(int i=2;i<input.length-1;i++){
					   sb.append(input[i]).append(",");
				   }
				   movieName = sb.append(input[input.length-1]).toString();
				   String value = new StringBuffer().append(year).append("|").append(movieName).toString();
				   map.put(movieId,value);
			   }
		   }
	   }
	   public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		   String movie_id = value.toString().split(",")[0];
		   String datakey = new StringBuffer().append(value.toString().replaceAll(",","|")).
				                 append("|").append(map.get(movie_id)).toString();
		   String datavalue = "";
		   context.write(new Text(datakey),new Text(datavalue));
	      }
   }
