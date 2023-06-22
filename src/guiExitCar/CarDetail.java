package guiExitCar;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import parking.Car;

public class CarDetail extends JPanel {
	JTextArea carInfo = new JTextArea();
	JLabel photo = new JLabel();

	CarDetail() {
		setLayout(new GridLayout(1, 2, 10, 10));
		add(carInfo);
		add(photo);
		carInfo.setBackground(new Color(225, 225, 225, 150));
	}

	void setCar(Car c) {
		carInfo.setText(c.toString());
		ImageIcon imageIcon = new ImageIcon(String.format("./resources/carImages/%d.jpg", c.carNum));
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(200, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg);
		photo.setIcon(imageIcon);
	}

	CarDetail(Car c) {
		this();
		setCar(c);
	}

}
