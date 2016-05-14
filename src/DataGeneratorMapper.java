import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class DataGeneratorMapper extends Mapper<LongWritable, Text, Text, Text> {
      // TODO define class variables for translation, language, and file name

      private String currentMovieId = "";
      StringBuffer sb = new StringBuffer();
      public void setup(Context context) {    	   
      // TODO determine the language of the current file by looking at it's name
             String currentFileName = ((FileSplit) context.getInputSplit()).getPath().getName();
             if(currentFileName.contains(".")){
                 currentFileName = currentFileName.split("\\.")[0];
             }
             currentMovieId = Integer.valueOf(currentFileName.substring(3,currentFileName.length())).toString();

      }

      public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
      // TODO instantiate a tokenizer based on a regex for the file

            if(!value.toString().contains(":")){
                  String[] input = value.toString().split(",");
                  if(input.length==3){
                        sb.setLength(0);
                        sb.append(currentMovieId).append("|").append(value.toString().replaceAll(",","|"));
                        String mapperOutKey = sb.toString();
                        context.write(new Text(mapperOutKey), new Text(" "));
                  }
            }
      }
}
