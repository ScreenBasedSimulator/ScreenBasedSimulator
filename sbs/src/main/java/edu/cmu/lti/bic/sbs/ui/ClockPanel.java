package edu.cmu.lti.bic.sbs.ui;

<<<<<<< HEAD
import javax.swing.JLabel;
import javax.swing.JPanel;
=======
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
>>>>>>> 50dcf14a682657f681f5bb3700d6ea479e56c0f3
import javax.swing.border.TitledBorder;

/**
 * 
 * @author Xiangzhu Long
 * 
 */
public class ClockPanel extends JPanel {
	private static final long serialVersionUID = 2681406500889937962L;

	JLabel timeLabel = null;

	int second = 0;
	int minute = 0;
	int hour = 0;

	/**
	 * setTime method sets the current time of clock panel.
	 * 
	 * @param h
	 *          represents the current hour
	 * @param m
	 *          represents the current minute
	 * @param s
	 *          represents the current second
	 */
	public void setTime(int h, int m, int s) {
		second = s;
		minute = m;
		hour = h;
		StringBuilder text = new StringBuilder();
		if (h < 0) {
			text.append("0" + h);
		} else {
			text.append(hour);
		}

		text.append(':');

		if (m < 10) {
			text.append("0" + m);
		} else {
			text.append(minute);
		}

		text.append(':');

		if (s < 10) {
			text.append("0" + s);
		} else {
			text.append(second);
		}
		timeLabel.setText(text.toString());
		timeLabel.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		timeLabel.setForeground(new Color(255,255,255));
	}

	/**
	 * initialize the clock panel and specify its content when initialized.
	 */
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
