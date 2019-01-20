package gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class JLabelGrand extends JLabel {

	private static final long serialVersionUID = 1L;

	public JLabelGrand(String string, int pos) {
		super(string, pos);
		setFont(new Font(super.getName(), Font.PLAIN, super.getFont().getSize() * 2));
		super.setPreferredSize(new Dimension(32, 32));
	}

}
