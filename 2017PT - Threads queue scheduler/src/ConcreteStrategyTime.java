import java.util.List;

public class ConcreteStrategyTime implements Strategy {
	@Override
	public void addTask(List<Server> servers, Task t) {
		
			Server leastTasks = new Server();
			leastTasks = SimulationManager.getServers().get(0);
			for (int i = 0; i < SimulationManager.getServers().size(); i++) {
				if(leastTasks.getWaitingPeriod().get() > SimulationManager.getServers().get(i).getWaitingPeriod().get())
					leastTasks = servers.get(i);
			}
			leastTasks.addTask(t);
			
}
}