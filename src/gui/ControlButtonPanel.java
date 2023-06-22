package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlButtonPanel extends JPanel {
	private JButton preBtn = new JButton("이전");
	private JButton nextBtn = new JButton("다음");
	private JPanel customBtnPanel;
	private JButton customBtn;

	public ControlButtonPanel() {
		setLayout(new GridLayout(1, 2, 0, 0));
		add(preBtn);
		add(nextBtn);
	}

	public void setEnabled(Boolean prev, Boolean next) {
		preBtn.setEnabled(prev);
		nextBtn.setEnabled(next);
	}

	public void setPreBtnActionListener(ActionListener actionListener) {
		for (ActionListener act : preBtn.getActionListeners()) {
			preBtn.removeActionListener(act);
		}
		preBtn.addActionListener(actionListener);
	}

	public void setNextBtnActionListener(ActionListener actionListener) {
		for (ActionListener act : nextBtn.getActionListeners()) {
			nextBtn.removeActionListener(act);
		}
		nextBtn.addActionListener(actionListener);
	}

	public void changeNextButton(JPanel btnPanel) {
		if (btnPanel != null) {
			customBtnPanel = btnPanel;
			remove(nextBtn);
			add(customBtnPanel);
		}
	}

	public void changeNextButton(JButton btn) {
		if (btn != null) {
			customBtn = btn;
			remove(nextBtn);
			add(customBtn);
		}
	}

	public void resetNextButton() {
		if (customBtnPanel != null)
			remove(customBtnPanel);
		if (customBtn != null)
			remove(customBtn);
		add(nextBtn);
	}

	public void removeNextBtn() {
		remove(nextBtn);
	}
}
