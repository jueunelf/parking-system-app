package guiExitCar;

import javax.swing.JLabel;

import gui.NumericPane;

import java.awt.*;

public class EntDis extends NumericPane {

	JLabel label = new JLabel("할인권 번호 입력");

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
