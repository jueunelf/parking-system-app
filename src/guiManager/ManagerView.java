package guiManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import mgr.Manageable;
import parking.Car;
import parking.Customer;
import parking.ParkingCell;
import parking.ParkingLot;
import static gui.GUIMain.p;

public class ManagerView extends JPanel implements ListSelectionListener {

	private static final long serialVersionUID = 1L;
	JTable table;
	int selectedIndex = -1;
	int menu;

	JTable list;
	JTextArea detail;

	public ManagerView(int m) {
		menu = m;
		if (menu == 1) {
			setCstTable(p);
			detail = new JTextArea(6, 20);
		}
		if (menu == 2) {
			setCarTable(p);
			detail = new JTextArea(6, 30);
		}
		this.setBackground(new Color(255, 255, 255, 150));
		this.add(table);
		this.add(detail);
		detail.setEditable(false);
	}

	void setCstTable(ParkingLot p) {
		final String[] columnNames = { "이름" };
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		Collections.sort(p.cstMgr.mList, new Comparator<Manageable>() {
			@Override
			public int compare(Manageable o1, Manageable o2) {
				Customer cst1 = (Customer) o1;
				Customer cst2 = (Customer) o2;
				// TODO Auto-generated method stub
				return cst1.name.compareTo(cst2.name);
			}
		});
		for (Manageable m : p.cstMgr.mList) {
			Customer cst = (Customer) m;
			String[] cstName = { cst.name };
			tableModel.addRow(cstName);
		}
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(300, 400));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(this);
	}

	void setCarTable(ParkingLot p) {
		final String[] columnNames = { "주차구역", "차량번호" };
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		Collections.sort(p.carMgr.mList, new Comparator<Manageable>() {
			@Override
			public int compare(Manageable o1, Manageable o2) {
				ParkingCell c1 = ((Car) o1).parkingArea;
				ParkingCell c2 = ((Car) o2).parkingArea;
				// TODO Auto-generated method stub
				return c1.pName.compareTo(c2.pName);
			}
		});
		for (Manageable m : p.carMgr.mList) {
			Car c = (Car) m;
			String[] carData = { c.parkingArea.pName, c.simpledata() };
			tableModel.addRow(carData);
		}
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(300, 400));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(this);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedIndex = lsm.getMinSelectionIndex();
		}
		showDetail();
	}

	public void showDetail() {
		if (selectedIndex != -1) {
			if (menu == 1) {
				Customer cst = p.findCst((String) table.getValueAt(selectedIndex, 0));
				if (cst != null)
					detail.setText(cst.showDetail());
			}
			if (menu == 2) {
				Car c = p.findCar((String) table.getValueAt(selectedIndex, 1));
				if (c != null)
					detail.setText(c.toString());
			}
		}
	}

}
