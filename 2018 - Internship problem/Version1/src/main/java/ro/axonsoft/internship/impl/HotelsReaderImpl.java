package ro.axonsoft.internship.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
//import java.util.stream.Stream;

import ro.axonsoft.internship.api.DecimalCoordinates;
import ro.axonsoft.internship.api.HotelDescriptor;
import ro.axonsoft.internship.api.HotelsReader;
import ro.axonsoft.internship.api.RoomDescriptor;
import ro.axonsoft.internship.builders.CoordinatesBuilder;
import ro.axonsoft.internship.builders.HotelBuilder;
import ro.axonsoft.internship.builders.RoomBuilder;

public class HotelsReaderImpl implements HotelsReader {

	public List<HotelDescriptor> readFile(String fileName) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(fileName)));
		/**
		 * One way of reading lines from a file, by splitting over newline String[]
		 * lines = content.split("\\n");
		 */
		List<String> lines = Pattern.compile("\n").splitAsStream(content).collect(Collectors.toList());
		List<HotelDescriptor> hotelsList = new ArrayList<>();

		for (String line : lines) {
			hotelsList.add(readLine(line));
		}
		return hotelsList;
	}

	public HotelDescriptor readLine(String line) {
		/**
		 * Alternative way to split the line by ";" Stream<String> hotelAttributes;
		 * hotelAttributes = Pattern.compile(";").splitAsStream(line); List<String>
		 * hotelAttributesString = hotelAttributes.collect(Collectors.toList());
		 */
		String[] hotelAttributes = line.split(";");
		//for (int i = 0; i < hotelAttributes.length; i++) {
		//	System.out.println(hotelAttributes[i]);
		//}
			/**
			 * Without using Builder DecimalCoordinates hotelCoordinates = new
			 * DecimalCoordinatesImpl();
			 * hotelCoordinates.setLatitude(Double.parseDouble(hotelAttributes[2]));
			 * hotelCoordinates.setLongitude(Double.parseDouble(hotelAttributes[3]));
			 */
			DecimalCoordinates hotelCoordinates = new CoordinatesBuilder()
					.setLatitude(Double.parseDouble(hotelAttributes[2]))
					.setLongitude(Double.parseDouble(hotelAttributes[3])).build();

			List<RoomDescriptor> availableRooms = new ArrayList<>();
			
			if(hotelAttributes.length >= 6) {
			  availableRooms.add(new
			  RoomBuilder().setNumber(Integer.parseInt(hotelAttributes[4]))
			  .setPrice(Double.parseDouble(hotelAttributes[5])).setType(RoomType.SINGLE).
			  build());
			  if(hotelAttributes.length >= 8) {
				  availableRooms.add(new
				  RoomBuilder().setNumber(Integer.parseInt(hotelAttributes[6]))
				  .setPrice(Double.parseDouble(hotelAttributes[7])).setType(RoomType.DOUBLE).
				  build());
				  if(hotelAttributes.length >= 10) {
					  availableRooms.add(new
					  RoomBuilder().setNumber(Integer.parseInt(hotelAttributes[8]))
					  .setPrice(Double.parseDouble(hotelAttributes[9])).setType(RoomType.SUITE).
					  build());
				  }
			  }
			}
			return new HotelBuilder().setName(hotelAttributes[0]).setAddress(hotelAttributes[1])
					.setCoordinates(hotelCoordinates).setAvailableRooms(availableRooms).build();
/*
			return new HotelBuilder().setName("Invalid hotel")
					.setAddress("Invalid address")
					.setCoordinates(new CoordinatesBuilder().setLatitude(0.0).setLongitude(0.0).build())
					.setAvailableRooms(new ArrayList<>())
					.build();
		}
*/
	}

}
