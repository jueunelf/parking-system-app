package guiManager;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ManagerMenu extends JPanel {

	JButton changeCost = new JButton("요금 변경");
	JButton viewCst = new JButton("등록 차량 조회");
	JButton viewCar = new JButton("전체 차량 조회");

	void setMenu(ActionListener changeCostActionListner, ActionListener viewCstAL, ActionListener viewCarAL) {
		changeCost.addActionListener(changeCostActionListner);
		viewCst.addActionListener(viewCstAL);
		viewCar.addActionListener(viewCarAL);

		this.add(changeCost);
		this.add(viewCst);
		this.add(viewCar);
	}

}
