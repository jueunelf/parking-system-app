package guiExitCar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GoodByeVisitor extends JPanel {
	private GridBagLayout gBag = new GridBagLayout();
	private Image backgroundImg;
	JButton home = new JButton("처음으로");

	public GoodByeVisitor(ActionListener goHomeActionListener) {
		backgroundImg = new ImageIcon("./resources/goodbye/visitor.jpg").getImage().getScaledInstance(500, 350,
				java.awt.Image.SCALE_SMOOTH);
		setLayout(gBag);
		home.addActionListener(goHomeActionListener);
		gbinsert(Box.createRigidArea(new Dimension(415, 314)), 0, 0, 1, 1);
		gbinsert(home, 1, 1, 1, 1);
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
}