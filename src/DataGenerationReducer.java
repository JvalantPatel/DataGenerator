/**
 * Created by jp on 5/12/16.
 */
import java.io.*;

public class DataGenerationReducer {

    public static void main(String[] args) throws IOException{

        String content = "This is the content to write into file";

        File file = new File("out.txt");

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        File folder = new File("DATA_TEST");
        File[] listOfFiles = folder.listFiles();

        for (File file1 : listOfFiles) {
            if (file1.isFile()) {

                FileInputStream fstream = new FileInputStream(file1.getPath());
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String strLine;
                while ((strLine = br.readLine()) != null)   {
                    bw.write(strLine);
                    bw.write("\n");
                }
            }
        }

        bw.close();

        System.out.println("Done");

    }
}
