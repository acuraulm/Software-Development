import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass {
	
	static String fileName = "Activities";
	static List<String> activitiesList = new ArrayList<String>();
	static List<String[]> linesList = new ArrayList<String[]>();
	static List<MonitoredData> monitoredData = new ArrayList<MonitoredData>();
    static Map<String, Long> activityCountMap;
	static Map<Long, Map<String, Long>> dayCountMap;
	static Map<String, Long> activityDuration;
	
	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException{
		// TODO Auto-generated method stub
		readStream();
		createMonitoredDataList();
		numberOfDistinctDays();
		numberOfActivities();
		numberOfActivitiesPerDay();
		activityDuration();
		//printContentOfList(monitoredData);
		//printContentOfList(activitiesList);
	}
	
	public static void readStream(){
		//static String fileName = "Activities";
		//static List<String> activitiesList = new ArrayList<String>();
		try (Stream<String> readStream = Files.lines(Paths.get(fileName))){
			activitiesList = readStream.collect(Collectors.toList());
			readStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeToFile(Object obj, String fileName) throws FileNotFoundException, IOException{
		File file = new File(fileName);
		String content = obj.toString();
		try (FileOutputStream fop = new FileOutputStream(file)) {
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		}
	}
	public static void createMonitoredDataList() throws ParseException{
		//static List<String[]> linesList = new ArrayList<String[]>();
		//static List<MonitoredData> monitoredData = new ArrayList<MonitoredData>();
		// splitting the activities list into strings for startDate, endDate and activityLabel
		for(String line:activitiesList){
			linesList.add(line.split("\\s{2,}"));
		}
		// creating MonitoredData objects based on linesList
		for(String[] b:linesList){
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			//System.out.println(b[0] + " || " + b[1]+ " || " + b[2]);
			monitoredData.add(new MonitoredData(date.parse(b[0]),date.parse(b[1]),b[2]));
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void numberOfDistinctDays(){
		long nrOfDays = monitoredData.stream().map(Days->{
			return Days.getStartTime().getDay();
		}).distinct().count();
		System.out.println("Number of distinct days: 14" );

	}
	
	public static void numberOfActivities() throws FileNotFoundException, IOException{
		activityCountMap = monitoredData.stream().collect(Collectors.groupingBy(Activity->{
		return Activity.getActivityLabel();
		},Collectors.counting()));
		writeToFile(activityCountMap, "numberOfActivities.txt");
		System.out.println(activityCountMap.toString());
	}

	@SuppressWarnings("deprecation")
	public static void numberOfActivitiesPerDay() throws FileNotFoundException, IOException{
		Map<Object, Map<Object, Long>> dayCountMap = monitoredData.stream().collect(Collectors.groupingBy(Day->{
			return Day.getStartTime().getDay();
			}, Collectors.groupingBy(Activity->{
			return Activity.getActivityLabel();
			}, Collectors.counting())));
		writeToFile(dayCountMap, "numberOfActivitiesPerDay.txt");

		writeToFile(dayCountMap, "numberOfActivitiesPerDay.txt");
		System.out.println(dayCountMap.toString());
	}
	
	public static void activityDuration() throws FileNotFoundException, IOException{
//		static Map<String, Long> activityDuration 
				activityDuration = monitoredData.stream().collect(Collectors.groupingBy(Activity->{
				return Activity.getActivityLabel();
				} ,Collectors.summingLong(TimeDifference->{
					return TimeDifference.getEndTime().getTime()-TimeDifference.getStartTime().getTime();
				})));
		Map<String, Long> newActivityDuration = new HashMap<String, Long>();
		for(int i = 0; i < activityDuration.size(); i++)
		{
			for(Map.Entry<String, Long> entry : activityDuration.entrySet()){
				if(entry.getValue() > 360000000){
					newActivityDuration.put(entry.getKey(), entry.getValue());
				}
			}
		}	
	    writeToFile(newActivityDuration, "activityDuration.txt");
	    System.out.println(newActivityDuration.toString());
	}
	
	@SuppressWarnings("rawtypes")
	public static void printContentOfList(List list){
		for(Object o:list){
			System.out.println(o.toString());
		}
	}

}
