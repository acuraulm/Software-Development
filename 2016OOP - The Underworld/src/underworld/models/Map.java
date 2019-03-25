package underworld.models;
import underworld.utils.Resources;
public class Map {
	private int[] resources;
	public Map(){
		resources = new int[10];
		for(int i = 0; i < 10; i++)
			resources[i] = 0;
	}
	public void generateResources(){
		for(int i = 0; i < 10; i++)
			resources[i] = Resources.generateValuableResource();
	}
	
	public boolean consumeResource(int resource){
		boolean checkResource = false;
		for (int i = 0; i < 10; i++){
			if (resources[i] == resource){
				resources[i]= -1;
				checkResource = true;
			}
			else 
				checkResource = false;
		}
		return checkResource;
	}
	
	public void ShowResources(){
		for (int i = 0; i < 10; i++){
			System.out.print(resources[i] + ", ");
		}
	}
}