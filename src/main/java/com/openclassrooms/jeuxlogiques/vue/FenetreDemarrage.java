package com.openclassrooms.jeuxlogiques.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

public class FenetreDemarrage extends JWindow {

	private static final long serialVersionUID = 1L;

	public FenetreDemarrage() {

		JLabel labelImage = new JLabel(new ImageIcon(getClass().getResource("/image_demarrage.png")));
		labelImage.setLayout(new BorderLayout());
		add(labelImage, BorderLayout.CENTER);

		JPanel panneauBarreProgression = new JPanel();
		panneauBarreProgression.setLayout(new BoxLayout(panneauBarreProgression, BoxLayout.Y_AXIS));
		labelImage.add(panneauBarreProgression, BorderLayout.SOUTH);
		panneauBarreProgression.setBackground(new Color(0, 0, 0, 0));

		JLabel labelDemarrage = new JLabel("Démarrage...");
		labelDemarrage.setAlignmentX(0.15f);
		labelDemarrage.setFont(new Font(labelDemarrage.getFont().getFontName(), Font.ITALIC,
				(int) (labelDemarrage.getFont().getSize() * .95)));
		panneauBarreProgression.add(labelDemarrage);

		JProgressBar barreProgression = new JProgressBar(0, 100);
		panneauBarreProgression.add(barreProgression);
		barreProgression.setStringPainted(true);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		new Thread(new Runnable() {
			public void run() {
				try {
					for (int i = 1; i <= 100; i++) {
						barreProgression.setValue(i);
						Thread.sleep(30);
					}
				} catch (Exception e) {
				}
				setVisible(false);
				dispose();
			}
		}).start();
	}

}
