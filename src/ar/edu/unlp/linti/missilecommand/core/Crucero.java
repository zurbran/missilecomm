package ar.edu.unlp.linti.missilecommand.core;

import java.awt.Color;

import ar.edu.unlp.linti.missilecommand.ui.Drawable;
import ar.edu.unlp.linti.missilecommand.ui.GameObjectRenderer;

/**
 * 
 * Clase abstracta para los Cruceros. Implementa la forma de un crucero, y que
 * hacer cuando es destruido (explotar)
 * 
 */
public abstract class Crucero extends Missile implements Krytolian, Destroyable, Drawable
{

	public Crucero(Position origen, Position destino, double velocidad)
	{
		super(origen, destino, velocidad, new Color(183, 26, 0));
		this.shape = new RectangleShape(10, 10);
	}

	@Override
	public void destroyMe()
	{
		this.explotar(false);
	}
	
	@Override
	public void draw(GameObjectRenderer visitor)
	{
		visitor.draw(this);		
	}

}
