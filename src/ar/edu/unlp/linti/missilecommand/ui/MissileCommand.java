package ar.edu.unlp.linti.missilecommand.ui;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.*;

import ar.edu.unlp.linti.missilecommand.core.*;

public class MissileCommand extends JFrame
{
	private static final long serialVersionUID = -4045761113399213210L;

	private static MissileCommand instance = null;
	
	private JPanel cards;
	private CardLayout cardsLayout;
	
	private GameUIBS gameUIBS;
	private GameSettingsPanel gameSettingsPanel;
	
	public static MissileCommand getInstance()
	{
		// Nunca deberia ocurrir
		/*if(instance == null)
		{
			instance = new MissileCommand();
		}*/
		return instance;
	}
	
	private MissileCommand()
	{
		initComponents();
		setVisible(true);
	}
	
	private void initComponents()
	{

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		//setMaximumSize(new java.awt.Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT + 25));
		setMinimumSize(new java.awt.Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT + 25));
		setPreferredSize(new java.awt.Dimension(700, 665));
		setResizable(true);
		setTitle("Missile Command");

		cardsLayout = new CardLayout();
		cards = new JPanel(cardsLayout);
		
		gameUIBS = new GameUIBS(this);
		gameSettingsPanel = new GameSettingsPanel();
		
		cards.add(new MainPanel(), "MainPanel");
		cards.add(gameSettingsPanel, "GameSettings");
		//cards.add(new Reglas(), "Reglas");
		cards.add(gameUIBS, "GameUIBS");
		
		getContentPane().add(cards);
		
		Game.getInstance().setDrawListener(gameUIBS);

		pack();
	}
	
	public void showReglas()
	{
		cardsLayout.show(cards, "MainPanel");
	}
	
	public void empezarJuego(String name, int level)
	{
		Game.getInstance().start(level);
		cardsLayout.show(cards, "GameUIBS");
	}
	
	public static void main(String args[])
	{
		try
		{
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				instance = new MissileCommand();
			}
		});
	}

	public void mostrarAjustes()
	{
		cardsLayout.show(cards, "GameSettings");		
	}
}
