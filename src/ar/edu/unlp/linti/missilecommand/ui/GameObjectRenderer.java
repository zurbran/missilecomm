package ar.edu.unlp.linti.missilecommand.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;

import ar.edu.unlp.linti.missilecommand.core.*;

public class GameObjectRenderer
{
	private Graphics2D g2d;
	private Resources resources;
	private double scale = 1.0;

	public GameObjectRenderer(Graphics2D g2d, double scale)
	{
		this.g2d = g2d;
		this.resources = Resources.getInstance();
		this.scale = scale;
	}


	private void setColor(Color color)
	{
		g2d.setColor(color);
	}

	private void fillOval(double i1, double i2, double i3, double i4)
	{
		g2d.fillOval((int) (i1 * scale), (int) (i2 * scale), (int) (i3 * scale), (int) (i4 * scale));
	}

	private void drawImage(Image img, double i1, double i2, double i3, double i4)
	{
		g2d.drawImage(img, (int) (i1 * scale), (int) (i2 * scale), (int) (i3 * scale),
				(int) (i4 * scale), null);
	}

	private void drawLine(double i1, double i2, double i3, double i4)
	{
		g2d.drawLine((int) (i1 * scale), (int) (i2 * scale), (int) (i3 * scale), (int) (i4 * scale));
	}

	private void setBasicStroke(float stroke)
	{
		g2d.setStroke(new BasicStroke(stroke * (float) scale));
	}

	private void setFont(Font font)
	{
		g2d.setFont(font.deriveFont(font.getSize() * (float) scale));
	}

	private void drawShadowedString(String string, double i1, double i2, double xdist, double ydist)
	{
		Color c = g2d.getColor();
		setColor(Color.BLACK);
		g2d.drawString(string, (int) ((i1 + xdist) * scale), (int) ((i2 + ydist) * scale));
		setColor(c);
		g2d.drawString(string, (int) (i1 * scale), (int) (i2 * scale));
	}

	private double stringWidth(String s)
	{
		return g2d.getFontMetrics().stringWidth(s) / scale;
	}

	public void drawAll(List<GameObject> gameObjects)
	{
		// Necesito dibujar en el fondo las estelas, para que estas no tapen a
		// los misiles
		// TODO: Agregar sistema de dibujo con indice z que dibuje en orden de
		// menor z a mayor z

		for (GameObject obj : gameObjects)
		{
			if (obj instanceof Drawable && obj instanceof Estela)
			{
				Drawable drawable = (Drawable) obj;
				drawable.draw(this);
			}
		}

		for (GameObject obj : gameObjects)
		{
			if (obj instanceof Drawable && !(obj instanceof Estela))
			{
				Drawable drawable = (Drawable) obj;
				drawable.draw(this);
			}
		}
	}

	public void drawOverlay(Score score)
	{
		setColor(Color.RED);
		setFont(resources.getImagineFont().deriveFont(20.0f));
		drawShadowedString(Integer.toString(score.getTotal()), 30.0, 14.0, 1.2, 2.0);
	}
	
	public void draw(Explosion explosion)
	{
		setColor(Color.WHITE);
		int radius = ((CircleShape) explosion.getShape()).getRadius();
		fillOval(explosion.getPosition().getX() - radius, explosion.getPosition().getY() - radius,
				radius * 2, radius * 2);
	}

	public void draw(MAB missile)
	{
		setColor(Color.WHITE);
		fillOval(missile.getPosition().getX() - 2, missile.getPosition().getY() - 2, 4, 4);
	}

	public void draw(MBI missile)
	{
		setColor(Color.WHITE);
		// fillRect(missile.getPosition().getX() - 2,
		// missile.getPosition().getY() - 2, 3, 3);
		fillOval(missile.getPosition().getX() - 2, missile.getPosition().getY() - 2, 4, 4);
	}

	public void draw(Crucero crucero)
	{
		Position pos = crucero.getPosition();
		RectangleShape shape = (RectangleShape) crucero.getShape();
		int width = shape.getWidth();
		int height = shape.getHeight();
		drawImage(resources.getCruceroSprite(), pos.getX() - width / 2, pos.getY() - height / 2,
				width, height);
	}

