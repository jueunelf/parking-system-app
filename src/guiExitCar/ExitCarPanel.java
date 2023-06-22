package guiExitCar;

import parking.Car;
import parking.Customer;
import parking.Discount;
import parking.Resident;

import javax.swing.*;

import gui.ControlButtonPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import static gui.GUIMain.mainFrame;
import static gui.GUIMain.p;

public class ExitCarPanel extends JPanel {
	private GridBagLayout gBag = new GridBagLayout();
	JPanel step[] = new JPanel[10];

	private static ExitCarPanel exitCar = null;

	private ExitCarPanel() {
		setLayout(gBag);
		setBackground();
		createNInit();
	}

	public static ExitCarPanel getInstance() {
		if (exitCar == null)
			exitCar = new ExitCarPanel();
		return exitCar;
	}

	ImageIcon icon;

	private void setBackground() {
		// TODO Auto-generated method stub
		icon = new ImageIcon("./resources/ExitCarBackground.jpg");
	}

	public void paintComponent(Graphics g) {
		g.drawImage(icon.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	void createNInit() {
		step[0] = new ControlButtonPanel();
		step[1] = new EntNum();
		step[3] = new EntDis();
		step[4] = new Paying();
		step[7] = new ChargingPlan();
		step[8] = new ViewParkingLog();
		step[2] = new CarInfo(toStep3ActionListner, toStep4ActionListner, toStep6ActionListner, toStep8ActionListner);
		step[5] = new GoodByeVisitor(toStep1ActionListner);
		step[6] = new GoodByeCst(toStep1ActionListner, toStep7ActionListner);

		setVisible(1);
		setTransparency();
		setgbinsert();
		gbinsert(step[0], 0, 1, 1, 1);

		((ControlButtonPanel) step[0]).setEnabled(false, true);
		((ControlButtonPanel) step[0]).setNextBtnActionListener(toStep2ActionListner);
	}

	private void setgbinsert() {
		// TODO Auto-generated method stub
		for (int i = 1; i < 9; i++) {
			gbinsert(step[i], 0, 0, 1, 1);
		}
	}

	private void setVisible(int n) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++) {
			if (i == 0 || i == n)
				step[i].setVisible(true);
			else
				step[i].setVisible(false);
		}
	}

	void setTransparency() {
		for (int i = 1; i < 9; i++) {
			step[i].setBackground(new Color(255, 255, 255, 150));
		}
	}

	public void gbinsert(Component c, int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gBag.setConstraints(c, gbc);
		add(c);
	}
	
	private ActionListener toStep1ActionListner = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			((EntNum) step[1]).clear();
			((ControlButtonPanel) step[0]).resetNextButton();
			((ControlButtonPanel) step[0]).setEnabled(false, true);
			((ControlButtonPanel) step[0]).setNextBtnActionListener(toStep2ActionListner);
			setVisible(1);
		}
	};

	private ActionListener toStep2ActionListner = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (((EntNum) step[1]).getValue().length() == 4) {
				toStep2();
			}
		}
	};

	private void toStep2() {
		JPanel controlButton = ((CarInfo) step[2]).setCarInfo(((EntNum) step[1]).getValue());
		((ControlButtonPanel) step[0]).setEnabled(true, false);
		((ControlButtonPanel) step[0]).resetNextButton();
		((ControlButtonPanel) step[0]).changeNextButton(controlButton);
		((ControlButtonPanel) step[0]).setPreBtnActionListener(toStep1ActionListner);
		setVisible(2);
	}

	private ActionListener toStep3ActionListner = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			((EntDis) step[3]).clear();
			((ControlButtonPanel) step[0]).resetNextButton();
			((ControlButtonPanel) step[0]).setPreBtnActionListener(toStep2ActionListner);
			((ControlButtonPanel) step[0]).setNextBtnActionListener(checkDiscountListener);
			((ControlButtonPanel) step[0]).setEnabled(true, true);
			setVisible(3);
			repaint();
		}
	};
	
	ActionListener checkDiscountListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (((EntDis) step[3]).getValue().length() == 7) {
				Discount d = null;
				d = p.findDc(((EntDis) step[3]).getValue());
				if (d == null) {
					JDialog invalidDis = new JDialog();
					JPanel text = new JPanel();
					JTextArea warning = new JTextArea();
					warning.setEditable(false);
					invalidDis.setSize(300, 100);
					warning.setText(((EntDis) step[3]).getValue() + "\n잘못된 할인권 번호입니다. 다시 입력해주세요.");
					text.add(warning);
					invalidDis.add(text, BorderLayout.CENTER);
					invalidDis.setVisible(true);
					invalidDis.setLocationRelativeTo(mainFrame);
					((EntDis) step[3]).clear();
				} else {
					((CarInfo) step[2]).c.applyDc(d);
					p.dcMgr.mList.remove(d);
					toStep2();
				}
			}
		}
	};

	private ActionListener toStep4ActionListner = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Customer cst = ((CarInfo) step[2]).cst;
			Car c = ((CarInfo) step[2]).c;
			if (cst == null)
				toStep4Vst(c);
			if (cst != null) {
				if (((ChargingPlan) step[7]).selectedPrice() == 0) {
					setVisible(7);
					//return;
				}
				else {
					toStep4Cst(((ChargingPlan) step[7]).selectedPrice());
					cst.addTime(((ChargingPlan) step[7]).selectedTime());
				}
			}
		}
	};
	
	void toStep4Vst(Car c) {
		((Paying) step[4]).set(c.price);
		setVisible(4);
		step[0].setVisible(false);
		Timer t = new Timer();
		t.schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				setVisible(5);
				step[0].setVisible(false);
				p.carMgr.mList.remove(c);
				c.out();
				t.cancel();
			}
		}, 3000);
	}
	
	void toStep4Cst(int pay) {
		((Paying) step[4]).set(pay);
		setVisible(4);
		step[0].setVisible(false);
		Timer t = new Timer();
		t.schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				toStep6(((CarInfo) step[2]).cst);
				t.cancel();
			}
		}, 3000);
	}

	private ActionListener toStep6ActionListner = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			toStep6(((CarInfo) step[2]).cst);
		}
	};

	void toStep6(Customer cst) {
		//Customer cst = p.findCst(notUpdated.name);
		int flag = 0;
		if (cst.ownCar.carState) {
			flag = cst.out();
		}
		if (flag == 0) {
			((GoodByeCst) step[6]).set(cst);
			setVisible(6);
			step[0].setVisible(false);
		}
		else {
			toStep4Cst(flag);
		}
		p.carMgr.mList.remove(cst.ownCar);
		
	}

	private ActionListener toStep7ActionListner = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selectBtn = new JButton("선택");
			selectBtn.addActionListener(toStep4ActionListner);
			((ControlButtonPanel) step[0]).resetNextButton();
			((ControlButtonPanel) step[0]).setPreBtnActionListener(toStep6ActionListner);
			((ControlButtonPanel) step[0]).changeNextButton(selectBtn);
			setVisible(7);
		}
	};

	private ActionListener toStep8ActionListner = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			((ViewParkingLog) step[8]).printLog(((CarInfo) step[2]).cst);
			JButton homeBtn = new JButton("처음으로");
			homeBtn.addActionListener(toStep1ActionListner);
			((ControlButtonPanel) step[0]).resetNextButton();
			((ControlButtonPanel) step[0]).setPreBtnActionListener(toStep2ActionListner);
			((ControlButtonPanel) step[0]).changeNextButton(homeBtn);
			setVisible(8);
		}
	};
}