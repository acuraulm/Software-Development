package ro.axonsoft.internship.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import ro.axonsoft.internship.api.HotelDescriptor;
import ro.axonsoft.internship.api.HotelsReader;
import ro.axonsoft.internship.impl.models.DataCreator;
import ro.axonsoft.internship.impl.validators.HotelReaderValidator;
import ro.axonsoft.internship.impl.validators.Validator;

public class HotelsReaderImpl implements HotelsReader {

	@Override
	public List<HotelDescriptor> readFile(String filename) {
		List<HotelDescriptor> hotelsList = new ArrayList<>();
		try {
			String content = new String(Files.readAllBytes(Paths.get(filename)));
			List<String> lines = Pattern.compile("\n").splitAsStream(content).collect(Collectors.toList());
			
			for (String line : lines) {
				HotelDescriptor hotelDescriptor = readLine(line);
				if(hotelDescriptor!=null)
					hotelsList.add(hotelDescriptor);
			}	
		} catch (IOException | ReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hotelsList;
		
		
	}

	@Override
	public HotelDescriptor readLine(String line) throws ReaderException {
		String[] attributes = line.split(";");
		Validator<String[]> validator = new HotelReaderValidator();
		if(validator.validate(attributes))
			return DataCreator.createHotel(attributes);
		else
			throw new ReaderException("Invalid hotel data input");
	}

}
