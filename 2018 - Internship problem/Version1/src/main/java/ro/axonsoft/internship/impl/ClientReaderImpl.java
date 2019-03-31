package ro.axonsoft.internship.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ro.axonsoft.internship.api.ClientDescriptor;
import ro.axonsoft.internship.api.ClientReader;
import ro.axonsoft.internship.api.DecimalCoordinates;

public class ClientReaderImpl implements ClientReader {

	public List<ClientDescriptor> readFile(String fileName) throws IOException {
		List<String> linesRead = new ArrayList<>();
		List<ClientDescriptor> clientsList = new ArrayList<>();

		Stream<String> readStream = Files.lines(Paths.get(fileName));
		linesRead = readStream.collect(Collectors.toList());
		readStream.close();

		for (String line : linesRead) {
			clientsList.add(readLine(line));
		}

		return clientsList;
	}

	public ClientDescriptor readLine(String line) {
		ClientDescriptor clientDescriptor = new ClientDescriptorImpl();
		List<String> clientAttributes = new ArrayList<>();
		DecimalCoordinates clientCoordinates = new DecimalCoordinatesImpl();

		clientAttributes = Arrays.stream(line.split(";")).map(e -> new String(e)).collect(Collectors.toList());

		clientCoordinates.setLatitude(Double.parseDouble(clientAttributes.get(1)));
		clientCoordinates.setLongitude(Double.parseDouble(clientAttributes.get(2)));

		clientDescriptor.setName(clientAttributes.get(0));
		clientDescriptor.setCoordinates(clientCoordinates);
		clientDescriptor.setRadius(Integer.parseInt(clientAttributes.get(3)));

		return clientDescriptor;
	}

}
