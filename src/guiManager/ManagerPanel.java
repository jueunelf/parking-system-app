package guiManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import gui.ControlButtonPanel;
import gui.GUIMain;

import static gui.GUIMain.mainFrame;
import static gui.GUIMain.p;

public class ManagerPanel extends JPanel {

	private GridBagLayout gBag = new GridBagLayout();
	private ManagerMenu menu = new ManagerMenu();
	private ChangeCost change = new ChangeCost();

	ControlButtonPanel controlPanel;
	
	private static ManagerPanel mgrPanel = null;

	private ManagerPanel() {
		setLayout(gBag);
		setBackground(Color.WHITE);
		menu.setMenu(changeCostActionListner, viewCstActionListener, viewCarActionListener);
		setBackground();
		setTransparency();
		setMenuPage();
	}

	public static ManagerPanel getInstance() {
		if (mgrPanel == null)
			mgrPanel = new ManagerPanel();
		return mgrPanel;
	}

	private void setTransparency() {
		// TODO Auto-generated method stub
		menu.setBackground(new Color(255, 255, 255, 150));
		change.setBackground(new Color(255, 255, 255, 150));
	}

	ImageIcon icon;

	private void setBackground() {
		// TODO Auto-generated method stub
		icon = new ImageIcon("./resources/ManagerBackground.jpg");
	}

	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
		setOpaque(false);
		super.paintComponent(g);
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

	public void setMenuPage() {
		gbinsert(menu, 0, 0, 1, 1);
		menu.setVisible(true);
		change.setVisible(false);
	}
	
	private ActionListener gotoManagePane = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			removeAll();
			repaint();
			setMenuPage();
		}
	};

	private ActionListener changeCostActionListner = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			controlPanel = new ControlButtonPanel();
			JButton acceptBtn = new JButton("적용");
			controlPanel.changeNextButton(acceptBtn);

			change.removeAll();
			change.set();
			change.setVisible(true);
			gbinsert(change, 0, 0, 1, 1);
			gbinsert(controlPanel, 0, 1, 1, 1);
			controlPanel.setVisible(true);
			controlPanel.setPreBtnActionListener(gotoManagePane);
			acceptBtn.addActionListener(updateActionListner);

			menu.setVisible(false);
		}

	};

	private ActionListener updateActionListner = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JTextArea complete = new JTextArea("주차요금\n기존 " + p.price + "원/" + p.perMin + "분 에서\n");
			if (!change.changePrice.getText().equals(""))
				p.price = Integer.parseInt(change.changePrice.getText());
			if (!change.changePer.getText().equals(""))
				p.perMin = Integer.parseInt(change.changePer.getText());
			JDialog completeAccept = new JDialog();
			JPanel dialogField = new JPanel();
			complete.setText(complete.getText() + p.price + "원/" + p.perMin + "분 으로 변경되었습니다");
			complete.setEditable(false);
			completeAccept.setSize(300, 100);
			JButton home = new JButton("처음으로");
			home.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					completeAccept.dispose();
				}
			});
			home.addActionListener(gotoManagePane);
			dialogField.add(complete);
			dialogField.add(home);
			completeAccept.add(dialogField, BorderLayout.CENTER);
			completeAccept.setVisible(true);
			completeAccept.setLocationRelativeTo(mainFrame);
		}

	};

	private ActionListener viewCstActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setViewPage(1);
		}

	};

	private ActionListener viewCarActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setViewPage(2);
		}

	};
	
	private void setViewPage(int n) {
		controlPanel = new ControlButtonPanel();
		controlPanel.setEnabled(true, false);
		removeAll();
		repaint();
		menu.setVisible(false);
		
		ManagerView viewAll = new ManagerView(n);
		
		gbinsert(viewAll, 0, 0, 1, 1);
		gbinsert(controlPanel, 0, 1, 1, 1);

		viewAll.setVisible(true);
		controlPanel.setPreBtnActionListener(gotoManagePane);

	}

}
