package guiExitCar;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import parking.Customer;

import static gui.GUIMain.p;

public class ViewParkingLog extends JPanel {

	JTextArea parkingLog = new JTextArea();

	ViewParkingLog() {
		this.add(parkingLog);
	}

	void printLog(Customer past) {
		Customer cst = p.findCst(past.name);
		parkingLog.setText("입차\t\t출차\n");
		parkingLog.setText(parkingLog.getText() + cst.parkingLog());
	}

}
