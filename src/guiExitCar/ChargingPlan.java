package guiExitCar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import static gui.GUIMain.p;

public class ChargingPlan extends JPanel implements ListSelectionListener {
	private static final long serialVersionUID = 1L;
	JTable table;
	int selectedIndex = -1;

	ArrayList<DefaultCharge> chargeList = new ArrayList<>();

	public ChargingPlan() {
		super(new BorderLayout());
		makeList();
		init();
		table.setBackground(new Color(225, 225, 225, 220));
		this.add(table);
	}

	private void makeList() {
		// TODO Auto-generated method stub
		for (int i = 5; i < 81;) {
			double d = (double) p.price / p.perMin;
			int ip = (int) (d * 60 * i);
			DefaultCharge temp = new DefaultCharge(i, ip);
			chargeList.add(temp);
			i = i + 5;
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		final String[] columnNames = { "시간", "가격(원)" };
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		for (DefaultCharge c : chargeList) {
			tableModel.addRow(c.getObj());
		}

		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(this);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

	}

	public int selectedPrice() {
		if (selectedIndex != -1)
			return (int) table.getValueAt(selectedIndex, 1);
		else
			return 0;
	}
	
	public int selectedTime() {
		if (selectedIndex != -1)
			return (int) table.getValueAt(selectedIndex, 0);
		else
			return 0;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedIndex = lsm.getMinSelectionIndex();
		}
	}
	
}
