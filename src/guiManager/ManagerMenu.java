package guiManager;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ManagerMenu extends JPanel {

	JButton changeCost = new JButton("��� ����");
	JButton viewCst = new JButton("��� ���� ��ȸ");
	JButton viewCar = new JButton("��ü ���� ��ȸ");

	void setMenu(ActionListener changeCostActionListner, ActionListener viewCstAL, ActionListener viewCarAL) {
		changeCost.addActionListener(changeCostActionListner);
		viewCst.addActionListener(viewCstAL);
		viewCar.addActionListener(viewCarAL);

		this.add(changeCost);
		this.add(viewCst);
		this.add(viewCar);
	}

}
