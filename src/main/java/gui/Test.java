package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class Test extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JToolBar barreOutils = new JToolBar();
		getContentPane().add(barreOutils, BorderLayout.NORTH);
		
		JButton boutonNouveauJeu = new JButton("Nouveau Jeu");
		barreOutils.add(boutonNouveauJeu);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		ButtonGroup toggleButtonGroupe = new ButtonGroup();
		
		JToggleButton tglbtnT = new JToggleButton("t1");
		toggleButtonGroupe.add(tglbtnT);
		panel.add(tglbtnT);
		
		JToggleButton tglbtnT_1 = new JToggleButton("t2");
		toggleButtonGroupe.add(tglbtnT_1);
		panel.add(tglbtnT_1);
		
		JToggleButton tglbtnT_2 = new JToggleButton("t3");
		toggleButtonGroupe.add(tglbtnT_2);
		panel.add(tglbtnT_2);
	}

}
