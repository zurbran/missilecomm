package ar.edu.unlp.linti.missilecommand.core;

import ar.edu.unlp.linti.missilecommand.ui.Drawable;
import ar.edu.unlp.linti.missilecommand.ui.GameObjectRenderer;

/**
 * Representa una explosion. Esta es un circulo que crece hasta cierto tamano
 * maximo, y luego se achica hasta el tamano inicial. Cuando toque algo que sea
 * Destroyable, lo destruira.
 */
public class Explosion extends GameObject implements Drawable
{
	private static final int MIN_RADIUS = 5;
	private static final int MAX_RADIUS = 30;
	private static final int SPEED = 1;

	private boolean growing;
	private boolean ignoreScore;

	/**
	 * 
	 * @param origen
	 *            Centro en el que aparecera la explosion
	 * @param ignoreScore
	 *            ignore el score
	 */
	public Explosion(Position origen, boolean ignoreScore)
	{
		super(origen);
		this.shape = new CircleShape(MIN_RADIUS);
		this.growing = true;
		this.ignoreScore = ignoreScore;
	}

	@Override
	void update()
	{
		CircleShape circle = (CircleShape) this.shape;
		if (growing)
		{
			if (circle.getRadius() < MAX_RADIUS)
			{
				circle.setRadius(circle.getRadius() + SPEED);
			}
			else
			{
				this.growing = false;
			}
		}
		else
		{
			if (circle.getRadius() > MIN_RADIUS)
			{
				circle.setRadius(circle.getRadius() - SPEED);
			}
			else
			{
				this.toRemove = true;
			}
		}

		for (GameObject ent : Game.getInstance().getGameObjects())
		{
			// Solo preocuparnos por los objetos "Destruibles". Ademas,
			// solamente esta implementada la deteccion para
			// aquellos que tengan forma rectangular, ya que por ahora no
			// necesitamos detectar colsiones entre dos objetos
			// circulares, solamente entre circulo (Explosion) y rectangulo
			// (misil, crucero, ciudad, etc)
			if (ent instanceof Destroyable && ent.getShape() instanceof RectangleShape)
			{
				// Evitemos los GameObjects que esten por ser borradas
				if (!ent.isToRemove())
				{
					RectangleShape entRect = (RectangleShape) ent.getShape();

					double closestX = Math.min(ent.getPosition().getX() + entRect.getWidth() / 2,
							Math.max(ent.getPosition().getX() - entRect.getWidth() / 2,
									this.position.getX()));
					double closestY = Math.min(ent.getPosition().getY() + entRect.getHeight() / 2,
							Math.max(ent.getPosition().getY() - entRect.getHeight() / 2,
									this.position.getY()));

					Position closestPos = new Position(closestX, closestY);

					if (closestPos.getEuclideanDistance(this.position) < circle.getRadius())
					{
						System.out.println("Explosion toco un " + ent.getClass().getSimpleName());
						Game.getInstance().callDestroy(ent, ignoreScore);
					}
				}
			}
		}
	}

	@Override
	public void draw(GameObjectRenderer visitor)
	{
		visitor.draw(this);
	}
}
