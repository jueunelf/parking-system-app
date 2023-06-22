package guiManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.NumericPane;
import static gui.GUIMain.p;

public class ChangeCost extends NumericPane {

	JLabel label = new JLabel("현재 요금");
	JTextField changePrice;
	JTextField changePer;

	@Override
	public void set() {
		digit = 6;
		changePrice = new JTextField("" + p.price, 5);
		changePer = new JTextField("" + p.perMin, 5);
		changePrice.setHorizontalAlignment(JTextField.RIGHT);
		changePer.setHorizontalAlignment(JTextField.RIGHT);
		changePrice.setEditable(false);
		changePer.setEditable(false);
		JLabel won = new JLabel("원");
		JLabel min = new JLabel("분");
		JPanel price = new JPanel();
		price.add(changePrice);
		price.add(won);
		JPanel per = new JPanel();
		per.add(changePer);
		per.add(min);

		this.add(label);
		this.add(price);
		this.add(per);
		super.set();
		this.remove(numTextField);

		changePrice.addMouseListener(clickField);
		changePer.addMouseListener(clickField);
	}

	private MouseListener clickField = new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == changePrice) {
				changePrice.setText(initField());
				for (int i = 0; i < 12; i++) {
					buttons[i].removeActionListener(numPerActionListener);
					buttons[i].addActionListener(numPriceActionListener);
				}
			}
			if (e.getSource() == changePer) {
				changePer.setText(initField());
				for (int i = 0; i < 12; i++) {
					buttons[i].addActionListener(numPerActionListener);
					buttons[i].removeActionListener(numPriceActionListener);
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	};

	private ActionListener numPriceActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String num = e.getActionCommand();
			if (isNumeric(num)) {
				if (changePrice.getText().length() < digit) {
					changePrice.setText(changePrice.getText() + num);
				}
			}
			if (num.equals("초기화")) {
				changePrice.setText(p.price + "");
				changePer.setText(p.perMin + "");
			}
			if (num.equals("<-")) {
				if (!changePrice.getText().equals(""))
					changePrice.setText(changePrice.getText().substring(0, changePrice.getText().length() - 1));
			}
		}

	};

	private ActionListener numPerActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String num = e.getActionCommand();
			if (isNumeric(num)) {
				if (Integer.parseInt(changePer.getText() + num) < 60) {
					changePer.setText(changePer.getText() + num);
				}
			}
			if (num.equals("초기화")) {
				changePer.setText(p.price + "");
				changePer.setText(p.perMin + "");
			}
			if (num.equals("<-")) {
				if (!changePer.getText().equals(""))
					changePer.setText(changePer.getText().substring(0, changePer.getText().length() - 1));
			}
		}

	};

}
