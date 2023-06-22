package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NumericPane extends JPanel {

	public JPanel buttonPanel;
	public JTextField numTextField;
	public int digit;
	public JButton buttons[] = new JButton[12];

	public void set() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 3, 10, 10));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		numTextField = new JTextField(initField(), 10);
		numTextField.setEditable(false);

		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton((i + 1) + "");
		}
		buttons[9] = new JButton("초기화");
		buttons[10] = new JButton("0");
		buttons[11] = new JButton("<-");

		for (int i = 0; i < 12; i++) {
			buttons[i].addActionListener(numActionListener);
			buttonPanel.add(buttons[i]);
		}
		this.add(numTextField);
		this.add(buttonPanel);

	}

	public static boolean isNumeric(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	protected String initField() {
		return "";
	}

	public void clear() {
		numTextField.setText(initField());
	}

	public String getValue() {
		return numTextField.getText();
	}

	protected ActionListener numActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String num = e.getActionCommand();
			if (isNumeric(num)) {
				if (numTextField.getText().length() < digit) {
					numTextField.setText(numTextField.getText() + num);
				}
			}
			if (num.equals("초기화")) {
				clear();
			}
			if (num.equals("<-")) {
				if (!numTextField.getText().equals(initField()))
					numTextField.setText(numTextField.getText().substring(0, numTextField.getText().length() - 1));
			}
		}

	};
}
