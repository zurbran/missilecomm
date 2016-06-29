package ar.edu.unlp.linti.missilecommand.ui;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JRadioButton;

public class Reglas extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2296440979698190679L;

	/**
	 * Create the panel.
	 */
	public Reglas()
	{
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnNewRadioButton, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnNewRadioButton, 10, SpringLayout.WEST, this);
		add(rdbtnNewRadioButton);

	}
}
