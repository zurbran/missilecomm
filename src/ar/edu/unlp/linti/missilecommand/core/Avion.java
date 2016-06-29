package ar.edu.unlp.linti.missilecommand.core;

import ar.edu.unlp.linti.missilecommand.ui.Drawable;
import ar.edu.unlp.linti.missilecommand.ui.GameObjectRenderer;

/**
 * Permite instanciar un Avion. Tiene forma de avion y ataca disparando 2 o mas
 * misiles balisticos interplanetarios de trayectoria recta * 
 */
public class Avion extends Bombardero implements Drawable
{

	public Avion(double altura, Sentido sentido)
	{
		super(altura, sentido);
		this.shape = new RectangleShape(60, 33);
		System.out.println("Avion generado altura=" + altura + " sentido=" + sentido.toString());
	}

	@Override
	public void draw(GameObjectRenderer visitor)
	{
		visitor.draw(this);		
	}

}
