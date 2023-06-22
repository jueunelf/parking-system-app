package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewParkingLot extends JPanel {

	String area;
	int cells;
	JPanel cell[];

	public ViewParkingLot(String pA, int num) {
		area = pA;
		cells = num;
	}

	void setView() {
		GridLayout g = new GridLayout();
		this.setLayout(new GridLayout(0, 7, 1, 1));
		cell = new JPanel[cells];
		int i = 0;
		for (; i < cells; i++) {
			cell[i] = new JPanel();
			cell[i].setPreferredSize(new Dimension(10, 30));
			cell[i].setBackground(Color.LIGHT_GRAY);
			JLabel cellName = new JLabel(this.area + (i + 1));
			cell[i].add(cellName);
			this.add(cell[i]);
		}
	}

}