	public void draw(MilitaryBase mb)
	{
		int xOffset = 10;
		int yOffset = 0;
		for (int i = 0; i < mb.getMissileCount(); i++)
		{
			if (i == 4)
			{
				xOffset = 0;
				yOffset = 19;
			}
			drawImage(resources.getMilbaseSprite(), mb.getPosition().getX() + xOffset - 30, mb
					.getPosition().getY() + yOffset - 5, 9, 19);

			xOffset += 9;
		}
	}

	public void draw(City city)
	{

		Position pos = city.getPosition();
		RectangleShape shape = (RectangleShape) city.getShape();
		int width = shape.getWidth();
		int height = shape.getHeight();
		drawImage(resources.getCitySprite(), pos.getX().intValue() - width / 2, pos.getY()
				.intValue() - height / 2, width, height);

	}

	public void draw(Satelite satelite)
	{
		Position pos = satelite.getPosition();
		RectangleShape shape = (RectangleShape) satelite.getShape();
		int width = shape.getWidth();
		int height = shape.getHeight();
		drawImage(resources.getSateliteSprite(), pos.getX() - width / 2, pos.getY() - height / 2,
				width, height);
	}

	public void draw(Avion avion)
	{
		Position pos = avion.getPosition();
		RectangleShape shape = (RectangleShape) avion.getShape();
		int width = shape.getWidth();
		int height = shape.getHeight();
		// Podriamos girarlo con codigo pero es mas eficiente usar una imagen ya
		// girada
		drawImage(
				avion.getSentido() == Sentido.IZQUIERDA ? resources.getAvionSprite()
						: resources.getAvionFSprite(), pos.getX() - width / 2, pos.getY() - height
						/ 2, width, height);
	}

	public void draw(Estela estela)
	{
		setColor(estela.getColor());
		setBasicStroke(2.0f);
		Position startPos = estela.getStartPos();
		Position currentPos = estela.getPosition();
		drawLine(startPos.getX().intValue(), startPos.getY(), currentPos.getX(), currentPos.getY()
				.intValue());
	}

	public void drawBackground()
	{
		drawImage(resources.getBackgroundSprite(), 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
	}

	public void drawNextLevel(List<GameObject> gameObjects, int level, Score score)
	{
		drawImage(resources.getLevelSprite(), 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

		String s = "Nivel " + level;
		setColor(new Color(255, 30, 30));
		setFont(resources.getLotlFont());
		int x = (int) ((Game.GAME_WIDTH - stringWidth(s)) / 2);
		drawShadowedString(s, x, 124, 2.0, 2.5);

		// No nos interesa dibujar la puntuacion en el primer nivel
		if (score.getTotal() > 0)
		{
			setFont(resources.getImagineFont().deriveFont(20.0f));
			int xt1 = (int) (195 - stringWidth(Integer.toString(score.getLastScoreCity())));
			drawShadowedString(Integer.toString(score.getLastScoreCity()), xt1, 200, 1.0, 2.0);

			int offset = 0;
			for (GameObject obj : gameObjects)
			{
				if (obj instanceof City)
				{
					drawImage(resources.getCitySprite(), 205 + offset, 176, 24, 33);
					offset += 30;
				}
			}

			int xt2 = (int) (195 - stringWidth(Integer.toString(score.getLastScoreMBA())));
			drawShadowedString(Integer.toString(score.getLastScoreMBA()), xt2, 260, 1.0, 2.0);

			int xOffset = 10;
			int yOffset = 0;
			for (GameObject obj : gameObjects)
			{
				if (obj instanceof MilitaryBase)
				{
					MilitaryBase mb = (MilitaryBase) obj;
					for (int i = 0; i < mb.getMissileCount(); i++)
					{
						drawImage(resources.getMilbaseSprite(), 194 + xOffset, 245 + yOffset, 9, 19);

						xOffset += 9;
					}
				}
			}
		}
	}

	public void drawGameOver()
	{
		drawImage(resources.getLevelSprite(), 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

		setColor(new Color(255, 30, 30));
		setFont(resources.getLotlFont());
		int x = (int) ((Game.GAME_WIDTH - stringWidth("GAME OVER")) / 2);
		drawShadowedString("GAME OVER", x, 124, 2.0, 2.5);
	}

}
