package guiExitCar;

import javax.swing.JLabel;

import gui.NumericPane;

import java.awt.*;

public class EntDis extends NumericPane {

	JLabel label = new JLabel("���α� ��ȣ �Է�");

	EntDis() {
		setBackground(Color.WHITE);
		digit = 7;
		add(label);
		set();
	}

	@Override
	protected String initField() {
		return "D";
	}

}
