import java.util.List;

public class Scheduler {
	private int maxNoServers;
	private Strategy strategy;
	public Scheduler(int maxNoServers){
		this.setMaxNoServers(maxNoServers);
	}
	
	public void changeStrategy(SelectionPolicy policy){
		if(policy == SelectionPolicy.SHORTEST_QUEUE){
			setStrategy(new ConcreteStrategyQueue());
		}
		if(policy == SelectionPolicy.SHORTEST_TIME){
			setStrategy(new ConcreteStrategyTime());
		}
	}
	
	public void dispatchTask(Task t){
		strategy.addTask(SimulationManager.getServers(), t);
	}

	public List<Server> getServers(){
		return SimulationManager.servers;
	}

	public int getMaxNoServers() {
		return maxNoServers;
	}

	public void setMaxNoServers(int maxNoServers) {
		this.maxNoServers = maxNoServers;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
}
