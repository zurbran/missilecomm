package ar.edu.unlp.linti.missilecommand.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ar.edu.unlp.linti.missilecommand.core.DrawListener;
import ar.edu.unlp.linti.missilecommand.core.Game;
import ar.edu.unlp.linti.missilecommand.core.GameObject;
import ar.edu.unlp.linti.missilecommand.core.Position;
import ar.edu.unlp.linti.missilecommand.core.Score;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.List;

public class GameUIBS extends JPanel implements DrawListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1108360606209877400L;
	private Canvas canvas;
	private JFrame parent;

	/**
	 * Create the panel.
	 */
	public GameUIBS(JFrame parent)
	{
		setLayout(null);
		this.parent = parent;
		canvas = new Canvas();
		setBackground(Color.black);

		add(canvas);
		canvas.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseReleased(MouseEvent arg0)
			{

			}

			@Override
			public void mousePressed(MouseEvent arg0)
			{
				final int x = (int) ((arg0.getX() * Game.GAME_WIDTH) / canvas.getWidth());
				final int y = (int) ((arg0.getY() * Game.GAME_HEIGHT) / canvas.getHeight());
				try
				{
					Game.getInstance().getRunQueue().put(new Runnable()
					{
						public void run()
						{
							Game.getInstance().getLevel().MABautomatico(new Position(x, y));
						}
					});
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}

			}

			@Override
			public void mouseExited(MouseEvent arg0)
			{

			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{

			}

			@Override
			public void mouseClicked(MouseEvent arg0)
			{

			}
		});

	}

	private void setHints(Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	}

	public void drawGame(List<GameObject> gameObjects, Score score, int level, boolean nextLevel,
			boolean gameOver)
	{
		centerCanvas();

		BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null)
		{
			canvas.createBufferStrategy(2);
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		setHints(g);

		GameObjectRenderer renderer = new GameObjectRenderer((Graphics2D) g, 1.0 * ((double) parent
				.getContentPane().getHeight() / Game.GAME_HEIGHT));
		renderer.drawBackground();
		renderer.drawAll(gameObjects);
		renderer.drawOverlay(score);
		if (nextLevel)
		{
			renderer.drawNextLevel(gameObjects, level, score);
		}
		else if (gameOver)
		{
			renderer.drawGameOver();
		}

		g.dispose();
		bs.show();
	}

	private void centerCanvas()
	{
		int width = (int) ((double) parent.getContentPane().getHeight() * ((double) Game.GAME_WIDTH / Game.GAME_HEIGHT));
		canvas.setLocation((parent.getContentPane().getWidth() - width) / 2, 0);
		canvas.setSize(width, parent.getContentPane().getHeight());
	}

	@Override
	public void drawEvent(List<GameObject> gameObjects, int level, Score score)
	{
		drawGame(gameObjects, score, level, false, false);
	}

	@Override
	public void drawNextLevelEvent(List<GameObject> gameObjects, int level, Score score)
	{
		drawGame(gameObjects, score, level, true, false);
	}

	@Override
	public void drawGameOverEvent(List<GameObject> gameObjects, int level, Score score)
	{
		drawGame(gameObjects, score, level, false, true);
	}

	@Override
	public void showMainScreen(List<GameObject> gameObjects, int level, Score score)
	{
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				MissileCommand.getInstance().showReglas();
			}
		});
	}
}
