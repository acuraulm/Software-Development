package acu.assignment2;

import java.util.ArrayList;
import java.util.List;

import acu.project1.persistence.entities.Teacher;

public class GetAdmins {

	public static List<Teacher> getAdminstrators(){
		/* administratorService.addAdministrator("Default Administrator", "Default Administrator");
	        administratorService.addAdministrator("popoct", "Pop Octavian");
	        administratorService.addAdministrator("joldik", "Marius Joldos");
	        administratorService.addAdministrator("altcineva", "Profesor DeCalitate");
	        administratorService.addAdministrator("ent", "Eneia Todoran");*/
	     List<Teacher> toReturn = new ArrayList<>();
	     Teacher a1 = new Teacher();
	     a1.setName("Default Administrator");
	     a1.setUsername("Default Administrator");
	     toReturn.add(a1);
	     a1 = new Teacher();
	     a1.setName("Pop Octavian");
	     a1.setUsername("popoct");
	     toReturn.add(a1);
	     a1 = new Teacher();
	     a1.setName("Marius Joldos");
	     a1.setUsername("joldik");
	     toReturn.add(a1);
	     a1 = new Teacher();
	     a1.setName("Profesor DeCalitate");
	     a1.setUsername("altcineva");
	     toReturn.add(a1);
	     a1 = new Teacher();
	     a1.setName("Eneia Todoran");
	     a1.setUsername("ent");
	     toReturn.add(a1);
	     
		return toReturn;
	     
	}
}
