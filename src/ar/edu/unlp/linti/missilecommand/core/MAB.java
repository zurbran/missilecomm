package ar.edu.unlp.linti.missilecommand.core;

import java.awt.Color;

import ar.edu.unlp.linti.missilecommand.ui.Drawable;
import ar.edu.unlp.linti.missilecommand.ui.GameObjectRenderer;

/**
 * 
 * Representa un Misil Anti Balistico
 * 
 */
public class MAB extends Missile implements Drawable
{
	private static final double velocidad = 14.0;
	
	public MAB(Position origen, Position destino)
	{
		super(origen, destino, velocidad, new Color(0, 74, 125));
		// El area de colision del MAB ocupa 1 pixel
		this.shape = new RectangleShape(1, 1);
		System.out.println("MBA generado origen=" + origen.toString() + " destino=" + destino.toString() + " velocidad=" + velocidad);
	}

	@Override
	public void draw(GameObjectRenderer visitor)
	{
		visitor.draw(this);		
	}
}
