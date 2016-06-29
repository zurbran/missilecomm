package ar.edu.unlp.linti.missilecommand.core;

import ar.edu.unlp.linti.missilecommand.ui.Drawable;
import ar.edu.unlp.linti.missilecommand.ui.GameObjectRenderer;

/**
 * Permite instanciar un Satelite. Tiene forma cuadrada y ataca disparando 2 o
 * mas misiles balisticos interplanetarios de trayectoria recta *
 */
public class Satelite extends Bombardero implements Drawable
{

	public Satelite(double altura, Sentido sentido)
	{
		super(altura, sentido);
		this.shape = new RectangleShape(65, 32);
		System.out.println("Satelite generado altura=" + altura + " sentido=" + sentido.toString());
	}

	@Override
	public void draw(GameObjectRenderer visitor)
	{
		visitor.draw(this);		
	}

}
