package edu.cmu.lti.bic.sbs.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author Xiangzhu Long
 * 
 */
public class ClockPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2681406500889937962L;

	JLabel timeLabel = null;

	int second = 0;
	int minute = 0;
	int hour = 0;

	public void setTime(int h, int m, int s) {
		second = s;
		minute = m;
		hour = h;
		StringBuilder text = new StringBuilder();
		if (h % 10 == 0) {
			text.append("0" + hour);
		} else {
			text.append(hour);
		}

		text.append(':');

		if (m % 10 == 0) {
			text.append("0" + minute);
		} else {
			text.append(minute);
		}

		text.append(':');

		if (s % 10 == 0) {
			text.append("0" + second);
		} else {
			text.append(second);
		}
		timeLabel.setText(text.toString());
	}

	public ClockPanel() {
		// this.setBorder(border);
		this.setBorder(new TitledBorder(null, "Clock", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		timeLabel = new JLabel();
		timeLabel.setText(0 + "");
		this.add(timeLabel);
		this.setTime(12, 0, 0);
	}
}
