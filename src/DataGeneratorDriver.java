import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DataGeneratorDriver extends Configured implements Tool {
   public static void main(String[] args) throws Exception {
      Configuration conf = new Configuration();
      System.exit(ToolRunner.run(conf, new DataGeneratorDriver(), args));
   }
   
   public int run(String[] args) throws Exception {
      Job job = new Job(getConf(), "Data Generator");
      job.setJarByClass(DataGeneratorDriver.class);
      job.setMapperClass(DataGeneratorMapper.class);
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(Text.class);
      job.setInputFormatClass(TextInputFormat.class);
      job.setNumReduceTasks(0); // to see the mapper output
      FileInputFormat.addInputPath(job, new Path(args[0]));
      FileOutputFormat.setOutputPath(job, new Path(args[1]));
      return job.waitForCompletion(true) ? 0:1;
   }
}

