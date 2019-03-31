package ro.axonsoft.internship.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ro.axonsoft.internship.api.ClientDescriptor;
import ro.axonsoft.internship.api.ClientReader;
import ro.axonsoft.internship.impl.models.DataCreator;
import ro.axonsoft.internship.impl.validators.ClientReaderValidator;
import ro.axonsoft.internship.impl.validators.Validator;
public class ClientReaderImpl implements ClientReader {

	@Override
	public List<ClientDescriptor> readFile(String filename) {
		List<String> linesRead = new ArrayList<>();
		List<ClientDescriptor> clientsList = new ArrayList<>();

		Stream<String> readStream;
		try {
			readStream = Files.lines(Paths.get(filename));
			linesRead = readStream.collect(Collectors.toList());
			readStream.close();

			for (String line : linesRead) {
				ClientDescriptor clientDescriptor = readLine(line);
				if(clientDescriptor!=null)
					clientsList.add(clientDescriptor);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return clientsList;
	}

	@Override
	public ClientDescriptor readLine(String line) throws ReaderException {
		String[] attributes = line.split(";");
		Validator<String[]> validator = new ClientReaderValidator();
		if(validator.validate(attributes))
			return DataCreator.createClient(attributes);
		else
			throw new ReaderException("Invalid client data input");
		
	}

}
