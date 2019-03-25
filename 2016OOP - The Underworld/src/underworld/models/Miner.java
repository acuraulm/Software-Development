package underworld.models;
import underworld.utils.Resources;

public class Miner {
	public String name;
	public int[] backpack;
	public int backpackIndex = 0;
	
	public Miner(String name){
		this.name = name;
		backpack = new int[10];
		for(int i = 0; i < 10; i++)
			backpack[i] = 0;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
	}
	public int dig(){
		int generatedRes = Resources.generateResource();
		if (backpackIndex == 5){
			System.out.println("Backpack is full. Your job here is done!");
		}
		else{
			backpack[backpackIndex] = generatedRes;
		}
		return backpack[backpackIndex];
	}
	public int sleep(){
		int generatedRes = Resources.generateResource();
		return generatedRes;
	}
	
	public void showBackpack(){	
		System.out.print("Resources in backpack: ");
		for(int i = 0; i < 10; i++)
			System.out.print(backpack[i]);
		getNumberOfBackpackSlots();
	}
	
	private int getNumberOfBackpackSlots(){
		int counter = 10;
		for(int i = 0; i < 10; i++){
			if(backpack[i]==0)
				counter--;
		}
		return counter;
	}
}
