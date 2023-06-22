package guiExitCar;

import javax.swing.JLabel;

import gui.NumericPane;

import java.awt.*;

public class EntNum extends NumericPane {
	JLabel label = new JLabel("차량번호 입력");

	EntNum() {
		setBackground(Color.WHITE);
		digit = 4;
		add(label);
		set();
	}
}