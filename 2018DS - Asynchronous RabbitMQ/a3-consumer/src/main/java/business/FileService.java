package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {

	public void writeFile(String name, String content) throws IOException{
		 BufferedWriter writer = new BufferedWriter(new FileWriter(name + ".txt"));
		    writer.write(content);			     
		    writer.close();
		    System.out.println("Wrote into a file: " + content);
	}
}
