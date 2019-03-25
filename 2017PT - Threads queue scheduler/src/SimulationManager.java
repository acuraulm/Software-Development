
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable {
	private int timeLimit;
	private int minArrivalTime;
	private int maxArrivalTime;
	private int minProcessingTime;
	private int maxProcessingTime;
	private int numberOfServers;
	private int numberOfClients;
	public SelectionPolicy seletionPolicy = SelectionPolicy.SHORTEST_TIME;
	private static Random rn = new Random();
    private  Scheduler scheduler;
	static List<Server> servers;
	private List<Task> generatedTasks;
	public static int currentTime;
	public static SimulationFrame sim;
	public static int averageWaitingSum = 0;
	
	public SimulationManager(int timeLimit,int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime, int numberOfServers,int numberOfClients, SelectionPolicy seletionPolicy) {
		super();
		this.timeLimit = timeLimit;
		this.maxArrivalTime=maxArrivalTime;
		this.minArrivalTime=minArrivalTime;
		this.maxProcessingTime=maxProcessingTime;
		this.minProcessingTime=minProcessingTime;
		this.numberOfServers = numberOfServers;
		this.numberOfClients = numberOfClients;
		this.scheduler = new Scheduler(numberOfServers);
		this.scheduler.changeStrategy(seletionPolicy);
		SimulationManager.setServers(new ArrayList<Server>());
		for(int i=0; i< numberOfServers;i++)
			SimulationManager.getServers().add(new Server());
		this.generatedTasks = new ArrayList<Task>();
		for(int i=0;i<numberOfClients;i++){
			int randomA = rn.nextInt(maxArrivalTime - minArrivalTime) + 1; // arrivalTime random
			int randomP = rn.nextInt(maxProcessingTime - minProcessingTime) + 1; // processingTime random
			 this.generatedTasks.add(new Task(randomA,randomP,i));
		}
	}

	public static int getCurrentTime(){
		return currentTime;
	}
	
	@Override
	public void run() {
		currentTime = 1;
		for(int i=0; i<generatedTasks.size();i++){
			sim.txtrLogs.setText(sim.txtrLogs.getText() + "\n" + generatedTasks.get(i).toString()+"(" + generatedTasks.get(i).getArrivalTime() + "," + generatedTasks.get(i).getProcessingTime()+ ")");
			averageWaitingSum+=generatedTasks.get(i).getProcessingTime();
		}
		averageWaitingSum = averageWaitingSum/generatedTasks.size();
		while(currentTime < timeLimit)
		{
		//	if(currentTime<10)
		//	sim.label.setText("00:0" + currentTime);
		//	if(currentTime>9)
		//		sim.label.setText("00:" + currentTime);
			for(int i=0;i<numberOfServers;i++)
				(new Thread(servers.get(i))).start();
			if(currentTime<10)
			sim.txtrLogs.setText(sim.txtrLogs.getText() +"\n-----------------Time: 00:0" + currentTime + "-----------------");
			if(currentTime>9)
				sim.txtrLogs.setText(sim.txtrLogs.getText() +"\n-----------------Time: 00:" + currentTime + "-----------------");
			for(int i=0;i<numberOfClients;i++){
				if(currentTime == generatedTasks.get(i).getArrivalTime()){
						generatedTasks.get(i).setEnqueueTime(currentTime);
						scheduler.dispatchTask(generatedTasks.get(i));		
						sim.txtrLogs.setText(sim.txtrLogs.getText() + "\n" + generatedTasks.get(i).toString()+" arrived" + "\t");

			    }
			}
			sim.txtrLogs.setText(sim.txtrLogs.getText()+ "\n");
			for(int i=0; i<numberOfServers;i++){
				if(getServers().get(i).getSize() == 0){
					sim.txtrLogs.setText(sim.txtrLogs.getText() + "\n" + "Server" + (i+1) + " is empty");
					}
				else{
				sim.txtrLogs.setText(sim.txtrLogs.getText() + "\n" + "Server" + (i+1) + ": " + getServers().get(i).toString() + " Total processing time: " + getServers().get(i).getWaitingPeriod());
				}
			if(getServers().size()==1){
				sim.textArea.setText(getServers().get(0).toString()+ " Total processing time: " + getServers().get(0).getWaitingPeriod());
			}if(getServers().size()==2){
				sim.textArea.setText(getServers().get(0).toString()+ " Total processing time: " + getServers().get(0).getWaitingPeriod());
				sim.textArea_1.setText(getServers().get(1).toString()+ " Total processing time: " + getServers().get(1).getWaitingPeriod());
				}if(getServers().size()==3){
				sim.textArea.setText(getServers().get(0).toString()+ " Total processing time: " + getServers().get(0).getWaitingPeriod());
				sim.textArea_1.setText(getServers().get(1).toString()+ " Total processing time: " + getServers().get(1).getWaitingPeriod());
				sim.textArea_2.setText(getServers().get(2).toString()+ " Total processing time: " + getServers().get(2).getWaitingPeriod());
				}if(getServers().size()==4){
				sim.textArea.setText(getServers().get(0).toString()+ " Total processing time: " + getServers().get(0).getWaitingPeriod());
				sim.textArea_1.setText(getServers().get(1).toString()+ " Total processing time: " + getServers().get(1).getWaitingPeriod());
				sim.textArea_2.setText(getServers().get(2).toString()+ " Total processing time: " + getServers().get(2).getWaitingPeriod());
				sim.textArea_3.setText(getServers().get(3).toString()+ " Total processing time: " + getServers().get(3).getWaitingPeriod());
				}if(getServers().size()==5){
				sim.textArea.setText(getServers().get(0).toString()+ " Total processing time: " + getServers().get(0).getWaitingPeriod());
				sim.textArea_1.setText(getServers().get(1).toString()+ " Total processing time: " + getServers().get(1).getWaitingPeriod());
				sim.textArea_2.setText(getServers().get(2).toString()+ " Total processing time: " + getServers().get(2).getWaitingPeriod());
				sim.textArea_3.setText(getServers().get(3).toString()+ " Total processing time: " + getServers().get(3).getWaitingPeriod());
				sim.textArea_4.setText(getServers().get(4).toString()+ " Total processing time: " + getServers().get(4).getWaitingPeriod());
				}if(getServers().size()==6){
				sim.textArea.setText(getServers().get(0).toString()+ " Total processing time: " + getServers().get(0).getWaitingPeriod());
				sim.textArea_1.setText(getServers().get(1).toString()+ " Total processing time: " + getServers().get(1).getWaitingPeriod());
				sim.textArea_2.setText(getServers().get(2).toString()+ " Total processing time: " + getServers().get(2).getWaitingPeriod());
				sim.textArea_3.setText(getServers().get(3).toString()+ " Total processing time: " + getServers().get(3).getWaitingPeriod());
				sim.textArea_4.setText(getServers().get(4).toString()+ " Total processing time: " + getServers().get(4).getWaitingPeriod());
				sim.textArea_5.setText(getServers().get(5).toString()+ " Total processing time: " + getServers().get(5).getWaitingPeriod());
			}
				}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentTime++;
		}
	}
	public static void main(String[] args){
		sim = new SimulationFrame();
		sim.setVisible(true);
		sim.setEnabled(true);
	}

	public int getMaxArrivalTime() {
		return maxArrivalTime;
	}

	public void setMaxArrivalTime(int maxArrivalTime) {
		this.maxArrivalTime = maxArrivalTime;
	}

	public int getMaxProcessingTime() {
		return maxProcessingTime;
	}

	public void setMaxProcessingTime(int maxProcessingTime) {
		this.maxProcessingTime = maxProcessingTime;
	}

	public static List<Server> getServers() {
		return servers;
	}

	public static void setServers(List<Server> servers) {
		SimulationManager.servers = servers;
	}

	public int getMinProcessingTime() {
		return minProcessingTime;
	}

	public void setMinProcessingTime(int minProcessingTime) {
		this.minProcessingTime = minProcessingTime;
	}

	public int getMinArrivalTime() {
		return minArrivalTime;
	}

	public void setMinArrivalTime(int minArrivalTime) {
		this.minArrivalTime = minArrivalTime;
	}
}
