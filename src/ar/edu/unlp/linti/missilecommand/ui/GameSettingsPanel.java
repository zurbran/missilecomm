package ar.edu.unlp.linti.missilecommand.ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;

import java.awt.Color;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GameSettingsPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4760601889106728239L;
	private JTextField textField;
	private JLabel lblError;
	private JSpinner spinner;

	/**
	 * Create the panel.
	 */
	public GameSettingsPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{78, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblError = new JLabel("");
		lblError.setFont(new Font("Helvetica LT Std Compressed", Font.PLAIN, 28));
		lblError.setForeground(Color.RED);
		GridBagConstraints gbc_lblDebesIngresarUn = new GridBagConstraints();
		gbc_lblDebesIngresarUn.insets = new Insets(0, 0, 5, 5);
		gbc_lblDebesIngresarUn.gridx = 2;
		gbc_lblDebesIngresarUn.gridy = 1;
		add(lblError, gbc_lblDebesIngresarUn);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Helvetica LT Std Compressed", Font.PLAIN, 28));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 2;
		add(lblNombre, gbc_lblNombre);
		
		textField = new JTextField();
		textField.setFont(new Font("Helvetica LT Std Compressed", Font.PLAIN, 28));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(20);
		
		JLabel lblNivel = new JLabel("Nivel");
		lblNivel.setFont(new Font("Helvetica LT Std Compressed", Font.PLAIN, 28));
		GridBagConstraints gbc_lblNivel = new GridBagConstraints();
		gbc_lblNivel.anchor = GridBagConstraints.EAST;
		gbc_lblNivel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNivel.gridx = 1;
		gbc_lblNivel.gridy = 3;
		add(lblNivel, gbc_lblNivel);
		
		spinner = new JSpinner();
		spinner.setFont(new Font("Helvetica LT Std Compressed", Font.PLAIN, 28));
		spinner.setModel(new SpinnerNumberModel(1, 1, 999, 1));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 3;
		add(spinner, gbc_spinner);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.setFont(new Font("Helvetica LT Std Compressed", Font.PLAIN, 28));
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verifyInput();
			}
		});
		GridBagConstraints gbc_btnJugar = new GridBagConstraints();
		gbc_btnJugar.insets = new Insets(0, 0, 5, 5);
		gbc_btnJugar.gridx = 2;
		gbc_btnJugar.gridy = 4;
		add(btnJugar, gbc_btnJugar);

	}

	private void verifyInput()
	{
		if(textField.getText().length() == 0)
		{
			lblError.setText("Debes ingresar un nombre válido");
			return;
		}
		if((Integer)spinner.getValue() <= 0)
		{
			lblError.setText("Debes ingresar un nivel válido");
			return;
		}
		String name = textField.getText().substring(0, Math.min(textField.getText().length(), 20));
		int level = (Integer)spinner.getValue();
		lblError.setText("");
		MissileCommand.getInstance().empezarJuego(name, level);
	}
}
