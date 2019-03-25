
public class Task {
	private int arrivalTime;
	private int processingTime;
	private int enqueueTime;
	private int taskID;
	
	public Task(int arrivalTime, int processingTime, int taskID){
		this.arrivalTime = arrivalTime;
		this.processingTime = processingTime;
		this.taskID = taskID;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getProcessingTime() {
		return processingTime;
	}
	public void decreaseProcessingTime(){
		this.processingTime--;
	}

	public void setProcessingTime(int processingTime) {
		this.processingTime = processingTime;
	}

	@Override
	public String toString() {
		return "Task" + taskID;
	}

	public int getEnqueueTime() {
		return enqueueTime;
	}

	public void setEnqueueTime(int enqueueTime) {
		this.enqueueTime = enqueueTime;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	
}
