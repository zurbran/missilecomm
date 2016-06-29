package ar.edu.unlp.linti.missilecommand.ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.Font;
import java.awt.Color;

public class MainPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5779182915337513200L;

	/**
	 * Create the panel.
	 */
	public MainPanel()
	{
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("min:grow(40)"),
				ColumnSpec.decode("default:grow(50)"),
				ColumnSpec.decode("min:grow(33)"),},
			new RowSpec[] {
				RowSpec.decode("default:grow(20)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("40dlu"),
				RowSpec.decode("default:grow(20)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow(20)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("40dlu"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow(20)"),}));
		
		JButton btnReglas = new JButton("Reglas");
		btnReglas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReglas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(btnReglas, "1, 3, center, fill");
		
		JButton btnHighScore = new JButton("High Score");
		btnHighScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(btnHighScore, "3, 3, center, fill");
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(btnJugar, "2, 10, center, fill");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MissileCommand.getInstance().mostrarAjustes();
			}
		});

	}
}
