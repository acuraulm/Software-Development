import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
	@Override
	public void addTask(List<Server> servers, Task t) {
		
			Server leastTasks = new Server();
			leastTasks = SimulationManager.getServers().get(0);
			for (int i = 0; i < SimulationManager.getServers().size(); i++) {
				if(leastTasks.getSize() > SimulationManager.getServers().get(i).getSize() && SimulationManager.getServers().get(i).getSize() >= 0)
					leastTasks = servers.get(i);
			}
			leastTasks.addTask(t);
			
}
}
//	@Override
//	public void addTask(List<Server> servers, Task t) {
//		Server Server1 = new Server();
//		Server1.addTask(t);
//		servers.add(Server1);
//
//	}

