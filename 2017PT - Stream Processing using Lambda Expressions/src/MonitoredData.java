import java.util.Date;

public class MonitoredData {

	Date startTime;
	Date endTime;
	String activityLabel;
	
	public MonitoredData(){	
	}

	public MonitoredData(Date startTime, Date endTime, String activityLabel) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.activityLabel = activityLabel;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getActivityLabel() {
		return activityLabel;
	}

	@Override
	public String toString() {
		return "MonitoredData [startTime=" + startTime + ", endTime=" + endTime + ", activityLabel=" + activityLabel
				+ "]";
	}

	public void setActivityLabel(String activityLabel) {
		this.activityLabel = activityLabel;
	}

	
	
}
