import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
	private BlockingQueue<Task> tasks = new ArrayBlockingQueue<Task>(1024);
	private AtomicInteger waitingPeriod = new AtomicInteger();
	
	public Server(){
		
	}
	public void addTask(Task newTask){
		increaseWaitingPeriod(newTask.getProcessingTime());
		tasks.add(newTask);
	}
	public void removeTask(Task oldTask){
		tasks.remove(oldTask);
		decreaseWaitingPeriod(oldTask.getProcessingTime());
	}
	public Task getFirst() throws InterruptedException{
		return tasks.peek();
	}
	public int getSize(){
		return tasks.size();
	}
	@Override
	public void run() {
		if(this.getSize()!= 0){
			try {
				if(this.getFirst() != null)
					{
					if(this.getFirst().getProcessingTime()!= 0)
					this.getFirst().decreaseProcessingTime();
					else{
						SimulationManager.sim.txtrLogs.setText(SimulationManager.sim.txtrLogs.getText() + "\n" + tasks.peek().toString() + " left Server");
						this.removeTask(this.getFirst());
				}
					}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Task[] getTasks(){
		Task[] array = new Task[tasks.size()];
		tasks.toArray();
		return array;
	}
	public int getProcTime(){
		int proc=0;
		try {
			proc = tasks.take().getProcessingTime();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proc;
	}
@Override
	public String toString() {
		return tasks.toString();// + ", waitingPeriod=" + waitingPeriod;
	}
	public AtomicInteger getWaitingPeriod() {
		return waitingPeriod;
	}
	public void setWaitingPeriod(int newValue) {
		waitingPeriod.set(newValue);
	}
	
	public void decreaseWaitingPeriod(int timeD){
		this.waitingPeriod.addAndGet(-timeD);
	}
	public void increaseWaitingPeriod(int timeD){
		this.waitingPeriod.addAndGet(timeD);
	}

}
