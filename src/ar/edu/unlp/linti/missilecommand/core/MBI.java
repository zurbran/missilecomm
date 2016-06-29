package ar.edu.unlp.linti.missilecommand.core;

import java.awt.Color;

import ar.edu.unlp.linti.missilecommand.ui.Drawable;
import ar.edu.unlp.linti.missilecommand.ui.GameObjectRenderer;


/**
 * 
 * Representa un Misil Balistico Interplanetario de trayectoria recta.
 * 
 */
public class MBI extends Missile implements Krytolian, Destroyable, Drawable
{
	public MBI(Position origen, Position destino, double velocidad)
	{
		super(origen, destino, velocidad, new Color(183, 26, 0));
		// El area de colision del MBI ocupa 1 pixel
		this.shape = new RectangleShape(1, 1);
		System.out.println("MBI generado origen=" + origen.toString() + " destino=" + destino.toString() + " velocidad=" + velocidad);
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
