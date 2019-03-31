package ro.axonsoft.internship.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.api.Writer;

public class WriterImpl implements Writer {

	@Override
	public void writeResult(List<SearchResult> results) {
		for (SearchResult result : results) {
			System.out.println(result.toString());
			try {
				writeToFile(result, "src/main/resources/" + result.getClient().getName() + ".txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private static void writeToFile(Object obj, String fileName) throws FileNotFoundException, IOException{
		File file = new File(fileName);
		String content = obj.toString();
		try (FileOutputStream fop = new FileOutputStream(file)) {
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		}
	}

}
