package guiExitCar;

import parking.Customer;
import parking.Resident;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static gui.GUIMain.p;

public class GoodByeCst extends JPanel {

	private JTextArea cstName = new JTextArea("");
	private JTextArea cstTime = new JTextArea("");
	private JButton charge = new JButton("충전");
	private JButton home = new JButton("처음으로");
	private Image backgroundImg;
	private Image residentImg;
	private Image customerImg;
	private GridBagLayout gBag = new GridBagLayout();

	GoodByeCst() {
		add(cstName);
		cstName.setEditable(false);
	}

	GoodByeCst(ActionListener goHomeActionListener, ActionListener chargeListener) {
		residentImg = new ImageIcon("./resources/goodbye/resident.jpg")
				.getImage().getScaledInstance(500, 350, java.awt.Image.SCALE_SMOOTH);
		customerImg = new ImageIcon("./resources/goodbye/customer.jpg")
				.getImage().getScaledInstance(500, 350, java.awt.Image.SCALE_SMOOTH);

		setLayout(gBag);
		charge.addActionListener(chargeListener);
		home.addActionListener(goHomeActionListener);
		cstName.setFont(new Font(cstName.getFont().getName(), Font.BOLD, 25));
		cstTime.setFont(new Font(cstTime.getFont().getName(), Font.BOLD, 25));
		gbinsert(Box.createRigidArea(new Dimension(137, 204)), 0, 0, 1, 1);
		gbinsert(cstName, 1, 1, 1, 1);
		gbinsert(Box.createRigidArea(new Dimension(150, 110)), 2, 2, 1, 1);
		gbinsert(cstTime, 2, 3, 1, 1);
		gbinsert(charge, 3, 3, 1, 1);
		gbinsert(home, 4, 3, 1, 1);
		gbinsert(Box.createHorizontalStrut(60), 3, 4, 1, 1);
	}

	public void gbinsert(Component c, int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gBag.setConstraints(c, gbc);
		add(c);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImg, 0, 0, this);
	}

	void set(Customer cst) {
		cstName.setText(cst.name);
		if (cst instanceof Resident) {
			backgroundImg = residentImg;
			charge.setVisible(false);
			cstTime.setVisible(false);
		} else {
			backgroundImg = customerImg;
			charge.setVisible(true);
			cstTime.setVisible(true);
		}
		cstTime.setText(cst.remainTime());
		cstName.setBackground(new Color(225, 225, 225, 0));
		cstTime.setBackground(new Color(225, 225, 225, 0));
	}
}
