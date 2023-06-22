package guiExitCar;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import parking.Car;
import parking.Customer;
import parking.Discount;
import parking.Resident;

import static gui.GUIMain.p;

public class CarInfo extends JPanel {
	Customer cst = null;
	Car c = null;
	Discount d;
	JPanel subNext = new JPanel();
	JButton yes = new JButton("할인권 적용");
	JButton pay = new JButton("출차");

	JButton out = new JButton("출차");
	JButton view = new JButton("입출차 내역 조회");

	JTextArea warnningMesage = new JTextArea("잘못된 차량번호입니다. 다시 입력해주세요.");
	JTextArea carStateOut = new JTextArea("출차 상태입니다.");
	CarDetail carDetail = new CarDetail();
	JPanel discountButton = new JPanel();
	JPanel exitCarButton = new JPanel();

	CarInfo() {
		add(warnningMesage);
		warnningMesage.setVisible(false);
		add(carDetail);
		carDetail.setVisible(false);
		add(carStateOut);
		carStateOut.setVisible(false);
	}

	CarInfo(ActionListener yesListener, ActionListener noListener, ActionListener outListener,
			ActionListener viewListener) {
		this();
		yes.addActionListener(yesListener);
		discountButton.add(yes);
		pay.addActionListener(noListener);
		discountButton.add(pay);

		out.addActionListener(outListener);
		exitCarButton.add(out);
		view.addActionListener(viewListener);
		exitCarButton.add(view);
	}

	public JPanel setCarInfo(String carNum) {
		cst = p.findCst(carNum);
		c = null;
		if (cst == null) {
			c = p.findCar(carNum);
			if (c == null) {
				carStateOut.setVisible(false);
				carDetail.setVisible(false);
				warnningMesage.setVisible(true);
				return null;
			}
			else {
				setBackground(new Color(225, 0, 0, 150));
				carDetail.setCar(c);
				warnningMesage.setVisible(false);
				carDetail.setVisible(true);
				carStateOut.setVisible(false);
				return discountButton;
			}
		}
		else {
			c = cst.ownCar;
			if (cst instanceof Resident) {
				setBackground(new Color(0, 225, 0, 150));
			} else {
				setBackground(new Color(0, 0, 225, 150));
			}
			if (c.carState) {
				carDetail.setCar(c);
				warnningMesage.setVisible(false);
				carDetail.setVisible(true);
				carStateOut.setVisible(false);
				out.setEnabled(true);
			}
			else {
				warnningMesage.setVisible(false);
				carDetail.setVisible(false);
				carStateOut.setVisible(true);
				out.setEnabled(false);
			}
			return exitCarButton;
		}
			
	}

}