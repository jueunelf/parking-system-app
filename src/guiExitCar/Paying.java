package guiExitCar;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Paying extends JPanel {

    JTextArea payment = new JTextArea();

    Paying(){
        add(payment);
    }

    void set(int p) {
    	payment.setFont(new Font(payment.getFont().getName(), Font.BOLD, 20));
        payment.setText(p + "(원) 결제 중...");
    }
}
