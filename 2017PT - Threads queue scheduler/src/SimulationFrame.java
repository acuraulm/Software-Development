import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class SimulationFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextField txtMaxProcTime;
	private JTextField txtTime;
	private JTextField txtServers;
	private JTextField txtClients;
	public JTextArea txtrLogs;
	public JTextArea textArea;
	public JTextArea textArea_1;
	public JTextArea textArea_2;
	public JTextArea textArea_3;
	public JTextArea textArea_4;
	public JTextArea textArea_5;
	private SimulationManager gen;
	private SelectionPolicy policy;
	private JTextField txtMinProcTime;
	private JTextField txtMaxArrival;
	private JTextField txtMinArrival;
	public JLabel lblSimulationResults_1;
	Thread t;
	public SimulationFrame() {
		setAlwaysOnTop(true);
		getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setBounds(0, 0, 981, 135);
		panel.setLayout(null);
		getContentPane().add(panel);
		this.setSize(1006,175);

		txtMaxProcTime = new JTextField();
		txtMaxProcTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaxProcTime.setText("10");
		txtMaxProcTime.setBounds(496, 93, 148, 20);
		panel.add(txtMaxProcTime);
		txtMaxProcTime.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtTime.setText("25");
		txtTime.setColumns(10);
		txtTime.setBounds(22, 42, 148, 20);
		panel.add(txtTime);
		
		txtServers = new JTextField();
		txtServers.setHorizontalAlignment(SwingConstants.CENTER);
		txtServers.setText("3");
		txtServers.setColumns(10);
		txtServers.setBounds(180, 42, 148, 20);
		panel.add(txtServers);
		
		txtClients = new JTextField();
		txtClients.setHorizontalAlignment(SwingConstants.CENTER);
		txtClients.setText("15");
		txtClients.setColumns(10);
		txtClients.setBounds(180, 93, 148, 20);
		panel.add(txtClients);
		
		JLabel lblMaxPt = new JLabel("Maximum processing time");
		lblMaxPt.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaxPt.setBounds(496, 75, 148, 17);
		panel.add(lblMaxPt);
		
		JLabel lblRunningTime = new JLabel("Serving time");
		lblRunningTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblRunningTime.setBounds(22, 24, 148, 17);
		panel.add(lblRunningTime);
		
		JLabel lblNumberOfServers = new JLabel("Maximum Nr. of servers");
		lblNumberOfServers.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfServers.setBounds(180, 24, 148, 17);
		panel.add(lblNumberOfServers);
		
		JLabel lblNumberOfClients = new JLabel("Number of clients");
		lblNumberOfClients.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfClients.setBounds(180, 75, 148, 17);
		panel.add(lblNumberOfClients);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 306, 302);
		
		
		txtrLogs = new JTextArea();
		scrollPane.setViewportView(txtrLogs);
		txtrLogs.setText("Logs");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(326, 136, 610, 41);
		
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(326, 188, 610, 41);
		
		
		textArea_1 = new JTextArea();
		scrollPane_2.setViewportView(textArea_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(326, 240, 610, 41);
		
		
		textArea_2 = new JTextArea();
		scrollPane_3.setViewportView(textArea_2);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(326, 292, 610, 41);
		
		
		textArea_3 = new JTextArea();
		scrollPane_4.setViewportView(textArea_3);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(326, 344, 610, 41);
		
		
		textArea_4 = new JTextArea();
		scrollPane_5.setViewportView(textArea_4);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(326, 396, 610, 41);
		
		
		textArea_5 = new JTextArea();
		scrollPane_6.setViewportView(textArea_5);
		
		txtMinProcTime = new JTextField();
		txtMinProcTime.setText("1");
		txtMinProcTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtMinProcTime.setColumns(10);
		txtMinProcTime.setBounds(496, 42, 148, 20);
		panel.add(txtMinProcTime);
		
		JLabel lblMinProcessingTime = new JLabel("Minimum processing time");
		lblMinProcessingTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinProcessingTime.setBounds(496, 24, 148, 17);
		panel.add(lblMinProcessingTime);
		
		txtMaxArrival = new JTextField();
		txtMaxArrival.setText("10");
		txtMaxArrival.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaxArrival.setColumns(10);
		txtMaxArrival.setBounds(338, 93, 148, 20);
		panel.add(txtMaxArrival);
		
		JLabel lblMaximumArrivalTime = new JLabel("Maximum arrival time");
		lblMaximumArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaximumArrivalTime.setBounds(338, 75, 148, 17);
		panel.add(lblMaximumArrivalTime);
		
		txtMinArrival = new JTextField();
		txtMinArrival.setText("1");
		txtMinArrival.setHorizontalAlignment(SwingConstants.CENTER);
		txtMinArrival.setColumns(10);
		txtMinArrival.setBounds(338, 42, 148, 20);
		panel.add(txtMinArrival);
		
		JLabel lblMinimumArrivalTime = new JLabel("Minimum arrival time");
		lblMinimumArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimumArrivalTime.setBounds(338, 24, 148, 17);
		panel.add(lblMinimumArrivalTime);
		
		//JComboBox<SelectionPolicy> comboBox = new JComboBox<SelectionPolicy>(SelectionPolicy.values());
		//comboBox.setBounds(22, 93, 148, 20);
		//panel.add(comboBox);
		
		JLabel lblServingPolicy = new JLabel("Serving policy");
		lblServingPolicy.setHorizontalAlignment(SwingConstants.CENTER);
		lblServingPolicy.setBounds(22, 76, 148, 17);
		panel.add(lblServingPolicy);
		
		JButton btnDoStuff = new JButton("Do stuff");
		btnDoStuff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//if(comboBox.getSelectedIndex()==1)
					policy = SelectionPolicy.SHORTEST_TIME;
				//if(comboBox.getSelectedIndex()==0)
				//	policy = SelectionPolicy.SHORTEST_QUEUE;
					panel.setSize(1000,500);
				setSize(1000,500);
				panel.add(scrollPane);
				if(Integer.parseInt(txtServers.getText())==1)
					panel.add(scrollPane_1);
				if(Integer.parseInt(txtServers.getText())==2){
					panel.add(scrollPane_1);
					panel.add(scrollPane_2);
				}
				if(Integer.parseInt(txtServers.getText())==3){
					panel.add(scrollPane_1);
					panel.add(scrollPane_2);
					panel.add(scrollPane_3);
				}if(Integer.parseInt(txtServers.getText())==4){
					panel.add(scrollPane_1);
					panel.add(scrollPane_2);
					panel.add(scrollPane_3);
					panel.add(scrollPane_4);
				}if(Integer.parseInt(txtServers.getText())==5){
					panel.add(scrollPane_1);
					panel.add(scrollPane_2);
					panel.add(scrollPane_3);
					panel.add(scrollPane_4);
					panel.add(scrollPane_5);
				}if(Integer.parseInt(txtServers.getText())==6){
					panel.add(scrollPane_1);
					panel.add(scrollPane_2);
					panel.add(scrollPane_3);
					panel.add(scrollPane_4);
					panel.add(scrollPane_5);
					panel.add(scrollPane_6);
				}
				addText("\nTimelapse: " + txtTime.getText());
				addText("\nMaximum Processing Time: "+txtMaxProcTime.getText());
				addText("\nNumber of servers:  "  + txtServers.getText());
				addText("\nNumber of clients:  " +txtClients.getText());
				//public SimulationManager(int timeLimit, int maxArrivalTime, int maxProcessingTime, int numberOfServers,int numberOfClients)
				gen = new SimulationManager(Integer.parseInt(txtTime.getText()), Integer.parseInt(txtMinArrival.getText()), Integer.parseInt(txtMaxArrival.getText()), Integer.parseInt(txtMinProcTime.getText()), Integer.parseInt(txtMaxProcTime.getText()), Integer.parseInt(txtServers.getText()), Integer.parseInt(txtClients.getText()),policy);
				t = new Thread(gen);
				t.start();
			}
		});
		btnDoStuff.setIcon(new ImageIcon(SimulationFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
		btnDoStuff.setBounds(654, 11, 136, 114);
		panel.add(btnDoStuff);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(800, 11, 174, 114);
		panel.add(scrollPane_8);
		
		lblSimulationResults_1 = new JLabel("Simulation results:");
		scrollPane_8.setViewportView(lblSimulationResults_1);
		lblSimulationResults_1.setVerticalAlignment(SwingConstants.TOP);
		lblSimulationResults_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSimulationResults_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblSimulationResults = new JLabel("Simulation Results:");
		lblSimulationResults.setBounds(1113, 11, -139, 102);
		panel.add(lblSimulationResults);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(1117, 20, -158, 361);
		panel.add(scrollPane_7);
		
		JTextArea txtrSimulationresultss = new JTextArea();
		scrollPane_7.setViewportView(txtrSimulationresultss);
		txtrSimulationresultss.setText("SimulationResultss");;
	}
	public void addText(String text){
		lblSimulationResults_1.setText(lblSimulationResults_1.getText() + text + "\n");
	}
	public void displayData(Task[][] tasks){
		
	}
}
//scrollpane <- jlist new jlist<tasks>(tasks[])