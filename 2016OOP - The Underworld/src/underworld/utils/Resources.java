package underworld.utils;
import java.util.Random;

public class Resources {
	private static Random random = new Random();
	private static int randomInitialized = 0 ;	
	public static int generateValuableResource(){
		randomInitialized = random.nextInt(1 + Constants.MAP_RESOURCES_NUMBER);
		return randomInitialized;
	}
	public static int generateResource(){
		randomInitialized = random.nextInt(1 + (Constants.MAP_RESOURCES_NUMBER*2));
		return randomInitialized;
	}	

}
