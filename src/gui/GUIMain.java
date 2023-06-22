package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import guiExitCar.ExitCarPanel;
import guiManager.ManagerPanel;
import mgr.Manageable;
import parking.ParkingCell;
import parking.ParkingLot;

public class GUIMain {

	private static GUIMain main = null;

	private GUIMain() {
	}

	public static GUIMain getInstance() {
		if (main == null)
			main = new GUIMain();
		return main;
	}

	public static ParkingLot p = ParkingLot.getInstance();

	public static void main(String[] args) {
		p.run();
		startGUI();
	}

	private static void startGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIMain.getInstance().createAndShowGUI();
			}
		});
	}

	public static JFrame mainFrame = new JFrame("주차 관리 시스템");

	private void createAndShowGUI() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane jtab = new JTabbedPane();
		
		ExitCarPanel exitPanel = ExitCarPanel.getInstance();
		ManagerPanel mgrPanel = ManagerPanel.getInstance();

		jtab.add("출차", exitPanel);
		jtab.add("주차현황", setupViewParkingLot());
		jtab.add("관리자", mgrPanel);

		mainFrame.getContentPane().add(jtab);
		jtab.setBackground(Color.WHITE);

		jtab.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (jtab.getSelectedIndex() == 1) {
					mainFrame.setSize(new Dimension(450, 1000));
					setupViewParkingLot();
				} else
					mainFrame.setSize(new Dimension(800, 450));
			}
		});

		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setSize(new Dimension(800, 450));
		mainFrame.setResizable(false);
	}

	private JPanel viewPL = new JPanel(new BorderLayout());
	private JPanel setupViewParkingLot() {
		viewPL.removeAll();
		viewPL.setLayout(new GridLayout(5, 1, 10, 30));
		setupSub("A", 10);
		setupSub("B", 13);
		setupSub("C", 14);
		setupSub("D", 12);
		setupSub("E", 10);
		return viewPL;
	}

	void setupSub(String area, int num) {
		ViewParkingLot view = new ViewParkingLot(area, num);
		view.setView();
		for (Manageable m : p.pcMgr.mList) {
			ParkingCell p = (ParkingCell) m;
			if (p.pArea.equals(area) && p.occupied)
				view.cell[p.pNum - 1].setBackground(Color.RED);
		}
		viewPL.add(view);
	}

}
