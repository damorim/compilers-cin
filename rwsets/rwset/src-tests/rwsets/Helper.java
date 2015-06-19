package rwsets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Helper {
  
  public static String readFile(String fileName) throws FileNotFoundException, IOException {
    StringBuffer sb = new StringBuffer();
    FileReader fr = new FileReader(new File(fileName));
    BufferedReader br = new BufferedReader(fr);
    String tmp;
    while ((tmp = br.readLine())!=null) {
      sb.append(tmp);
      sb.append("\n");
    }
    br.close();
    fr.close();
    return sb.toString();
  }
  



  
}
